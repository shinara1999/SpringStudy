package com.sist.mapper;
import java.util.*;
import com.sist.dao.*;
public interface EmpMapper {
	// <select id="empAllData" resultType="empMap">
	public List<EmpVO> empAllData();
	
	// <select id="empDetailData" resultMap="empMap" parameterType="int">
	public EmpVO empDetailData(int empno);
	
}
