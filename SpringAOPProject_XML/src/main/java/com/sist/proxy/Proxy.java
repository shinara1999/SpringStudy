package com.sist.proxy;
/*
	AOP ==> 위빙은 메소드를 결합
	
	어떤 클래스의 어떤 메소드에 적용 => PointCut
	어떤 시점 => JoinPoint
	  => Before : try 수행 전
	  => After : finally
	  => After-Returning : 정상 수행시에 => 웹 전송
	  => After-Throwing : catch 수행 => 웹 (오류 발생)
	  => Around
	     로그 / 트랜잭션
	     = 로그
	       => 1. 시간, 호출 메소드 => setAutoCommit (false)
	             => 수행 문장
	          2. 시간 => commit()
	PointCut+JoinPoint => Advice
 */
public class Proxy {
	private Sawon sawon;
	public Proxy(Sawon sa)
	{
		this.sawon=sa;
	}
	// 위빙
	public void display()
	{
		System.out.println("비포처리");
		sawon.display();
		System.out.println("애프터처리");
	}
}
