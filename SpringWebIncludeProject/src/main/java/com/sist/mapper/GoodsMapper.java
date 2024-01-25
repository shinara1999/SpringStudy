package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface GoodsMapper {
	/*
	private int no, goods_discount, hit;
	private String goods_name, goods_sub, goods_price, goods_first_price, 
	               goods_delivery, goods_poster;
	 */
	
	// 리스트 출력
	@Select("SELECT no, goods_name, goods_price, goods_poster, num "
			+"FROM (SELECT no, goods_name, goods_price, goods_poster, rownum as num "
			+"FROM (SELECT no, goods_name, goods_price, goods_poster "
			+"FROM goods_all ORDER BY no ASC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsListData(@Param("start") int start, @Param("end") int end);
	// 리스트 총페이지
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all")
	public int goodsTotalPage();
	
	// 조회수 증가
	@Update("UPDATE goods_all SET "
			+"hit=hit+1 "
			+"WHERE no=#{no}")
	public void hitIncrement(int no);
	
	// 상세보기
	@Select("SELECT no, goods_name, goods_price, goods_poster,"
			+"goods_discount, goods_sub, goods_first_price,"
			+"goods_delivery "
			+"FROM goods_all "
			+"WHERE no=#{no}")
	public GoodsVO goodsDetailData(int no);
	
	// 검색
	@Select("SELECT no, goods_name, goods_price, goods_poster, num "
			+"FROM (SELECT no, goods_name, goods_price, goods_poster, rownum as num "
			+"FROM (SELECT no, goods_name, goods_price, goods_poster "
			+"FROM goods_all "
			+ "WHERE ${col_name} LIKE '%'||#{ss}||'%'"
			+ "ORDER BY no ASC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsFindData(Map map);
	// 리스트 총페이지
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all "
			+"WHERE ${col_name} LIKE '%'||#{ss}||'%'")
	public int goodsFindTotalPage(Map map);
}


