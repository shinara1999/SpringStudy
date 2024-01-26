package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.sist.vo.*;
public interface SeoulMapper {
	/*
	 		-- 리스트 출력
	 		CREATE or REPLACE PROCEDURE seoulLocationData(
			    pStart NUMBER,
			    pEnd NUMBER,
			    pResult OUT SYS_REFCURSOR
			)
			IS
			BEGIN
			    OPEN pResult FOR
			        SELECT no, title, poster, msg, address, hit, num
			        FROM ( SELECT no, title, poster, msg, address, hit, rownum as num
			        FROM ( SELECT no, title, poster, msg, address, hit
			        FROM seoul_location ORDER BY no ASC))
			        WHERE num BETWEEN pStart AND pEnd;
			END;
			/
	 */
	@Select(value="{CALL seoulLocationData(#{pStart, mode=IN, javaType=java.lang.Integer}, #{pEnd, mode=IN, javaType=java.lang.Integer}, #{pResult, mode=OUT, jdbcType=CURSOR, resultMap=seoulMap})}")
	@Options(statementType = StatementType.CALLABLE)
	public List<SeoulVO> seoulListData(Map map);
	
	/*
			-- 총페이지
			CREATE or REPLACE PROCEDURE seoulLocationTotalPage(
			    pTotal OUT NUMBER
			)
			IS
			BEGIN
			    SELECT CEIL(COUNT(*)/12.0) INTO pTotal
			    FROM seoul_location;
			END;
			/
			
			<select id="seoulLocationTotalPage" resultType="int">
				SELECT CEIL(COUNT(*)/12.0) FROM seoul_location
			</select>
	 */
	@Select(value="{CALL seoulLocationTotalPage(#{pTotal, mode=OUT, jdbcType=INTEGER})}")
	@Options(statementType = StatementType.CALLABLE)
	public Integer seoulTotalPage(Map map);
	public int seoulLocationTotalPage();
	
	/*
			--  상세보기 (디테일)
			CREATE OR REPLACE PROCEDURE seoulLocationDetailData(
			    pNo seoul_location.no%TYPE,
			    pResult OUT SYS_REFCURSOR
			)
			IS
			BEGIN
			    OPEN pResult FOR
			        SELECT no, title, poster, msg, address, hit
			        FROM seoul_location
			        WHERE no=pNo;
			END;
			/
	 */
}
















