package uw.webservices.destination;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

	public String GetAddressFromDatabase(String query, String resultColumn)
	{
		Connection connect = null;
		try {
            String url = "jdbc:sqlite:database.db";
            
            connect = DriverManager.getConnection(url);
		
			Statement statement = (Statement) connect.createStatement();
			
			ResultSet resultSet = statement
					.executeQuery(query);
			
			System.out.println(resultSet);
			
			return writeResultSet(resultSet, resultColumn);
		
		} catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connect != null) {
                		connect.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
		
		return null;
	}
	
	private String writeResultSet(ResultSet resultSet, String nameOfColumn) throws SQLException {
		
		String NameOfrestaurant = "";
		while (resultSet.next()) {
			
			NameOfrestaurant =NameOfrestaurant + resultSet.getString(nameOfColumn);

			System.out.println(nameOfColumn + ": " + NameOfrestaurant);
		}
		return NameOfrestaurant;
	}
	
}
