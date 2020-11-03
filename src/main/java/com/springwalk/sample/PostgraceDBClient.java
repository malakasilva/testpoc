package com.springwalk.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PostgraceDBClient {

	
	private static final Logger LOGGER = Logger.getLogger(PostgraceDBClient.class.getName());
	private static final String WATER_MARK = "WATER_MARK";
	
	private String dbUrl;
	
	
	public PostgraceDBClient(String dbUrl) {
		this.dbUrl = dbUrl;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, "Unable to make a Database connection. Class not found." ,e);			
		}
		createMataTable();
	}
		

	
	public void setLastPageNumber(int pageNumber) {
		PreparedStatement stmt = null;
		Connection dbConnection = null;
		try {
			dbConnection = DriverManager.getConnection(dbUrl);
			// Start transaction
			dbConnection.setAutoCommit(true);
			// insert error data table
			stmt = dbConnection.prepareStatement(getPageUpdateStatement());
			stmt.setInt (1, pageNumber);				
			//Take current timestamp from server
			stmt.setTimestamp(2, new Timestamp((new Date()).getTime()));
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE, "Error inserting watermark table : ");
		} finally {
			try {
				dbConnection.close();
			} catch (Exception e) {}			
			try {
				stmt.close();
			} catch (Exception e) {}
			try {
				dbConnection.close();
			} catch (Exception e) {	}
		}
	}
	
	public Integer getLastPageNumber() {
		Statement stmt = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Connection dbConnection = null;
		try {
			dbConnection = DriverManager.getConnection(dbUrl);
			stmt = dbConnection.createStatement();
			rs = stmt.executeQuery("select lastpagenumber from " + WATER_MARK );
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				preparedStatement = dbConnection.prepareStatement(getPageInsertStatement());
				preparedStatement.setInt (1, 0);				
				//Take current timestamp from server
				preparedStatement.setTimestamp(2, new Timestamp((new Date()).getTime()));
				preparedStatement.executeUpdate();
				return 0;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Error getting the watermark");
		} finally {
			try {
				dbConnection.close();
			} catch (Exception e) {}			
			try {
				preparedStatement.close();
			} catch (Exception e) {}			
			try {
				stmt.close();
			} catch (Exception e) {}
			try {
				rs.close();
			} catch (Exception e) {}
		}
		return null;
	}

	private boolean createMataTable(){
		Connection dbConnection = null;
		try {
			dbConnection = DriverManager.getConnection(dbUrl);
			Statement stmt = dbConnection.createStatement();
			stmt.executeUpdate(getCreateMetaTableQuery());
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error creating meta-data table", e);
			return false;
		} finally {
			try {
				dbConnection.close();
			} catch (Exception e) {}			
		}
	}	
	

	
	private String getPageInsertStatement() {
		return "insert into  " + WATER_MARK + " (lastpagenumber,lastupdatedtimestamp) values (?,?)";
	}	

	private String getPageUpdateStatement() {
		return "update " + WATER_MARK + " set lastpagenumber = ?, lastupdatedtimestamp = ?";
	}	

	private String getCreateMetaTableQuery() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("CREATE TABLE IF NOT EXISTS " + WATER_MARK);
		sb.append(" ( lastpagenumber integer ");
		sb.append(",lastupdatedtimestamp timestamp);");		
		return sb.toString();
	}	
}

