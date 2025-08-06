package com.jstl.examples;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyTag extends SimpleTagSupport{
	
	public void doTag() {
		JspWriter out= getJspContext().getOut();
		try {
			out.println("Hello I am a printer");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
