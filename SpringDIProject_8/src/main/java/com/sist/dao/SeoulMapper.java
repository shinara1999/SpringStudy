package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface SeoulMapper {
	@Select("SELECT no, title, msg, address "
			+"FROM seoul_shop "
			+"ORDER BY no ASC")
	public List<SeoulVO> shopListData();
	
	@Select("SELECT no, title, address, msg "
			+"FROM seoul_shop "
			+"WHERE no=#{no}")
	public SeoulVO shopDetailData(int no);
	
	@Select("SELECT no, title, msg, address "
			+"FROM seoul_shop "
			+"WHERE title LIKE '%'||#{title}||'%'")
	public List<SeoulVO> shopFindListData(String title);
}
