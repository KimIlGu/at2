package com.sbs.kig.at.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("beforeActionInterceptor") // 컴포넌트 이름 설정
public class BeforeActionIntercepter implements HandlerInterceptor {
	@Autowired
	@Value("${custom.logoText}")
	private String siteName;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}