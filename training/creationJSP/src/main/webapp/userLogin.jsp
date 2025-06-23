<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	
	<%!
		private int a;
		private int b;
		/*public int getValueOfA(){
			return a;
		}
		public int getValueOfB(){
			return b;
		}
		public void setValueOfA(int a){
			this.a = a;
		}
		public void setValueOfB(int b){
			this.b = b;
		}*/
	%>
	
	<%
		a = Integer.parseInt(request.getParameter("number1"));
		b = Integer.parseInt(request.getParameter("number2"));
		
		request.setAttribute("a", a);
		request.setAttribute("b",b);
		RequestDispatcher rd = request.getRequestDispatcher("/addNumbers");
		rd.forward(request, response);
	%>
	
</body>
</html>