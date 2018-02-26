package com.src.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.src.pkg.geolocationresponse.Location;
import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * Servlet implementation class DatabaseConnection
 */
@WebServlet("/DatabaseConnection")
public class DatabaseConnection extends HttpServlet {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private static final long serialVersionUID = 1L;

	/**
	 * @throws Exception
	 * @see HttpServlet#HttpServlet()
	 */
	public DatabaseConnection() throws Exception {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter write = response.getWriter();
		if (request.getParameter("address") == null) {
			write.println(" Please Enter Your name");
		} else {
			String address = request.getParameter("address");
			String query ="SELECT * FROM Database_prof_to_buildingName.Directory_of_prof_nameOfBuilding WHERE NameOfProf='" + address+ "'; " ;
			//System.out.println(query);

			try {
				// This will load the MySQL driver, each DB has its own driver
				Class.forName("com.mysql.jdbc.Driver");
				// Setup the connection with the DB
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/Database_prof_to_buildingName?" + "user=root&password=Wannawin!1");

				// Statements allow to issue SQL queries to the database
				statement = (Statement) connect.createStatement();
				// Result set get the result of the SQL query
				resultSet = statement
						.executeQuery(query);
				
				
				// write.print("result = " +resultSet);
				String nameofBuildding = writeResultSet(resultSet);
				
				ServletToGetTheLocationOfBuilding locServlet = new ServletToGetTheLocationOfBuilding();
				Location loc = locServlet.getLatitudeAndLongitude(nameofBuildding);
				double latittude = loc.getLat();
				double longitude = loc.getLng();
				write.println("  " + latittude + "," + longitude);

			} catch (Exception e) {
				try {
					throw e;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	private String writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		String NameOfBuilding = "";
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			//int id = resultSet.getInt("id");
			//String nameOfProf = resultSet.getString("nameOfProf");
			 NameOfBuilding =NameOfBuilding + resultSet.getString("NameOfBuilding");

			//System.out.println("id: " + id);
			//System.out.println("nameOfProf: " + nameOfProf);
			System.out.println("NameOfBuilding: " + NameOfBuilding);
		}
		return NameOfBuilding;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
