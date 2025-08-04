package com.JspIntro.customTags;

import java.io.IOException;

import com.JspIntro.model.UserDetails;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

public class User extends TagSupport {
	private static final long serialVersionUID = 1L;

	private UserDetails details = null;

	public User(UserDetails details) {
		this.details = details;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.details = userDetails;
	}

	public User() {

	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		if (details != null) {
			try {
				out.println(details.getName());
				out.println(details.getEmail());
				out.println(details.getPhonenumber());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				out.println("User details not found");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return SKIP_BODY;
	}

}
