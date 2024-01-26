package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
/*
		*.do ======== DispacherServlet
							 | => preHandle() => 자동로그인 / ID저장
							 | HandlerMapping
				      @Controller / @RestController
				      		 | => postHandle()
				      		 | Model=request => ViewResolver
				      		 | => afterCompletion() => 권한
				      		JSP
		=> AOP
		   void aaa(); => Before
		   void bbb(); => AfterThrowing
		   void ccc(); => After
		   void ddd(); => After
		   
		   public String display()
		   {
		   		aaa();
		   		try
		   		{
		   			==============
		   			  핵심 소스
		   			==============
		   		}catch(Exception e)
		   		{
		   			bbb();
		   		}
		   		finally
		   		{
		   			ccc();
		   		}
		   		ddd();
		   		return "";
		   }
		   
		   JoinPoint : 호출 위치
		   PointCut : 대상 메소드
		   ==================== Advice
		   						====== 여러개 모아서 == Aspect
		   					    공통 모듈
		   => MVC
		       | DI, AOP => Annotation
 */

@Repository
public class ReplyDAO {
	@Autowired
	private ReplyMapper mapper;
	
	public void replyInsert(ReplyVO vo)
	{
		mapper.replyInsert(vo);
	}
	
	public List<ReplyVO> replyListData(int fno)
	{
		return mapper.replyListData(fno);
	}
	
	public void replyUpdate(ReplyVO vo)
	{
		mapper.replyUpdate(vo);
	}
	
	public void replyDelete(int no)
	{
		mapper.replyDelete(no);
	}
}
