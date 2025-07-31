package com.medplus.marketing.handler;

import java.time.format.DateTimeParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.medplus.discounts.PromotionException;
import com.medplus.discounts.PromotionHandlingException;
import com.medplus.marketing.exception.MarketingException;
import com.medplus.marketing.helper.MarketingResponseHelper;
import com.medplus.partner.exception.PartnerException;
import com.medplus.reactcomponents.core.domain.form.Response;
import com.medplus.reactcomponents.core.domain.form.Response.StatusCode;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class MarketingExceptionHandler {
	
	@ExceptionHandler(MarketingException.class)
	public ResponseEntity<Response> exceptionHandler(HttpServletRequest request, MarketingException ex) {
		log.error(ex.getMessage(),ex);
		return MarketingResponseHelper.sendErrorResponse(StatusCode.FAILURE, ex.getMessage(),HttpStatus.OK);
	}
	
	@ExceptionHandler(MissingServletRequestPartException.class)
	public ResponseEntity<Response> exceptionHandler(HttpServletRequest request, MissingServletRequestPartException ex) {
		log.error(ex.getMessage(),ex);
		return MarketingResponseHelper.sendErrorResponse(StatusCode.FAILURE, "Missing Request Part file "+ex.getRequestPartName(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Response> missingParameterExceptionHandler(HttpServletRequest request, MissingServletRequestParameterException ex) {
		log.error("Missing param : {}, param type : {}, url : {}, IP: {}", ex.getParameterName(), ex.getParameterType(), request.getRequestURI(),request.getRemoteAddr());
		log.error(ex.getMessage(),ex);
		return MarketingResponseHelper.sendErrorResponse(StatusCode.FAILURE, "Missing Parameter "+ex.getParameterName(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PartnerException.class)
	public ResponseEntity<Response> exceptionHandler(HttpServletRequest request, PartnerException ex) {
		log.error(ex.getMessage(),ex);
		return MarketingResponseHelper.sendErrorResponse(StatusCode.FAILURE, ex.getMessage(),HttpStatus.OK);
	}
	
	@ExceptionHandler(PromotionException.class)
	public ResponseEntity<Response> exceptionHandler(HttpServletRequest request, PromotionException ex) {
		log.error(ex.getMessage(),ex);
		return MarketingResponseHelper.sendErrorResponse(StatusCode.FAILURE, ex.getMessage(),HttpStatus.OK);
	}
	
	@ExceptionHandler(PromotionHandlingException.class)
	public ResponseEntity<Response> exceptionHandler(HttpServletRequest request, PromotionHandlingException ex) {
		log.error(ex.getMessage(),ex);
		return MarketingResponseHelper.sendErrorResponse(StatusCode.FAILURE, ex.getMessage(),HttpStatus.OK);
	}
	
	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<Response> exceptionHandler(HttpServletRequest request, DateTimeParseException ex) {
		log.error(ex.getMessage(),ex);
		return MarketingResponseHelper.sendErrorResponse(StatusCode.FAILURE, ex.getMessage(),HttpStatus.OK);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> genericExceptionHandler(HttpServletRequest request, Exception ex) {
		log.error("Generic Exception msg : {}, url :{}, IP: {}", ex.getMessage(), request.getRequestURI(), request.getRemoteAddr());
		log.error(ex.getMessage(),ex);
		return MarketingResponseHelper.sendErrorResponse(StatusCode.FAILURE, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Response> exceptionHandler(HttpServletRequest request, AccessDeniedException ex) {
		log.error(ex.getMessage(),ex);
		return MarketingResponseHelper.sendErrorResponse(StatusCode.FAILURE, "Access Denied. User Doesn't have Required Roles", HttpStatus.UNAUTHORIZED);
	}

}
