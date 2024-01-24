package com.sist.main;
/*
		@Autowired : 반드시 스프링에서 메모리 할당을 해야 자동 주입이 가능하다.
		
		class A
		{
			@Autowired		==> @Autowired에서 NullPoint오류가 뜨면 Annotation이 올라왔는지 확인해야 한다.
			B b; ==> null
		}
		@Component
		class B
		{
			@Autowired
			A a; ==> 주소
		}
 */
import java.util.*;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
public class MainClass3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("application-*.xml");
		FoodDAO fDao=(FoodDAO)app.getBean("fDao");
		Scanner scan=new Scanner(System.in);
		System.out.print("1. 업체명, 2. 주소, 3. 음식종류 선택: ");
		int col=scan.nextInt();
		String fd="";
		String[] temp= {"", "name", "address", "type"};
		fd=temp[col];
		System.out.print("검색어 입력:");
		String ss=scan.next();
		
		Map map=new HashMap();
		map.put("col_name", fd);
		map.put("ss", ss);
		
		List<FoodVO> list=fDao.foodFindData(map);
		
		for(FoodVO vo:list)
		{
			System.out.println(vo.getFno()+" "
		                      +vo.getName()+" "
		                      +vo.getAddress()+" "
		                      +vo.getType());
		}
	}

}




















