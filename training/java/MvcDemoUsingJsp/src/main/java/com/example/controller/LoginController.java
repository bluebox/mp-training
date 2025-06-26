package com.example.controller;

import com.example.dao.UserDAO;
import com.example.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User loginUser = new User(username, password);
        boolean isValid = userDAO.validate(loginUser);

        if (isValid) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
            request.setAttribute("username", username);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
