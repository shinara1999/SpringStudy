package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.GoodsReplyMapper;
import com.sist.vo.GoodsReplyVO;

@Repository
public class GoodsReplyDAO {
	@Autowired
	private GoodsReplyMapper mapper;
	
	public void replyInsert(GoodsReplyVO vo)
	{
		mapper.replyInsert(vo);
	}
	
	public List<GoodsReplyVO> replyListData(int no)
	{
		return mapper.replyListData(no);
	}
	
	public void replyUpdate(GoodsReplyVO vo)
	{
		mapper.replyUpdate(vo);
	}
	
	public void replyDelete(int rno)
	{
		mapper.replyDelete(rno);
	}
}
