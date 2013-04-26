package jsf.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.faces.component.UIForm;
import javax.faces.event.ValueChangeEvent;

public class peopleNearBy 
{
	private String url, address_query;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private double userLatitude, userLongitude;
	private String zip;
	private List<userDetails> userList;
	private userDetails user;
	
	public List<userDetails> getUserList() {
		return userList;
	}

	public void setUserList(List<userDetails> userList) {
		this.userList = userList;
	}

	
	public userDetails getUser() {
		return user;
	}

	public void setUser(userDetails user) {
		this.user = user;
	}

	private double distance = 5.0;
	private double earthRadius = 3959.0;
	private UIForm tableForm;

	public void selectedDistance(ValueChangeEvent ve)
	{
		this.distance = Double.parseDouble((String)ve.getNewValue());
		System.out.println(distance);
	}
	
	public void searchNeighbors()
	{
		userList = new ArrayList<userDetails>();
		
		
		url = "jdbc:mysql://localhost:3306/crowd_shipping";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","root");
			
			address_query = "select latitude, longitude from zips where zip_code = ?";
			
			pstmt = conn.prepareStatement(address_query);
			pstmt.setInt(1, Integer.parseInt(zip));
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				System.out.println("Results Found");
				userLatitude = rs.getDouble("latitude");
				userLongitude = rs.getDouble("longitude");
				System.out.println(userLatitude);
				System.out.println(userLongitude);
			}
			
		}catch (Exception e) 
		{
			//throw new Exception(e.getMessage());
		}
		fetchUserDetails();
		
	}
			
		public void fetchUserDetails()
		{
			try
			{
				double latN = Math.toDegrees(Math.asin(Math.sin(Math.toRadians(userLatitude)) * Math.cos(distance / earthRadius) + Math.cos(Math.toRadians(userLatitude)) * Math.sin(distance / earthRadius) * Math.cos(Math.toRadians(0.0))));
                double latS = Math.toDegrees(Math.asin(Math.sin(Math.toRadians(userLatitude)) * Math.cos(distance / earthRadius) + Math.cos(Math.toRadians(userLatitude)) * Math.sin(distance / earthRadius) * Math.cos(Math.toRadians(180.0))));
                double lonE = Math.toDegrees(Math.toRadians(userLongitude) + Math.atan2(Math.sin(Math.toRadians(90.0)) * Math.sin(distance / earthRadius) * Math.cos(Math.toRadians(userLatitude)), Math.cos(distance / earthRadius) - Math.sin(Math.toRadians(userLatitude)) * Math.sin(Math.toRadians(latN))));
                double lonW = Math.toDegrees(Math.toRadians(userLongitude) + Math.atan2(Math.sin(Math.toRadians(270.0)) * Math.sin(distance / earthRadius) * Math.cos(Math.toRadians(userLatitude)), Math.cos(distance / earthRadius) - Math.sin(Math.toRadians(userLatitude)) * Math.sin(Math.toRadians(latN))));
				
			    String searchquery = "select * from user_address where (latitude <= ?  AND latitude >= ? ) AND (longitude <= ? AND longitude >= ? ) AND (latitude != ? AND longitude != ?) AND city != ''";
			    System.out.println("select * from user_address where (latitude <=" + latN + "  AND latitude >=" + latS + " AND longitude <=" + lonE +  " AND longitude >=" + lonW + ") AND (latitude !=" + userLatitude + " AND longitude !=" + userLongitude + ") AND city != ''"); 
			    PreparedStatement stmt1 = conn.prepareStatement(searchquery);
			    stmt1.setDouble(1, latN);
			    stmt1.setDouble(2, latS);
			    stmt1.setDouble(3, lonE);
			    stmt1.setDouble(4, lonW);
			    stmt1.setDouble(5, userLatitude);
			    stmt1.setDouble(6, userLongitude);
			    
			    
			    ResultSet rs1 = stmt1.executeQuery();
			    System.out.println("Working till here");
			    
			    while(rs1.next())
			    {
			    	user = new userDetails();
			    	String email = rs1.getString("email");
			    	System.out.println(email);
			    	String query = "Select fname, lname, mobile from user_details where email = '"+ email +"' ";
			    	Statement stmt = conn.createStatement();
			    	ResultSet rs2 = stmt.executeQuery(query);
			    	rs2.next();
			    	user.setFname(rs2.getString("fname"));
			    	user.setLname(rs2.getString("lname"));
			    	user.setMobile(rs2.getLong("mobile"));
			    	user.setStreet1(rs1.getString(2));
			    	user.setStreet2(rs1.getString(3));
			    	user.setCity(rs1.getString(4));
			    	user.setState(rs1.getString(5));
			    	user.setCountry(rs1.getString(6));
			    	user.setZip(rs1.getInt(7));
			    	
			    	int temp = (int)((Math.acos(Math.sin(Math.toRadians(userLatitude)) * Math.sin(Math.toRadians(rs1.getDouble(8))) + Math.cos(Math.toRadians(userLatitude)) * Math.cos(Math.toRadians(rs1.getDouble(8))) * Math.cos(Math.toRadians(rs1.getDouble(9)) - Math.toRadians(userLongitude))) * earthRadius ) * Math.pow(10, 2));
			    	double difference = ((double)temp)/Math.pow(10, 2);
			    	user.setDifference(difference);
			    	
			    	userList.add(user);
			    }
			    
					
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public UIForm getTableForm() {
		return tableForm;
	}

	public void setTableForm(UIForm tableForm) {
		this.tableForm = tableForm;
	}
}
