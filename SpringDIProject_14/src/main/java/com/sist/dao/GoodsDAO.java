package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;

@Repository("gDao") // 1. 메모리 할당 후
public class GoodsDAO {
	@Autowired // 2. 구현된 객체를 넣어준다.
	private GoodsMapper mapper; 
	
	public List<GoodsVO> goodsListData(Map map)
	{
		return mapper.goodsListData(map);
	}
	public GoodsVO goodsDetailData(Map map)
	{
		return mapper.goodsDetailData(map);
	}
	
}
