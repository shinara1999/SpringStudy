package com.sist.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.FoodVO;

public interface FoodService {
	public List<FoodVO> foodListData(String type);
	public FoodVO foodDetailData(int fno);
}
