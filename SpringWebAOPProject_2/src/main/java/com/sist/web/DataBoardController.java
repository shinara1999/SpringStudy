package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sist.dao.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("databoard/") // 공통경로 저장
// RequestMapping => GetMapping + PostMapping 동시에 다룬다.
// GetMapping / PostMapping => 구분해서 사용하는 것 / 나눠서 다룬다.
// => PathValiable /databoard/detail/1 : 폴더인지 파일인지 값인지 모르게 프로그램을 짠다. => 해킹위험 방지
/*
		1. Model
		  1) 구분자 (메소드 찾기)
		  	 => @RequestMapping (URI)
		  	    = @GetMapping (URI)
		  	    = @PostMapping (URI)
		  http://localhost:8080/web/databoard/list.do => URL
		  --------------------- ---------------------
		  		  서버정보			     URI
		  		  						  |
		  		  				     ContextPath
		2. 리턴형
		   => String : JSP파일 지정
		   			   경로명 / JSP명 => forward
		   			   redirect:... => 반드시 확장자가 .do로 끝나야 한다. => sendRedirect
		   => void : 다운로드 , ajax (X) => @RestController
		3. 매개변수 : String, 해당 데이터형, VO
					=> 내장객체, Model, RedirectAttibutes
		4. 메소드명은 아무거나 사용 가능
		5. getBean() => DispacherServlet에서 처리
		   => @Autowired
		*** 스프링에서 메모리 할당을 해야 @Autowired를 사용할 수 있다.
		
		@Autowired는 객체 주소를 받는 경우에만 사용
		@Value()
		  	    
 */
public class DataBoardController {
	@Autowired
	private DataBoardDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	// 사용자 요청 처리
	@GetMapping("list.do")
	/*
			매개변수
			모든 데이터는 String, 해당 데이터형으로 설정
			Model => 전송 객체
			addAttribute(String key, Object obj)
			{
				request.setAttribute(key, obj)
			}
			내장 객체
			1. HttpServletRequest
			2. HttpServletResponse
			3. HttpSession
			4. RedirectAttributes
			5. 커맨드 객체 => VO단위
			6. String[]
			7. List
			   => name="file[0]"
			   => 400 bad request
			   => 404, 500
	 */
	public String databoard_list(String page, Model model)
	{
		// 페이지 나누기
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		List<DataBoardVO> list=dao.databoardListData(start, end);
		
		// 총페이지
		int totalpage=dao.databoardTotalPage();
		model.addAttribute("curpage", curpage); // request.setAttribute 와 동일한 기작
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		
		return "databoard/list";
	}
	@GetMapping("insert.do")
	public String databoard_insert() // 화면만 보여준다.
	{
		return "databoard/insert";
	}
	@PostMapping("insert_ok.do")
	public String databoard_insert_ok(DataBoardVO vo, HttpServletRequest request)
	{
		String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
		path=path.replace("\\", File.separator);
		
		// 업로드 폴더가 없으면 폴더를 만들어라. (있으면 그냥 간다.)
		File dir=new File(path);
		if(!dir.exists())
		{
			dir.mkdir();
		}
		
		List<MultipartFile> list=vo.getFiles();
		if(list==null) // 업로드가 없는 상태
		{
			vo.setFilename("");
			vo.setFilesize("");
			vo.setFilecount(0);
		}
		else // 업로드가 된 상태
		{
			// 1. 업로드 할 파일 리스트를 받는다.
			String filename="";
			String filesize="";
			for(MultipartFile mf:list)
			{
				String name=mf.getOriginalFilename();
				//					사용자가 보낸 파일명
				File file=new File(path+name);
				// 2. try-catch절을 이용해 파일을 업로드한다.
				try
				{
					mf.transferTo(file); // transferTo : 업로드
				}catch(Exception e) {}
				filename+=name+",";
				filesize+=file.length()+","; // 파일이 여러개 들어오면 ,로 구분한다.
			}
			filename=filename.substring(0, filename.lastIndexOf(",")); // 맨 마지막 ,를 지운다.
			filesize=filesize.substring(0, filesize.lastIndexOf(","));
			vo.setFilename(filename);
			vo.setFilesize(filesize);
			vo.setFilecount(list.size());
		}
		
		String enPwd=encoder.encode(vo.getPwd());
		vo.setPwd(enPwd);
		
		// 위의 값을 db에 넣기
		dao.databoardInsert(vo);
		return "redirect:list.do";
	}
	// 상세보기
	@GetMapping("detail.do")
	public String databoard_detail(int no, Model model)
	{
		DataBoardVO vo=dao.databoardDetailData(no);
		if(vo.getFilecount()>0)
		{
			// 1. 배열을
			String[] filenames=vo.getFilename().split(",");
			String[] filesizes=vo.getFilesize().split(",");
			// 2. 리스트로 변환
			List<String> fList=Arrays.asList(filenames);
			List<String> sList=Arrays.asList(filesizes);
			// 3. 보내기
			model.addAttribute("fList", fList);
			model.addAttribute("sList", sList);
		}
		model.addAttribute("vo", vo); // 파일이 있을때만 보내줘야 하므로 vo는 if문 밖에 쓴다.
		return "databoard/detail";
	}
	
