package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
	
	private static final String query = "update PAYMENTDATA set CUSNAME=?,CUSADDRESS=?,CUSACCOUNT=?,TIME=?,DATE=? where id=?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get PrintWriter
				PrintWriter pw = resp.getWriter();
				//set content type
				resp.setContentType("text/html");
				//get the id of record
				int id = Integer.parseInt(req.getParameter("id"));
				//get the edit data we want to edit
				String cusName = req.getParameter("cusName");
				String cusAddress = req.getParameter("cusAddress");
				String cusAccount = req.getParameter("cusAccount");
				String time = req.getParameter("time");
				String date = req.getParameter("date");
				//LOAD jdbc driver
				try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				}catch(ClassNotFoundException cnf) {
					cnf.printStackTrace();
				}
				//generate the connection
				try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/management","root","y1o2h3a4n5@#");
						PreparedStatement ps = con.prepareStatement(query);){
					ps.setString(1, cusName);
					ps.setString(2, cusAddress);
					ps.setString(3, cusAccount);
					ps.setString(4, time);
					ps.setString(5, date);
					ps.setInt(6, id);
					int count = ps.executeUpdate();
					if(count==1) {
						pw.println("<h2>Record is Edited Successfully</h2>");
					}else {
						pw.println("<h2>Record is not Edited Successfully</h2>");
					}
				}catch(SQLException se) {
					se.printStackTrace();
					pw.println("<h1>"+se.getMessage()+"</h2>");
				}catch(Exception e) {
					e.printStackTrace();
					pw.println("<h1>"+e.getMessage()+"</h2>");
				}
				pw.println("<a href='Phome.html'>Home</a>");
				pw.println("<br>");
				pw.println("<a href='paymentList'>Payment List</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}
}
