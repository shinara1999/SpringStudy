package com.sist.mapper;
import java.util.*;

import com.sist.dao.GoodsVO;
public interface GoodsMapper {
	public List<GoodsVO> goodsFindData(Map map);
}
