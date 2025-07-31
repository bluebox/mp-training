package com.medplus.marketing.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.common.cas.model.UserDetails;
import com.medplus.common.utility.UtilValidate;
import com.medplus.reactcomponents.core.domain.form.Response;
import com.medplus.reactcomponents.core.domain.form.Response.StatusCode;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

	@Autowired
	HttpSessionCsrfTokenRepository csrfTokenRepository;
	
	@GetMapping("/user-login-details")
	public Response getLoginDetails(HttpServletRequest request, HttpServletResponse response) {
		log.info("getting user deatils");
		Map<String, Object> res = new HashMap<>();
 		request.getSession().setAttribute("user-agent", request.getHeader("User-Agent"));
		UserDetails userInfo = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(UtilValidate.isEmpty(userInfo) ||  UtilValidate.isEmpty(userInfo.getUserId())) {
			return new Response(StatusCode.FAILURE, "No Logged-in User available or User detail not found");
		}
		CsrfToken token = csrfTokenRepository.loadToken(request);
		res.put("userDetails", userInfo);
		res.put("tokenDetails", token);
		return new Response(StatusCode.SUCCESS, "", res);
	}
}
