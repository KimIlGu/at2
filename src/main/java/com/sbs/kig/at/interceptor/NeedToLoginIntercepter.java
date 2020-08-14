package com.sbs.kig.at.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("needToLoginInterceptor") // 컴포넌트 이름 설정
public class NeedToLoginIntercepter implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}