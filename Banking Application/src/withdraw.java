import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class withdraw extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		Connection connection = null ;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bank3","root" , "DahabAshraf17");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		float with_amount = Float.valueOf(request.getParameter("withdraw_amount"));
		float B = 0;
		try {
			PreparedStatement qBalance = connection.prepareStatement("select balance from userss where username = ?");
			qBalance.setString(1, request.getParameter("username1"));
			ResultSet rs = qBalance.executeQuery();
			rs.next();
			B = rs.getFloat(1);
			qBalance.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (B - with_amount > 0) {
			PreparedStatement withdraw;
			try {
				withdraw = connection.prepareStatement(
						
						"update userss set balance = balance - ? " + "where username = ?");
				
				// PASS in the values as parameters 
				withdraw.setFloat(1, Float.valueOf(request.getParameter("withdraw_amount")));
				withdraw.setString(2, request.getParameter("username1"));
				
				
				try {
					int rowsUpdated = withdraw.executeUpdate();
					withdraw.close();
					PrintWriter out = response.getWriter();
					response.setContentType("text/html");
					out.println("<html><boby> Successfull withdraw !</boby></html>");
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<html><boby>insufficient funds !</boby></html>");
		}
		
	}

}
