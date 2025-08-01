package com.servlet;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
       
        PrintWriter out = res.getWriter();

        String name = req.getParameter("name");

      //  req.setAttribute("userName", name);
        res.sendRedirect("SecondServlet");
//        RequestDispatcher rd = req.getRequestDispatcher("second");
//        rd.include(req, res);
//        // rd.forward(req,res);
    }
}