package com.boombastic.primer;

import java.io.*;

import com.boombastic.config.DBConnection;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    DBConnection db = new DBConnection();

    public void init() {
        message = "Hello Pepe!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");


        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println(db.getConnection());
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}