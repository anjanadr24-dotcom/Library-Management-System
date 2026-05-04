package com.library;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ViewBookServlet")
public class ViewBookServlet extends HttpServlet {

protected void doGet(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException {

response.setContentType("text/html");
PrintWriter out=response.getWriter();

out.println("<html><head>");
out.println("<link rel='stylesheet' href='style.css'>");
out.println("</head><body>");

out.println("<div class='main'><div class='glass'>");
out.println("<h1>Books List</h1>");

try{
Connection con=DBConnection.getConnection();

PreparedStatement ps=con.prepareStatement("select * from books");
ResultSet rs=ps.executeQuery();

out.println("<table>");
out.println("<tr><th>ID</th><th>Title</th><th>Author</th><th>Qty</th></tr>");

while(rs.next()){
out.println("<tr>");
out.println("<td>"+rs.getInt(1)+"</td>");
out.println("<td>"+rs.getString(2)+"</td>");
out.println("<td>"+rs.getString(3)+"</td>");
out.println("<td>"+rs.getInt(4)+"</td>");
out.println("</tr>");
}

out.println("</table>");

}catch(Exception e){
out.println(e.getMessage());
}

out.println("<br><a href='dashboard.html'><button>Back</button></a>");
out.println("</div></div></body></html>");
}
}