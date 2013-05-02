package jsf.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class Approve 
{
//	Notifications goBack = new Notifications();
	private String approveStatus, packageDirection, approve_query;
	private String packageApprover, packageOwner;
	private peopleNearBy neighbor;
	private String username, user_email;
	private PreparedStatement pstmt, zstmt, rstmt, rstmt1;
	private ResultSet rs, zrs, urs, urs2;
	private static ResultSet urs1, tempurs1;
	
	private Connection conn;
	private int user_zip, pkg_id, appPkgID;
	private double distance = 5.0;
	private userDetails user,mySendInfo;
	private List<userDetails> userList, mySendList;
	private int notificationCount = 0;
	private HtmlDataTable dataTable, dataTable1;
	private List<Integer> nearByZips;
	private nearbyRequests nreq;
	private Package pkg, myPkgInfo, pkgRow = new Package(), rcvPkgInfo = new Package();
	private List<Package> packageList, myPkgList;

	public Approve()
	{
		packageList = new ArrayList<Package>();
		userList = new ArrayList<userDetails>();
		myPkgList = new ArrayList<Package>();

		username = String.valueOf(((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("username"));
		String url = "jdbc:mysql://localhost:3306/crowd_shipping";


		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","root");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public userDetails getUser() {
		return user;
	}

	public void setUser(userDetails user) {
		this.user = user;
	}

	public userDetails getMySendInfo() {
		return mySendInfo;
	}

	public void setMySendInfo(userDetails mySendInfo) {
		this.mySendInfo = mySendInfo;
	}

	public List<userDetails> getUserList() {
		return userList;
	}

	public void setUserList(List<userDetails> userList) {
		this.userList = userList;
	}

	public List<userDetails> getMySendList() {
		return mySendList;
	}

	public void setMySendList(List<userDetails> mySendList) {
		this.mySendList = mySendList;
	}

	public Package getMyPkgInfo() {
		return myPkgInfo;
	}

	public void setMyPkgInfo(Package myPkgInfo) {
		this.myPkgInfo = myPkgInfo;
	}

	public List<Package> getMyPkgList() {
		return myPkgList;
	}

	public void setMyPkgList(List<Package> myPkgList) {
		this.myPkgList = myPkgList;
	}

	public void approveRequest(String packageDirection, int pkg_id, String packageOwner)
	{
//		editDataItem();

		if(packageDirection == "Send")
			approve_query = "update sender_info set status = 1, approved_email = ?  where id = ?";
		else
			approve_query = "update receive_package set status = 1, approved_email = ?  where id = ?";
		
		try 
		{
			PreparedStatement stmt = conn.prepareStatement(approve_query);
			stmt.setString(1, username);
//			stmt.setString(2, user_email);
			stmt.setInt(2, pkg_id);
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0)
			{
				userList.clear();
				String user_details_query = "Select * from user_address where email = ?";
				PreparedStatement pstmt = conn.prepareStatement(user_details_query);
				pstmt.setString(1, packageOwner);
				urs = pstmt.executeQuery();

				while(urs.next())
				{
					user = new userDetails();
			    	String email = urs.getString("email");
			    	System.out.println(email);
			    	String query = "Select fname, lname, mobile from user_details where email = '"+ email +"' ";
			    	Statement stmt1 = conn.createStatement();
			    	ResultSet rs2 = stmt1.executeQuery(query);
			    	rs2.next();
			    	user.setFname(rs2.getString("fname"));
			    	user.setLname(rs2.getString("lname"));
			    	user.setMobile(rs2.getLong("mobile"));
			    	if(packageDirection == "Receive")
			    	{
			    		/*user.setStreet1(getUrs1().getString("street1"));
			    		user.setStreet2(getUrs1().getString("street2"));
			    		user.setCity(getUrs1().getString("city"));
			    		user.setState(getUrs1().getString("state"));
			    		user.setCountry(getUrs1().getString("country"));
			    		user.setZip(getUrs1().getInt("zip"));*/
			    		
			    		user.setStreet1(rcvPkgInfo.getStreet1());
			    		user.setStreet2(rcvPkgInfo.getStreet2());
			    		user.setCity(rcvPkgInfo.getCity());
			    		user.setState(rcvPkgInfo.getState());
//			    		user.setCountry(getUrs1().getString("country"));
			    		user.setZip(rcvPkgInfo.getZip());
			    	}
			    	else
			    	{
			    		user.setStreet1(urs.getString("street1"));
			    		user.setStreet2(urs.getString("street2"));
			    		user.setCity(urs.getString("city"));
			    		user.setState(urs.getString("state"));
			    		user.setCountry(urs.getString("country"));
			    		user.setZip(urs.getInt("zip"));
			    	}
			    	userList.add(user);
				}

			}

			FacesContext.getCurrentInstance().getExternalContext().redirect("Approve.jsp");

		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void approvedRequest()
	{
//		editDataItem2();
		if(this.approveStatus != null)
		{
			if(this.approveStatus == "Pending")
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("Notifications.jsp#");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else
			{
				try{
				userList.clear();
			String user_details_query = "Select * from user_address where email = ?";
			PreparedStatement pstmt = conn.prepareStatement(user_details_query);
			pstmt.setString(1, packageApprover);
			urs = pstmt.executeQuery();

			while(urs.next())
			{
				user = new userDetails();
		    	String email = urs.getString("email");
		    	System.out.println(email);
		    	String query = "Select fname, lname, mobile from user_details where email = '"+ packageApprover +"' ";
		    	Statement stmt1 = conn.createStatement();
		    	ResultSet rs2 = stmt1.executeQuery(query);
		    	rs2.next();
		    	user.setFname(rs2.getString("fname"));
		    	user.setLname(rs2.getString("lname"));
		    	user.setMobile(rs2.getLong("mobile"));
		    	user.setStreet1(urs.getString("street1"));
	    		user.setStreet2(urs.getString("street2"));
	    		user.setCity(urs.getString("city"));
	    		user.setState(urs.getString("state"));
	    		user.setCountry(urs.getString("country"));
	    		user.setZip(urs.getInt("zip"));
	    		userList.add(user);
		    	
			}
			FacesContext.getCurrentInstance().getExternalContext().redirect("Approve.jsp");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			}

				
		}
	}

	
}


