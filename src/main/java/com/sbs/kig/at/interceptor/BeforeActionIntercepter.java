package com.sbs.kig.at.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbs.kig.at.dto.Member;
import com.sbs.kig.at.service.MemberService;
import com.sbs.kig.at.util.Util;

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
		Map<String, Object> param = Util.getParamMap(request);
		String paramJson = Util.toJsonStr(param);

		String requestUri = request.getRequestURI();
		String queryString = request.getQueryString();
		
		if (queryString != null && queryString.length() > 0) {
			requestUri += "?" + queryString;
		}

		String encodedRequestUri = Util.getUriEncoded(requestUri);
		
		request.setAttribute("requestUri", requestUri);
		request.setAttribute("encodedRequestUri", encodedRequestUri);

		String afterLoginUri = requestUri;

		// 현재 페이지가 이미 로그인 페이지라면, 이 상태에서 로그인 버튼을 눌렀을 때 기존 param의 redirectUri가 계속 유지되도록
		// 한다.
		if (requestUri.contains("/usr/member/login")) {
			afterLoginUri = Util.getString(request, "redirectUri", "");
		}

		String encodedAfterLoginUri = Util.getUriEncoded(afterLoginUri);

		request.setAttribute("afterLoginUri", afterLoginUri);
		request.setAttribute("encodedAfterLoginUri", encodedAfterLoginUri);

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
		 * requestUriQueryString (쿼리스트링 포함, 미포함) URL urlEncodedRequestUriQueryString
		 * (쿼리스트링 포함) URL param 쿼리스트링 paramJson Json 쿼리스트링 isAjax 일단 doWriteReplyAjax때는
		 * 무조건 true loginedMemberId 1 isLogined true loginedMember 관리자
		 */
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}