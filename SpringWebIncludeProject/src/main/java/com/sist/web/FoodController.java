package com.sist.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.FoodVO;
import com.sist.vo.ReplyVO;
/*
		RedirectAttributes
		Model
		
		< MVC 화면 변경 방법 2가지 >
		  1. request 전송 방법 => forward 기법
		     => 값을 전송 : Model
		     return "main/main"; (모델을 전송)
		  2. 재사용 / 재호출 방법 => sendRedirect
		     => RedirectAttributes
		     return "redirect:~.do"
		     
		Model (Controller)
		  1) 형식
		     = 리턴형
		       String, void만 사용 가능
		     = 매개변수
		       요청값을 받을 때 사용
		       String, 일반 데이터형 (int...), vo, 내장객체, 스프링 지원 객체 사용 가능 => 순서는 상관 없다.
		       => ?no=1 ==> (int no)
		       => <input type=text name=name> ==> (String name)
		       
		       => ?no=1&type=2 ==> (int no, int type) ==> 변수명을 맞춰줘야 한다.
		     
		     @GetMapping
		       => <a> sendRedirect location.href
		     @PostMapping
		       => <form> , ajax({type:'post';})
		       => axios.get()
		       => axios.post()
		     @RequestMapping => GetMapping+PostMapping
 */
@Controller
public class FoodController {
	
	@Autowired
	private FoodDAO dao;
	
	@Autowired // dao마다 따로따로 줘야 한다.
	private ReplyDAO rDao;
	
	// food/detail_before.do
	@GetMapping("food/detail_before.do")
	public String food_detail_before(int fno, HttpServletResponse response, RedirectAttributes ra) // RedirectAttributes : ? 안붙여줘도 된다.
	{
		// 쿠키는 일반 객체 => 매개변수로 받을 수 없다.
		Cookie cookie=new Cookie("food_"+fno, String.valueOf(fno)); // String.valueOf(fno) => 맛집번호
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		// sendRedirect => 값 전송 / 물음표 넘기지 말고 이 방법 사용하기
		ra.addAttribute("fno", fno);
		return "redirect:../food/detail.do";
	}
	
	@GetMapping("food/detail.do")
	public String food_detail(int fno, Model model)
	{
		FoodVO vo=dao.foodDetailData(fno);
		// 댓글
		List<ReplyVO> rList=rDao.replyListData(fno);
		model.addAttribute("rList", rList);
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../food/detail.jsp");
		return "main/main";
	}
	
	// 검색
	@RequestMapping("food/find.do") // get과 post 둘 다 사용해야 하므로 requestMapping 사용 (get: 페이지 나누기, post:form태그 submit검색버튼) 
	public String food_find(String page, String colname, String ss, Model model) //  Model model: 데이터 전송해야 하므로 필요
	{
		if(colname==null)
			colname="type"; // 타입 default
		if(ss==null)
			ss="한식"; // 한식 default
		if(page==null)
			page="1"; // 1페이지 default
		
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		Map map=new HashMap();
		map.put("col_name", colname); // col_name : FoodMapper에서 준 이름과 동일
		map.put("ss", ss);
		map.put("start", start);
		map.put("end", end);
		List<FoodVO> list=dao.foodFindData(map);
		
		int totalpage=dao.foodFindTotalPage(map);
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		model.addAttribute("ss", ss);
		model.addAttribute("colname", colname);

		model.addAttribute("main_jsp", "../food/find.jsp");
		return "main/main";
	}
}



















