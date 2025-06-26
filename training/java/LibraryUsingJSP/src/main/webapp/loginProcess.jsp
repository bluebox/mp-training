

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    // Dummy login check
    if("admin".equals(username) && "12345".equals(password)) {
        response.sendRedirect("home.jsp");
    } else {
        // Set error message
        request.setAttribute("error", "Invalid username or password.");

        // Forward back to login.jsp with error message
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }
%>

