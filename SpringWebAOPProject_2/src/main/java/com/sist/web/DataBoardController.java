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

import java.io.File;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("databoard/") // 공통경로 저장
// RequestMapping => GetMapping + PostMapping 동시에 다룬다.
// GetMapping / PostMapping => 구분해서 사용하는 것 / 나눠서 다룬다.
// => PathValiable /databoard/detail/1 : 폴더인지 파일인지 값인지 모르게 프로그램을 짠다. => 해킹위험 방지
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
}





















