package jsf.example;

import java.sql.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Signup 
{
	private String url, user_address_query, user_details_query, login_query, zips_query;
	private Connection conn;
	private PreparedStatement ud_pstmt, ua_pstmt, login, zips;
	private int ud_rs,ua_rs, user_login;
	private ResultSet rs;
	private double latitude, longitude;
	private String email, fname, lname, street1, street2, city, state, country, password, zip;
	private Long mobile;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFname() {
		return fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getStreet1() {
		return street1;
	}
	
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	
	public String getStreet2() {
		return street2;
	}
	
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getZip() 
	{
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public Long getMobile() {
		return mobile;
	}
	
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String registerUser()
	{
		url = "jdbc:mysql://localhost:3306/crowd_shipping";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","root");
			
			user_details_query = "insert into user_details values(?,?,?,?)";
			user_address_query = "insert into user_address values(?,?,?,?,?,?,?,?,?)";
			login_query = "insert into login values (?,?)";
			zips_query = "select latitude, longitude from zips where zip_code = ?";
			
			zips = conn.prepareStatement(zips_query);
			zips.setInt(1, Integer.parseInt(zip));
			rs = zips.executeQuery();
			
			if(rs.next())
			{
				this.latitude = rs.getDouble("LATITUDE");
				this.longitude = rs.getLong("LONGITUDE");
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage("Signup:btnsignup", new FacesMessage("Invalid Zip. Please enter a valid 5-digit zip code"));
			}
			
			ud_pstmt = conn.prepareStatement(user_details_query);
			ud_pstmt.setString(1, fname);
			ud_pstmt.setString(2, lname);
			ud_pstmt.setLong(3, mobile);
			ud_pstmt.setString(4, email);
			
			ua_pstmt = conn.prepareStatement(user_address_query);
			ua_pstmt.setString(1, email);
			ua_pstmt.setString(2, street1);
			ua_pstmt.setString(3, street2);
			ua_pstmt.setString(4, city);
			ua_pstmt.setString(5, state);
			ua_pstmt.setString(6, country);
			ua_pstmt.setInt(7, Integer.parseInt(zip));
			ua_pstmt.setDouble(8, latitude);
			ua_pstmt.setDouble(9, longitude);
			
			login = conn.prepareStatement(login_query);
			login.setString(1, email);
			login.setString(2, password);
			
			
			
			ud_rs = ud_pstmt.executeUpdate();
			ua_rs = ua_pstmt.executeUpdate();
			user_login = login.executeUpdate();
			
			if(ud_rs > 0 && ua_rs > 0 && user_login > 0)
			{
				FacesContext.getCurrentInstance().getExternalContext().redirect("Login.jsp");
				return "Success";
			}
				
			else
				return "Failed";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Failed";
		}
		finally
		{
			try 
			{
				conn.close();
				ua_pstmt.close();
				ud_pstmt.close();
				
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	}

