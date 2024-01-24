package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.*;

@Controller
public class FoodController {
	@Autowired // 다종 두입
	private FoodDAO dao;
	
	@RequestMapping("food/list.do")
	public String food_list(String page, Model model) // 매개변수로 받는다. => Model : jsp로 데이터를 보내는 전송 객체
	{						// 첫 페이지가 null값이므로 String으로 받아야한다.
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		List<FoodVO> list=dao.foodListData(start, end);
		
		// totalPage
		int totalpage=dao.foodTotalPage();
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
		return "food/list";
	}
	
	@RequestMapping("food/detail.do")
	public String food_detail(int fno, Model model) // Model : 전송객체 => spring에서는 request/response가 없어진다.
	{
		FoodVO vo=dao.foodDetailData(fno);
		model.addAttribute("vo", vo);
		/*
		 		class Model
		 		{
		 			private HttpServletRequest request
		 			public Model(HttpServletRequest request)
		 			{
		 				this.request=request;
		 			}
		 			public void addAttribute(String key, Object obj)
		 			{
		 				request.setAttribute(key, obj)
		 			}
		 		}
		 */
		return "food/detail";
	}
	
	// 화면이동은 .do를 줘야한다. (처리를 하지는 않고 데이터만 보내줌)
	// 처리는 RestController에서 한다.
	@RequestMapping("food/find.do")
	public String food_find()
	{
		return "food/find";
	}
}
