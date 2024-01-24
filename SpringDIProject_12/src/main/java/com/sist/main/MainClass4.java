package com.sist.main;
import java.util.*;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
public class MainClass4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("application-*.xml");
		GoodsDAO gDao=(GoodsDAO)app.getBean("gDao");
		Scanner scan=new Scanner(System.in);
		System.out.print("1. 제품명, 2. 가격 선택: ");
		int col=scan.nextInt();
		String gd="";
		String[] temp= {"", "goods_name", "goods_price"};
		gd=temp[col];
		System.out.print("검색어 입력: ");
		String ss=scan.next();
		
		Map map=new HashMap();
		map.put("col_goods_name", gd);
		map.put("ss", ss);
		
		List<GoodsVO> list=gDao.goodsFindData(map);
		
		for(GoodsVO vo:list)
		{
			System.out.println(vo.getNo()+" "
					           +vo.getGoods_name()+" "
					           +vo.getGoods_price());
		}
	}

}
