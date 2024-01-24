package com.sist.dao;
import java.util.*;

import org.mybatis.spring.support.SqlSessionDaoSupport;
public class DeptDAO extends SqlSessionDaoSupport{
	public List<DeptVO> deptListData()
	{
		return getSqlSession().selectList("deptListData");
	}
}
