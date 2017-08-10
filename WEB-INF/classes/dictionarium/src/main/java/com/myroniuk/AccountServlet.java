package com.myroniuk;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter printWriter = resp.getWriter();
        printWriter.println(
            "<html>\n"
            + "<head></head>\n"
            + "<body>\n"
            + "<br>User name: " + req.getParameter("username") + "\n"
            + "<br>Password: " + req.getParameter("password") + "\n"
            + "</body>\n"
            + "</html>");
    }
}
