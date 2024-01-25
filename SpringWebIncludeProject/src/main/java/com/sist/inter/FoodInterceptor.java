package com.sist.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*
					  FrontController : 요청(request)받아서 응답하는 역할
		main.do ===== DispacherServlet ===== HandlerMapping : Model 찾기 역할
											 | @GetMapping/@PostMapping => 기능 수행 역할 (Back-End)
											   ========================
											    프로그래머가 작성한다. (Model) 찾기
					   |						Model(Controller, Action)
					   |						  	    기능 제어	    동작
					     						 1) VO
					   							 2) DAO
					   							 3) Manager
					   							 4) Service
					   							 ----------- 다 모델이다.
					  preHandle		   => @GetMapping("main.do")
					  : 찾기 전 수행 메소드		 |
					  					   return "main/main";
					  					     | ---> postHandle
					  					   ViewResolver
					  					     | request
					  					     | ---> afterCompletion : 화면 처리 함수
					  					    JSP (Front-End)
 */
// <bean 이용> => Login
public class FoodInterceptor extends HandlerInterceptorAdapter{

	@Override
	// main.do 찾기 전 (모델 수행 전 : 자동로그인, 아이디 저장)
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("===== preHandle() Call... =====");
		return super.preHandle(request, response, handler);
	}

	@Override
	// ViewResolver로 넘어가기 전에 사용 (JSP에 request 전송하기 전)
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("===== postHandle() Call... =====");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	// JSP로 넘어가기 전
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("===== afterCompletion =====");
		super.afterCompletion(request, response, handler, ex);
	}
	
}
