package com.vejas.examples.jsp;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CustomTag extends SimpleTagSupport{
	StringWriter writer=new StringWriter();
	@Override
	public void doTag() throws JspException, IOException {
		getJspBody().invoke(writer);
		//writer.append("<3");
		getJspContext().getOut().println(writer.toString());
	}
}
