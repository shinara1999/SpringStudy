package com.sist.dao;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class DataBoardVO {
	private int no, hit, filecount;
	private String name, subject, content, pwd, filename, filesize, dbday;
	private Date regdate;
	private List<MultipartFile> files; // 업로드 된 파일 들어감
}
