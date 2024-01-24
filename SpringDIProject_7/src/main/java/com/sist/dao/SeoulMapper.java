package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface SeoulMapper {
	@Select("SELECT no, title, msg, address "
			+"FROM seoul_nature "
			+"ORDER BY no ASC")
	public List<SeoulVO> natureListData();
	
	@Select("SELECT no, title, address, msg "
			+"FROM seoul_nature "
			+"WHERE no=#{no}")
	public SeoulVO natureDetailData(int no);
	
	@Select("SELECT no, title, msg, address "
			+"FROM seoul_nature "
			+"WHERE title LIKE '%'||#{title}||'%'")
	public List<SeoulVO> natureFindListData(String title);
}
