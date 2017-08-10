package com.myroniuk;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {

    protected abstract String getPathToHtml();
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        ServletContext servletContext = req.getServletContext();
        String pathToHtml = getPathToHtml();
        String realPath = servletContext.getRealPath(pathToHtml);

        PrintWriter printWriter = resp.getWriter();
        printWriter.println(getHtmlContent(realPath));
    }

    static private String getHtmlContent(String filePath) throws IOException {
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