	// 파일다운로드
	@GetMapping("download.do")
	public void databoard_download(String fn, HttpServletRequest request, HttpServletResponse response) // fn, 내장객체
	{
		try
		{
			// file명, file크기
			String path=request.getSession().getServletContext().getRealPath("/")+"upload\\"; // 파일 다운받는 위치
			path=path.replace("\\", File.separator);
			File file=new File(path+fn);
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fn, "UTF-8")); // 파일명
			response.setContentLength((int)file.length()); // 파일크기
			
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file)); // 1. 서버에 있는 파일을 읽어서
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream()); // 2. 클라이언트 응답 영역으로 보낸다.
			
			int i=0;
			byte[] buffer=new byte[1024];
			while((i=bis.read(buffer, 0, 1024))!=-1) // 읽은 바이트 수
			{
				bos.write(buffer, 0, i);
			}
			bis.close();
			bos.close();
			
		}catch(Exception e) {}
	}
	
	// 수정하기 => 화면변경이 있으니까 Controller사용
	@GetMapping("update.do")
	public String databoard_update(int no, Model model)
	{
		DataBoardVO vo=dao.databoardUpdateData(no);
		model.addAttribute("vo", vo);
		return "databoard/update";
	}
	@PostMapping("update_ok.do")
	public String databoard_update_ok(DataBoardVO vo, Model model, HttpServletRequest request)
	{
		// 1. 파일을 다 지우고
		String path=request.getSession().getServletContext().getRealPath("/")+"upload\\"; // 파일 다운받는 위치
		path=path.replace("\\", File.separator);
		DataBoardVO fvo=dao.databoardFileInfoData(vo.getNo());
		try
		{	if(fvo.getFilecount()>0) // 파일 존재
			{
				StringTokenizer st=new StringTokenizer(vo.getFilename(), ",");
				while(st.hasMoreTokens())
				{
					File file=new File(path+st.nextToken());
				}
			}
		}catch(Exception e) {}
		
		// 2. 새로운 파일을 넣는 방식으로 간다.
		List<MultipartFile> list=vo.getFiles();
		if(list==null)
		{
			vo.setFilename("");
			vo.setFilesize("");
			vo.setFilecount(0);
		}
		else
		{
			String filename="";
			String filesize="";
			for(MultipartFile mf:list)
			{
				String name=mf.getOriginalFilename();
				File f=new File(path+name);
				try
				{
					mf.transferTo(f);
				}catch(Exception e) {}
				filename+=name+",";
				filesize+=f.length()+",";
			}
			filename=filename.substring(0, filename.lastIndexOf(","));
			filesize=filesize.substring(0, filesize.lastIndexOf(","));
			vo.setFilecount(list.size());
			vo.setFilename(filename);
			vo.setFilesize(filesize);
		}
		boolean bCheck=dao.databoardUpdate(vo);
		model.addAttribute("bCheck", bCheck);
		model.addAttribute("no", vo.getNo());
		return "databoard/update_ok";
	}
}





















