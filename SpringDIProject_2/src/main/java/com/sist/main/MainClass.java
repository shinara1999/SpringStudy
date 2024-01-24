package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.spring.Member;
import com.sist.spring.Sawon;

// 스프링이 관리하는 클래스가 아니다. (xml에 등록되지 않음.)
public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app1.xml");
		Sawon s=(Sawon)app.getBean("sa");
		System.out.println("사번:"+s.getSabun());
		System.out.println("이름:"+s.getName());
		System.out.println("성별:"+s.getSex());
		
		Member mem=(Member)app.getBean("mem");
		mem.print();
		
		Member mem1=(Member)app.getBean("mem1");
		mem1.print();
		
		Member mem2=(Member)app.getBean("mem2");
		mem2.print();
	}
}
