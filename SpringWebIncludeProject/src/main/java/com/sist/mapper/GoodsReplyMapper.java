package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface GoodsReplyMapper {
	// 추가
	@SelectKey(keyProperty = "rno", resultType = int.class, before = true, statement = "SELECT NVL(MAX(rno)+1, 1) as rno FROM springGoodsReply")
	@Insert("INSERT INTO springGoodsReply VALUES("
			+"#{rno}, #{no}, #{id}, #{name}, #{msg}, SYSDATE)")
	public void replyInsert(GoodsReplyVO vo);
	
	// 목록
	@Select("SELECT rno, no, id, name, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday, msg "
			+"FROM springGoodsReply "
			+"WHERE no=#{no} "
			+"ORDER BY rno DESC")
	public List<GoodsReplyVO> replyListData(int no);
	
	// 수정
	@Update("UPDATE springGoodsReply SET "
			+"msg=#{msg} "
			+"WHERE rno=#{rno}")
	public void replyUpdate(GoodsReplyVO vo);
	
	// 삭제
	@Delete("DELETE FROM springGoodsReply "
			+"WHERE rno=#{rno}")
	public void replyDelete(int rno);
}
