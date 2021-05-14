package com;

import java.sql.*;

public class Fund {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gb", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String readFunds() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			
			output = "<table class='table table-hover'><tr><th>Funding Agency</th><th>Address</th><th>Phone</th>"
					+ "<th>Project ID</th><th>Fund</th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from funds";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String FundId = Integer.toString(rs.getInt("FundId"));
				String FundingAgency = rs.getString("FundingAgency");
				String FAddress = rs.getString("FAddress");
				String FPhone = rs.getString("FPhone");
				String FProjectID = rs.getString("FProjectID");
				String Fund = rs.getString("Fund");
				// Add into the html table
				output += "<tr><td><input id='hidFundIdUpdate' name='hidFundIdUpdate' type='hidden' value='" + FundId
						+ "'>" + FundingAgency + "</td>";
				output += "<td>" + FAddress + "</td>";
				output += "<td>" + FPhone + "</td>";
				output += "<td>" + FProjectID + "</td>";
				output += "<td>" + Fund + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
						+ FundId + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Funds.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertFunds(String FundingAgency, String FAddress, String FPhone, String FProjectID,String Fund) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into funds(`FundId`,`FundingAgency`,`FAddress`,`FPhone`,`FProjectID`,`Fund`)"
					+ "values(?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, FundingAgency);
			preparedStmt.setString(3, FAddress);
			preparedStmt.setString(4, FPhone);
			preparedStmt.setString(5, FProjectID);
			preparedStmt.setString(6, Fund);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newFund = readFunds();
			output = "{\"status\":\"success\", \"data\": \"" +newFund+ "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Funds.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateFunds(String FundId,String FundingAgency, String FAddress, String FPhone, String FProjectID,String Fund) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE funds SET FundingAgency=?,FAddress=?,FPhone=?,FProjectID=?,Fund=? WHERE FundId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
		
			preparedStmt.setString(1, FundingAgency);
			preparedStmt.setString(2, FAddress);
			preparedStmt.setString(3, FPhone);
			preparedStmt.setString(4, FProjectID);
			preparedStmt.setString(5, Fund);
			preparedStmt.setInt(6, Integer.parseInt(FundId));
	
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newFund =readFunds();
			output = "{\"status\":\"success\", \"data\": \"" +newFund+ "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the Funds.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteFunds(String FundId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from funds where FundId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(FundId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newFund = readFunds();
			output = "{\"status\":\"success\", \"data\": \"" +newFund + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Funds.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}