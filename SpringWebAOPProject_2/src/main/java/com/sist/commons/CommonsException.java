package com.sist.commons;

import org.springframework.web.bind.annotation.ControllerAdvice;
/*
 		AOP : 공통 모듈 (공통으로 사용되는 소스를 모아서 관리)
 		      유지보수, 재사용
 		      => OOP에서 불가능한 것을 추가해서 보완
 		         ===
 		          | 자동 호출 (CallBack) => 불가능
 		      => Callback => 원하는 위치를 지정하면 호출 가능
 		      				 =========
 		      				 1. 메소드 지정 (지정된 메소드 => Target)
 		      				 2. 메소드의 위치 (JoinPoint)
 		      				    public void display()
 		      				    {
 		      				    	@Before
 		      				    	=> 설정된 함수 호출
 		      				    	try
 		      				    	{
 		      				    		=> 로그 / 트랜잭션 만들 때 Around 사용
 		      				    		============== @Around
 		      				    		 핵심
 		      				    		============== @Around
 		      				    	}catch(Exception e)
 		      				    	{
 		      				    		@AfterThrowing
 		      				    		=> @ControllerAdvice
 		      				    	}
 		      				    	finally
 		      				    	{
 		      				    		@After
 		      				    	}
 		      				    	return; => @AfterReturning
 		      				    }
 		      				 PointCut+JoinPoint => Advice
 		      				 Advice가 여러개 => 공통모듈 (Aspect)
 		=> PointCut : 어떤 메소드
 		=> JoinPoint : 메소드 호출 위치
 		=> Weaving : 묶어준다 (조립)
 		=> Target : 대상 메소드
 		=> Proxy : 조립된 메소드 호출 (대리자)
 		=> Advice : 공통모듈 단위
 		=> Aspect : 공통모듈
 		
 		public void getConnection(){}
 		public void disConnection(){}
 		public void success(){}
 		
 		class A
 		{
 			public void list()
 			{
 				getConnection();
 				==================
 				 select와 관련된 소스
 				==================
 				disConnection();
 				success();
 			}
 			public void insert()
 			{
 				getConnection();
 				==================
 				 insert와 관련된 소스
 				==================
 				disConnection();
 				success();
 			}
 			public void update()
 			{
 				getConnection();
 				==================
 				 update와 관련된 소스
 				==================
 				disConnection();
 				success();
 			}
 		}
 */
@ControllerAdvice
public class CommonsException {

}
