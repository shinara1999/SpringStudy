package com.sist.web;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import java.io.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataBoardRestController {
	@Autowired
	private DataBoardDAO dao;
	
	@PostMapping("databoard/delete_ok.do") // 파일도 삭제해야 한다.
	public String databoard_delete(int no, String pwd, HttpServletRequest request)
	{
		String path=request.getSession().getServletContext().getRealPath("/")+"upload\\"; // 파일이 저장된 위치 (경로)
		path=path.replace("\\", File.separator);
		String result="";
		try
		{
			DataBoardVO vo=dao.databoardFileInfoData(no);
			boolean bCheck=dao.databoardDelete(no, pwd);
			if(bCheck==true)
			{
				result="yes"; // detail.jsp의 js에서 사용
				if(vo.getFilecount()>0)
				{
					StringTokenizer st=new StringTokenizer(vo.getFilename(), ",");
					while(st.hasMoreTokens())
					{
						File file=new File(path+st.nextToken());
						file.delete();
					}
				}
			}
			else
			{
				result="no"; // detail.jsp의 js에서 사용
			}
		}catch(Exception e) {}
		return result;
	}
	
	
}


















