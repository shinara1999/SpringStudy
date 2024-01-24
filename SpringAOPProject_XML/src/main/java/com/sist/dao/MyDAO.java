package com.sist.dao;

public class MyDAO {
	public void getConnection()
	{
		System.out.println("오라클 연결");
	}
	public void disConnection()
	{
		System.out.println("오라클 해제");
	}
	public void select()
	{
		getConnection();
		System.out.println("SELECT문장 수행 요청");
		disConnection();
	}
	public void insert()
	{
		getConnection();
		System.out.println("INSERT문장 수행 요청");
		disConnection();
	}
	public void update()
	{
		getConnection();
		System.out.println("UPDATE문장 수행 요청");
		disConnection();
	}
	public void delete()
	{
		getConnection();
		System.out.println("DELETE문장 수행 요청");
		disConnection();
	}
}
