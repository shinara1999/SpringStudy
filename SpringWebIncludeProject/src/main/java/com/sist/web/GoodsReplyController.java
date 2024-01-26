package com.sist.web;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.dao.*;
import com.sist.vo.*;
import com.sist.mapper.*;

@Controller
public class GoodsReplyController {
	
	@Autowired
	private GoodsReplyDAO dao;
	
	// 댓글입력
	@PostMapping("goodsReply/reply_insert.do")
	public String reply_insert(int no, String msg, HttpSession session, RedirectAttributes ra)
	{
		GoodsReplyVO vo=new GoodsReplyVO();
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		vo.setNo(no);
		vo.setMsg(msg);
		vo.setId(id);
		vo.setName(name);
		dao.replyInsert(vo);
		ra.addAttribute("no", no);
		return "redirect:../goods/detail.do";
	}
	
	// 댓글 삭제
	@GetMapping("goodsReply/reply_delete.do")
	public String reply_delete(int rno, int no, RedirectAttributes ra)
	{
		dao.replyDelete(rno);
		ra.addAttribute("no", no);
		return "redirect:../goods/detail.do";
	}
	
	// 댓글 수정
	@PostMapping("goodsReply/reply_update.do")
	public String reply_update(int rno, int no, String msg, RedirectAttributes ra)
	{
		GoodsReplyVO vo=new GoodsReplyVO();
		vo.setRno(rno);
		vo.setMsg(msg);
		dao.replyUpdate(vo);
		ra.addAttribute("no", no);
		return "redirect:../goods/detail.do";
	}
}








