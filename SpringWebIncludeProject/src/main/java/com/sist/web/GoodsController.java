package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.dao.GoodsDAO;
import com.sist.dao.GoodsReplyDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.GoodsReplyVO;
import com.sist.vo.GoodsVO;

@Controller
public class GoodsController {
		// 상품 리스트 출력
		@Autowired
		private GoodsDAO dao;
		
		// 댓글용
		@Autowired
		private GoodsReplyDAO gDao;
		
		@GetMapping("goods/main.do")
		public String goods_main(String page, Model model)
		{
			if(page==null)
				page="1";
			int curpage=Integer.parseInt(page);
			int rowSize=12;
			int start=(rowSize*curpage)-(rowSize-1);
			int end=rowSize*curpage;
			
			List<GoodsVO> list=dao.goodsListData(start, end);
			int totalpage=dao.goodsTotalPage();
			
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

			model.addAttribute("main_jsp", "list.jsp");
			return "goods/main";
		}	
		
		// 쿠키
		@GetMapping("goods/detail_before.do")
		public String goods_detail_before(int no, HttpServletResponse response, RedirectAttributes ra)
		{
			Cookie cookie=new Cookie("goods_"+no, String.valueOf(no));
			cookie.setPath("/");
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
			ra.addAttribute("no", no);
			return "redirect:../goods/detail.do";
		}
		
		// 상세보기
		@GetMapping("goods/detail.do")
		public String goods_detail(int no, Model model)
		{
			GoodsVO vo=dao.goodsDetailData(no);
			// 댓글
			List<GoodsReplyVO> gList=gDao.replyListData(no);
			model.addAttribute("gList", gList);
			model.addAttribute("vo", vo);
			model.addAttribute("main_jsp", "../goods/detail.jsp");
			return "goods/main";
		}
		
		// 검색
		@RequestMapping("goods/find.do") // get과 post 둘 다 사용해야 하므로 requestMapping 사용 (get: 페이지 나누기, post:form태그 submit검색버튼) 
		public String goods_find(String page, String colname, String ss, Model model) //  Model model: 데이터 전송해야 하므로 필요
		{
			if(colname==null)
				colname="goods_name"; // 타입 default
			if(ss==null)
				ss="특가"; // 상품명 default
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
			List<GoodsVO> list=dao.goodsFindData(map);
			
			int totalpage=dao.goodsFindTotalPage(map);
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

			model.addAttribute("main_jsp", "../goods/find.jsp");
			return "goods/main";
		}
}



















