package com.library;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/BorrowBookServlet")
public class BorrowBookServlet extends HttpServlet {

protected void doGet(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException {

response.setContentType("text/html");
PrintWriter out=response.getWriter();

out.println("<html><head>");
out.println("<link rel='stylesheet' href='style.css'>");
out.println("</head><body>");

out.println("<div class='main'><div class='glass'>");

out.println("<h1>Borrow Book</h1>");

out.println("<form method='post'>");
out.println("<input type='number' name='id' placeholder='Enter Book ID' required>");
out.println("<button type='submit'>Borrow Now</button>");
out.println("</form><br>");

out.println("<a href='dashboard.html'><button>Back</button></a>");

out.println("</div></div></body></html>");
}

protected void doPost(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException {

try{

Connection con=DBConnection.getConnection();

PreparedStatement ps=con.prepareStatement(
"update books set quantity=quantity-1 where id=? and quantity>0");

ps.setInt(1,Integer.parseInt(request.getParameter("id")));

int i=ps.executeUpdate();

response.setContentType("text/html");
PrintWriter out=response.getWriter();

out.println("<html><head>");
out.println("<link rel='stylesheet' href='style.css'>");
out.println("</head><body>");

out.println("<div class='main'><div class='glass'>");

if(i>0)
out.println("<h1>Book Borrowed Successfully</h1>");
else
out.println("<h1>Book Not Available</h1>");

out.println("<a href='dashboard.html'><button>Back</button></a>");

out.println("</div></div></body></html>");

}catch(Exception e){
response.getWriter().println(e.getMessage());
}
}
}