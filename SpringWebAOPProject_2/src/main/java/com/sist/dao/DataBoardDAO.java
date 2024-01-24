package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.mapper.*;
// MVC 기초 => Jquery => Vue
/*
		Spring MVC
		  => 1. web.xml => WebServlet
		        DispacherServlet 등록 => 서블릿은 URL에 따라서 톰캣에 의해 호출
		        ================
		        | Servlet => init() => service => destroy
		        			   |
		        			 WebApplicationContext => 클래스 정보를 넘겨준다.
		        			 =====================
		        			 <init-param>
		        			 	<param-name>contextConfigLocation</param-name>
		        			 	<param-value>/WEB-INF/config/application-*.xml</param-value>
		        			 </init-param>
 */
@Repository
public class DataBoardDAO {
	@Autowired
	private DataBoardMapper mapper;
	
	public List<DataBoardVO> databoardListData(int start, int end)
	{
		return mapper.databoardListData(start, end);
	}
	public void databoardInsert(DataBoardVO vo)
	{
		mapper.databoardInsert(vo);
	}
	public int databoardTotalPage()
	{
		return mapper.databoardTotalPage();
	}
	public DataBoardVO databoardDetailData(int no)
	{
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}
}
