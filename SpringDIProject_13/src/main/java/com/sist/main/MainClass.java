package com.sist.main;
/*
		DAO를 받을 때 => 자동주입
		DAO를 받을 때 => 직접받기
		===================== 어떤걸 할지 선택해야 한다.
 */
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// xml을 스프링 컨테이너에 전송
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		EmpDAO dao=(EmpDAO)app.getBean("empDAO");
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEmpno()+" "
					          +vo.getEname()+" "
					          +vo.getJob());
		}
	}

}
