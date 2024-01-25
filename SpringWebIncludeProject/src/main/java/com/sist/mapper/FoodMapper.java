package com.sist.mapper;
/*
	1. web.xml
	   DispacherServlet 등록
	   한글변환
	    = 보안 등록
	    = 에러 처리
	2. 클래스 제작 => 메모리 할당 / 주입 (*****)
	3. Spring => xml코드 설정
	4. mapper => dao => model처리 (*****)
	5. jsp제작 (*****)
 */
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.FoodVO;
public interface FoodMapper {
	@Select("SELECT fno, name, poster, num "
			+"FROM (SELECT fno, name, poster, rownum as num "
			+"FROM (SELECT fno, name, poster "
			+"FROM food_menu_house ORDER BY fno ASC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM food_menu_house")
	public int foodTotalPage();
	
	// 조회수 증가
	@Update("UPDATE food_menu_house SET "
			+"hit=hit+1 "
			+"WHERE fno=#{fno}")
	public void hitIncrement(int fno);
	
	// 상세보기
	@Select("SELECT fno, score, poster, name, type, address,"
			+"phone, theme, price, time, seat, content "
			+"FROM food_menu_house "
			+"WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	
	// 검색 => 업체명 / 주소 / 음식종류
	@Select("SELECT fno, name, poster, num "
			+"FROM (SELECT fno, name, poster, rownum as num "
			+"FROM (SELECT fno, name, poster "
			+"FROM food_menu_house "
			+"WHERE ${col_name} LIKE '%'||#{ss}||'%'"
			+"ORDER BY fno ASC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodFindData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM food_menu_house "
			+"WHERE ${col_name} LIKE '%'||#{ss}||'%'") // ss : searchString
	public int foodFindTotalPage(Map map);
}













