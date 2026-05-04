package com.library;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {

protected void doGet(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException {

response.setContentType("text/html");
PrintWriter out=response.getWriter();

out.println("<html><head>");
out.println("<link rel='stylesheet' href='style.css'>");
out.println("</head><body>");

out.println("<div class='main'>");
out.println("<div class='glass'>");

out.println("<h1>Add Book</h1>");

out.println("<form method='post'>");
out.println("<input type='text' name='title' placeholder='Book Title' required>");
out.println("<input type='text' name='author' placeholder='Author Name' required>");
out.println("<input type='number' name='quantity' placeholder='Quantity' required>");
out.println("<button type='submit'>Add Book</button>");
out.println("</form><br>");

out.println("<a href='dashboard.html'><button>Back</button></a>");

out.println("</div></div></body></html>");
}

protected void doPost(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException {

try{
Connection con=DBConnection.getConnection();

PreparedStatement ps=con.prepareStatement(
"insert into books(title,author,quantity) values(?,?,?)");

ps.setString(1,request.getParameter("title"));
ps.setString(2,request.getParameter("author"));
ps.setInt(3,Integer.parseInt(request.getParameter("quantity")));

ps.executeUpdate();

response.sendRedirect("dashboard.html");

}catch(Exception e){
response.getWriter().println(e.getMessage());
}
}
}