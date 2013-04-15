package jsf.example;

import java.sql.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

public class Login {

	private String url, loginquery, userquery;
	private Connection conn;
	private PreparedStatement pstmt, pstmt1;
	private ResultSet rs, rs1;
	private String username = "myexample@abc.com", password;
	public static boolean isLoggedIn ;
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String checkUser()
	{
		url = "jdbc:mysql://localhost:3306/crowd_shipping";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","root");
			
			loginquery = "select * from login where email = ? and password = ?";
			userquery = "select fname from user_details where email = ?";
			pstmt = conn.prepareStatement(loginquery);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			//System.out.println(rs.getString(1));
			if(rs.next())
			{
				
				isLoggedIn = true;
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", username);
				//pstmt.close();
				//rs.close();
				
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage("login:btnLogin", new FacesMessage("Invalid Username or Password"));
				
			}
			
			pstmt = conn.prepareStatement(userquery);
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			//System.out.println(rs1.getString(1));
			if(isLoggedIn && rs.next())
			{
				this.username = rs.getString("fname");
				FacesContext context = FacesContext.getCurrentInstance();
				//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", username);
				context.getExternalContext().redirect("Profile.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "Hello";
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
