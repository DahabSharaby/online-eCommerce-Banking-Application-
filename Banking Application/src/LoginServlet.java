import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.Result;

public class LoginServlet extends HttpServlet {
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
		
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = statement.executeQuery("select username,password from users");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(rs.next()) {
				if (rs.getString(1).equalsIgnoreCase(request.getParameter("username"))&&
						(rs.getString(2).equalsIgnoreCase(request.getParameter("password"))));
				/*PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				out.println("<html><boby>YOU HAVE LOGGED IN !</boby></html>");*/
				
								}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("Bank_home.html");
		

		}
		

}
