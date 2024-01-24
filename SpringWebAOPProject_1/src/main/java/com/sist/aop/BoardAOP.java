package com.sist.aop;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;
import com.sist.dao.*;
/*
		1. 어떤 클래스의 메소드에서 적용
		   PointCut
		     => execution("특정위치 지정")
		     => within("패키지명")
		2. 메소드 위치
		   public String display()
		   {
		    before();
		   	try
		   	{
		   	}catch(Exception e)
		   	{
		   	  afterThrowing()
		   	}
		   	finally
		   	{
		   	  after();
		   	}
		   	afterReturning()
		   	return "";
		   }
		   
		   예)
		   @Before
		    public void before()
		    {
		    }
		   @After
		    public void after()
		    {
		    }
		   @AfterReturning
		    public void afterReturning()
		    {
		    }
		   @AfterThrowing
		    public void afterThrowing()
		    {
		    }
 */
@Aspect
@Component
public class BoardAOP {
	@Autowired
    private BoardDAO dao;
	
	@After("execution(* com.sist.web.*Controller.*(..))")
	public void after()
	{
		List<BoardVO> list=dao.boardTop5();
		// 실제 사용중인 request를 가지고 올 때 사용 => Cookie
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.setAttribute("tList", list);
	}
}