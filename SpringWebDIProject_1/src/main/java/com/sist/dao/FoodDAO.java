package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
/*
		Annotation : 구분자 => 메모리할당 대상인지 아닌지 구분한다.
		==========
		1. 메모리 할당을 해라. 하고 요청하는 => 선택적 어노테이션 => 클래스별 구분
		   										@Component
		        									|
		     ---------------------------------------------------------------------------------
		     |			  |			  |				|					|					 |
		@Repository   @Service	@Controller	 @RestController   @ControllerAdvice   @RestControllerAdvice
		=> DAO		  => BI		=> Model	 => Vue / React
		
		2. 주입 => DI
		   @Autowired : 자동 주입
		   @Inject
		3. 공통모듈 => AOP : @Aspectt => @Before, @After...
 */

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<FoodVO> foodListData(int start, int end)
	{
		return mapper.foodListData(start, end);
	}
	
	public int foodTotalPage()
	{
		return mapper.foodTotalPage();
	}
}
















