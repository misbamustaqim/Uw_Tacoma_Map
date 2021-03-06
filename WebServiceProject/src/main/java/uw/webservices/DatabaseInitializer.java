package uw.webservices;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DatabaseInitializer {

	private final static String databaseFileName = "database.db";
	
	private static void createNewDatabase(String fileName) {
		 
        String url = "jdbc:sqlite:" + fileName;
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	private static void executeDDLStatement(String fileName, String sql) {
        // SQLite connection string
		String url = "jdbc:sqlite:" + fileName;
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            
            //conn.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	private static void insertDataIntoTable(String name, String address, String tableName)
	{
		String sql = "REPLACE INTO " + tableName + " (name,address) VALUES(?,?)";
		 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.executeUpdate();
            
            System.out.println("data inserted: " + sql);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally
        {
        		
        }
	}
	
	private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:" + databaseFileName;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	
	public static void initializeDatabase()
	{
		createNewDatabase(databaseFileName);
		
		String queryToCreateFacultyTable = "CREATE TABLE IF NOT EXISTS faculty (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL UNIQUE,\n"
                + "	address text NOT NULL\n"
                + ");";
		
		executeDDLStatement(databaseFileName, queryToCreateFacultyTable);
		
		String queryToCreateRestaurantTable = "CREATE TABLE IF NOT EXISTS restaurant (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL UNIQUE, \n"
                + "	address text NOT NULL\n"
                + ");";
		
		executeDDLStatement(databaseFileName, queryToCreateRestaurantTable);
		
		HashMap<String, String> profAddresses = new HashMap<String, String>();
		profAddresses.put("Wes Loyyd", "Cherry Parkes");
		profAddresses.put("Mohamed Ali", "Cherry Parkes");
		profAddresses.put("Martine De Cock", "McDonald Smith");
		profAddresses.put("Ankur Suri", "Cherry Parkes");
		profAddresses.put("Menaka Abraham", "Cherry Parkes");
		profAddresses.put("Eyhab Al-Masri", "Cherry Parkes");
		profAddresses.put("Paulo Barreto", "Cherry Parkes");
		profAddresses.put("Ka Yee Yeung", "Cherry Parkes");
		profAddresses.put("Ankur Teredesai", "McDonald Smith");
		profAddresses.put("Orlando Baiocchi", "Cherry Parkes");
		profAddresses.put("Charles Bryani", "Cherry Parkes");
		profAddresses.put("Donald Chinn", "Cherry Parkes");
		profAddresses.put("Charles Costarella", "Cherry Parkes");
		profAddresses.put("Alan Fowlera", "Cherry Parkes");
		profAddresses.put("Chunming Gao", "Cherry Parkes");
		profAddresses.put("Bryan Goda", "Cherry Parkes");
		profAddresses.put("DC Grant", "Cherry Parkes");
		profAddresses.put("Ingrid Horakova", "McDonald Smith");
		profAddresses.put("Rajendra Katti", "Cherry Parkes");
		profAddresses.put("Raza-Ali Kazmi", "Cherry Parkes");
		profAddresses.put("Massimiliano Laddomada", "Cherry Parkes");
		profAddresses.put("Chris Marriott", "Cherry Parkes");
		profAddresses.put("Michael McCourt", "Cherry Parkes");
		profAddresses.put("George Mobus", "McDonald Smith");
		profAddresses.put("Anderson Nascimentot", "Cherry Parkes");
		profAddresses.put("Samah Saeed", "Cherry Parkes");
		profAddresses.put("Raghavi Sakpal", "Cherry Parkes");
		profAddresses.put("David Schuesslert", "Cherry Parkes");
		profAddresses.put("Jie Sheng", "Cherry Parkes");
		profAddresses.put("Monika Sobolewska", "Cherry Parkes");
		profAddresses.put("Josh Tenenberg", "Cherry Parkes");
		profAddresses.put("James West", "Cherry Parkes");
		profAddresses.put("Matthew Tolentino", "Cherry Parkes");
		profAddresses.put("Asbjornsen, Samantha", "KeyStone");
		profAddresses.put("Hawes, Janelle", "West Coast Grocery");
		profAddresses.put("Curtis Black", "Cherry Parkes");
		
		for (Map.Entry<String, String> kvp : profAddresses.entrySet()) {
			insertDataIntoTable(kvp.getKey(), kvp.getValue(), "faculty");
		}
		
		HashMap<String, String> restroAddreses = new HashMap<String, String>();
		restroAddreses.put("Starbucks", "1748 Pacific Ave");
		restroAddreses.put("Abella Pizzeria", "1946 Pacific Ave");
		restroAddreses.put("The Swiss Restaurant & Pub", "1904 Jefferson Ave");
		restroAddreses.put("THEKOI Japanese Cuisine", "1552 Commerce St #100");
		restroAddreses.put("Indochine Asian Dining Lounge", "11924 Pacific Ave");
		restroAddreses.put("Harmon Brewery & Eatery", "1938 Pacific Ave");
		restroAddreses.put("Savor Tacoma Creperie", "1916 Pacific Ave");
		restroAddreses.put("Pho Than Brothers", "1712 Pacific Ave");
		restroAddreses.put("Elemental Pizza", "11702 Pacific Ave");
		restroAddreses.put("Pacific Grill", "1502 Pacific Ave");
		restroAddreses.put("The Melting Pot", "2121 Pacific Ave");
		restroAddreses.put("Renaissance Cafe", "1746 Pacific Ave");
		restroAddreses.put("SUBWAY@Restaurants", "1910 Pacific Ave");
		restroAddreses.put("The Social Bar and Grill", "1715 Dock St");
		
		for (Map.Entry<String, String> kvp : restroAddreses.entrySet()) {
			
			insertDataIntoTable(kvp.getKey(), kvp.getValue(), "restaurant");
		}
	}
}
