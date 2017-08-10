package com.myroniuk;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        ServletContext servletContext = req.getServletContext();
        String realPath = servletContext.getRealPath("/views/index.html");

        PrintWriter printWriter = resp.getWriter();
        printWriter.println(getHtml(realPath));
    }

    static private String getHtml(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader buffReader = new BufferedReader(fileReader);
        
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String line = buffReader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
                line = buffReader.readLine();
            }
            
            return stringBuilder.toString();
        } finally {
            buffReader.close();
        }
    }
}
