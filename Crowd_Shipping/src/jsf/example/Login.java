package jsf.example;

import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

public class Login {

	private String url, loginquery, userquery;
	private Connection conn;
	private PreparedStatement pstmt, pstmt1;
	private ResultSet rs, rs1;
	private String username, password;
	public static boolean isLoggedIn = false;
	
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

	public static String MD5(String md5) {
        try {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                byte[] array = md.digest(md5.getBytes());
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < array.length; ++i) {
                    sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
            
        }
        return null;
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
			pstmt.setString(2, MD5(password));
			
			rs = pstmt.executeQuery();
			//System.out.println(rs.getString(1));
			if(rs.next())
			{
				
				isLoggedIn = true;
				FacesContext.getCurrentInstance().getExternalContext().getSession(true);
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
