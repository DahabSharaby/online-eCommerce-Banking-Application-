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

public class lodge extends HttpServlet {
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
		PreparedStatement lodge;
		try {
			lodge = connection.prepareStatement(
					
					"update userss set balance = balance + ? " + "where username = ?");
			
			// PASS in the values as parameters 
			lodge.setFloat(1, Float.valueOf(request.getParameter("lodge_amount")));
			lodge.setString(2, request.getParameter("username"));
			
			
			try {
				int rowsUpdated = lodge.executeUpdate();
				lodge.close();
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				out.println("<html><boby>Lodge Success !</boby></html>");
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			
			
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
