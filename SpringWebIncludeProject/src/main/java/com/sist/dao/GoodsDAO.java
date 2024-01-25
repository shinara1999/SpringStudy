package com.sist.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper;
	
	public List<GoodsVO> goodsListData(int start, int end)
	{
		return mapper.goodsListData(start, end);
	}
	public int goodsTotalPage()
	{
		return mapper.goodsTotalPage();
	}
	public GoodsVO goodsDetailData(int no)
	{
		mapper.hitIncrement(no);
		return mapper.goodsDetailData(no);
	}
	public GoodsVO goodsCookieData(int no)
	{
		return mapper.goodsDetailData(no);
	}
	// 검색
	public List<GoodsVO> goodsFindData(Map map)
	{
		return mapper.goodsFindData(map);
	}
	public int goodsFindTotalPage(Map map)
	{
		return mapper.goodsFindTotalPage(map);
	}
}
