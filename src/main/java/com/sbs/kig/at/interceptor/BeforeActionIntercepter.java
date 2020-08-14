package com.sbs.kig.at.interceptor;

import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbs.kig.at.dto.Member;
import com.sbs.kig.at.service.MemberService;

@Component("beforeActionInterceptor") // 컴포넌트 이름 설정
public class BeforeActionIntercepter implements HandlerInterceptor {
	@Autowired
	@Value("${custom.logoText}")
	private String siteName;

	@Autowired
	private MemberService memberService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 기타 유용한 정보를 request에 담는다.
		Map<String, Object> param = new HashMap<>();

		Enumeration<String> parameterNames = request.getParameterNames();
		
		// 쿼리스트링을 param에 저장한다.
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			Object paramValue = request.getParameter(paramName);
			param.put(paramName, paramValue);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		// Json 쿼리스트링
		String paramJson = mapper.writeValueAsString(param);
		// URI
		String requestUri = request.getRequestURI();
		// 쿼리스트링
		String queryString = request.getQueryString();

		String requestUriQueryString = requestUri;
		// 쿼리스트링이 존재할 경우
		if (queryString != null && queryString.length() > 0) {
			requestUriQueryString += "?" + queryString;
		}
		// 그와중에 꿀팁 : Alt + shift + R : 동일한 변수 동시 수정 
		
		// uri + queryString 인코딩
		String encodedRequestUriQueryString = URLEncoder.encode(requestUriQueryString, "UTF-8");
		
		// 2가지 경우 : 1. 쿼리스트링이 존재하지 않을 경우 2. 쿼리스트링이 존재할 경우
		request.setAttribute("requestUriQueryString", requestUriQueryString);
		
		// 1가지 경우 : 쿼리스트링이 존재하는데 인코딩한 경우
		request.setAttribute("urlEncodedRequestUriQueryString", encodedRequestUriQueryString);
		
		// 맵 형식으로 쿼리스트링을 저장한 Param
		request.setAttribute("param", param);
		
		// Json 형식으로 쿼리스트링을 저장한 ParamJson
		request.setAttribute("paramJson", paramJson);
		
		// uri가 ajax로 끝날 경우
		boolean isAjax = requestUri.endsWith("Ajax");

		// url가 ajax로 끝나지 않을 경우
		if (isAjax == false) {
			// 쿼리스트링 key가 ajax가 있을 경우 그리고 쿼리스트링 key가 ajax인 값을 가져와서 Y일 경우
			if (param.containsKey("ajax") && param.get("ajax").equals("Y")) {
				isAjax = true;
			}
		}
		
		request.setAttribute("isAjax", isAjax);

		// 설정 파일에 있는 정보를 request에 담는다.
		request.setAttribute("logoText", this.siteName);
		HttpSession session = request.getSession();

		// 임시작업
		session.setAttribute("loginedMemberId", 1);

		// 로그인 여부에 관련된 정보를 request에 담는다.
		boolean isLogined = false;
		int loginedMemberId = 0;
		Member loginedMember = null;

		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			isLogined = true;
			loginedMember = memberService.getMemberById(loginedMemberId);
		}

		request.setAttribute("loginedMemberId", loginedMemberId);
		request.setAttribute("isLogined", isLogined);
		request.setAttribute("loginedMember", loginedMember);
		
		// BeforeActionIntercepter를 거친 이후 설정
		
		/*
			requestUriQueryString (쿼리스트링 포함, 미포함) URL
			urlEncodedRequestUriQueryString (쿼리스트링 포함) URL
			param 쿼리스트링 
			paramJson Json 쿼리스트링 
			isAjax 일단 doWriteReplyAjax때는 무조건 true        
			loginedMemberId 1
			isLogined true
			loginedMember 관리자
		*/
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}