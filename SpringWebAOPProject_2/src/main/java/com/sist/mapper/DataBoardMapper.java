package com.sist.mapper;

import java.util.Date;
import java.util.List;
/*
	private int no, hit, filecount;
	private String name, subject, content, pwd, filename, filesize, dbday;
	private Date regdate;
	private List<MultipartFile> files;
 */

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.dao.DataBoardVO;
public interface DataBoardMapper {
	// databoard list
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num "
			+"FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+"FROM (SELECT /*+ INDEX_DESC(springDataBoard sdb_no_pk)*/ no, subject, name, regdate, hit "
			+"FROM springDataBoard)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> databoardListData(@Param("start") int start, @Param("end") int end); // 두개의 변수는 Param 사용
	
	// 총페이지
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM springDataBoard")
	public int databoardTotalPage();
	
	// databoard insert
	@Insert("INSERT INTO springDataBoard VALUES("
			+"sdb_no_seq.nextval, #{name}, #{subject},"
			+"#{content}, #{pwd}, SYSDATE, 0, #{filename},"
			+"#{filesize}, #{filecount})")
	public void databoardInsert(DataBoardVO vo);
	
	// 조회수 증가
	@Update("UPDATE springDataBoard SET "
			+"hit=hit+1 "
			+"WHERE no=#{no}")
	public void hitIncrement(int no);
	
	// 실제데이터 가져오기 (상세보기)
	@Select("SELECT no, name, subject, content,"
			+"TO_CHAR(regdate, 'YYYY-MM-DD') as dbday,"
			+"hit, filename, filesize, filecount "
			+"FROM springDataBoard "
			+"WHERE no=#{no}")
	public DataBoardVO databoardDetailData(int no);
}












