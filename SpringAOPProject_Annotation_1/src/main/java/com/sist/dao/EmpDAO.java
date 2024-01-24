package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
@Repository
public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	private MyDataSource ds;
	/*
		@Autowired
		=> 자동 주입
		public class A
		{
			private B b;
			public A(B b)
			{
			}
			public void setB(B b) => setter
			{
			}
			public void init(B b) => 일반 메소드
			{
			}
		}
	 */
	@Autowired
	public EmpDAO(MyDataSource ds) 
	{
		this.ds = ds;
		try
		{
			Class.forName(ds.getDriver());
		}catch(Exception e) {}
	}
	// AOP => before
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(ds.getUrl(), ds.getUsername(), ds.getPassword());
		}catch(Exception e) {}
	}
	// AOP => after
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception e) {}
	}
	
	// 전체 목록
	public List<EmpVO> empListData()
	{
		List<EmpVO> list=new ArrayList<EmpVO>();
		// before => getConnection
		try
		{
			// Around
			String sql="SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD'), sal "
					+"FROM emp";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				EmpVO vo=new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setSal(rs.getInt(5));
				list.add(vo);
			}
			rs.close();
			
			// Around
		}catch(Exception e)
		{
			
		}
		finally
		{
			
		}
		return list; // After-Returning
	}
	// 상세보기
	public EmpVO empDetailData(int empno)
	{
		EmpVO vo=new EmpVO();
		try
		{
			String sql="SELECT empno, ename, job,"
					+"TO_CHAR(hiredate, 'YYYY-MM'DD'), sal "
					+"FROM emp "
					+"WHERE empno="+empno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setEmpno(rs.getInt(1));
			vo.setEname(rs.getString(2));
			vo.setJob(rs.getString(3));
			vo.setDbday(rs.getString(4));
			vo.setSal(rs.getInt(5));
			rs.close();
		}catch(Exception e)
		{
			
		}
		finally
		{
			
		}
		return vo;
	}
}
















