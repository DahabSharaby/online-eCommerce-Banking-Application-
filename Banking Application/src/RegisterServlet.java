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

public class RegisterServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		Connection connection = null ;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank3","root" , "DahabAshraf17");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PreparedStatement createUser;
		try {
			createUser = connection.prepareStatement(
					
					"INSERT into userss"
					+ "(username , password)" + "VALUES (?,?)");
			
			// PASS in the values as parameters 
			createUser.setString(1, request.getParameter("username"));
			createUser.setString(2, request.getParameter("password"));
			//createUser.setString(3, request.getParameter("password"));
			
			//int rowsUpdated = createUser.executeUpdate();
			String pass= request.getParameter("password");
			String pass1= request.getParameter("passverfiy");
			if(pass.equals(pass1)) {
				int rowsUpdated = createUser.executeUpdate();
				createUser.close();
				response.sendRedirect("login.html");
			}
			else {
				response.sendRedirect("index.html");
			}
			
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
 	
	public static void main(String[]args) throws Exception {
		
		new RegisterServlet();
	}	

	
}


