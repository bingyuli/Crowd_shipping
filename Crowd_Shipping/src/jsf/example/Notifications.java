package jsf.example;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

public class Notifications 
{
	private String approveStatus;
	
	
	private String rejectStatus = "Reject";
	private peopleNearBy neighbor;
	private String username, user_email;
	private PreparedStatement pstmt, zstmt;
	private ResultSet rs, zrs, urs;
	private Connection conn;
	private int user_zip, pkg_id;
	private double distance;
	private userDetails user,mySendInfo;
	private List<userDetails> userList, mySendList;

	
	
	
	public int getUser_zip() {
		return user_zip;
	}

	public void setUser_zip(int user_zip) {
		this.user_zip = user_zip;
	}

	private List<Integer> nearByZips;
	private nearbyRequests nreq;
	private Package pkg, myPkgInfo;
	private List<Package> packageList, myPkgList;
	
	public List<Package> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<Package> packageList) {
		this.packageList = packageList;
	}

	public List<userDetails> getUserList() {
		return userList;
	}

	public void setUserList(List<userDetails> userList) {
		this.userList = userList;
	}

	
//	public Notifications()
	{
		System.out.print("Constructor called");
		neighbor = new peopleNearBy();
		nreq  = new nearbyRequests();
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
		
		
		fetchSubInfo();
		
		//neighbor.searchNeighbors();
		/*if(!Login.isLoggedIn)
		{
			FacesContext context = FacesContext.getCurrentInstance();
			try {
				context.getExternalContext().redirect("Login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}

	public void fetchReqUserInfo()
	{
		try
		{
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(url,"root","root");
			
			String user_zip_query = "Select zip from user_address where email = ?";
			String sender_ifo_query = "Select * from sender_info where zip = ?";
			pstmt = conn.prepareStatement(user_zip_query);
			pstmt.setString(1, username);
//			pstmt.setString(2, password);
//			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				
				
				user_zip = rs.getInt("zip");
				nreq.searchNeighbors(user_zip, distance);
				nearByZips = nreq.getNearByZips();
				
				for (int j = 0; j < nearByZips.size(); j++) 
				{
					System.out.println("inside for");
					zstmt = conn.prepareStatement(sender_ifo_query);
					zstmt.setInt(1, nearByZips.get(j));
					
					zrs = zstmt.executeQuery();
					
					if(zrs.next())
					{
						pkg = new Package();
						pkg.setId(zrs.getInt("id"));
						this.pkg_id = zrs.getInt("id");
						pkg.setPackageOwner(zrs.getString("email"));
						this.user_email = zrs.getString("email");
						pkg.setStreet1(zrs.getString("street1"));
						pkg.setStreet2(zrs.getString("street2"));
						pkg.setCity(zrs.getString("city"));
						pkg.setState(zrs.getString("state"));
						pkg.setZip(zrs.getInt("zip"));
						pkg.setPkgType(zrs.getString("pkgdt"));
						pkg.setDate(zrs.getDate("date").toString());
						pkg.setComment(zrs.getString("comment"));
						
						packageList.add(pkg);
					}
					
					
				}
				
			}
//		
		}catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	public void selectedDistance(ValueChangeEvent ve)
	{
		this.distance = Double.parseDouble((String)ve.getNewValue());
		packageList.clear();
		fetchReqUserInfo();
		fetchSubInfo();
		System.out.println(distance);
	}
	
	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}


	public String getRejectStatus() {
		return rejectStatus;
	}


	public void setRejectStatus(String rejectStatus) {
		this.rejectStatus = rejectStatus;
	}
	
	public void approveRequest()
	{
		
		String approve_query = "update sender_info set status = 1, approved_email = ?  where email = ? and id = ?";
		
		try 
		{
			PreparedStatement stmt = conn.prepareStatement(approve_query);
			stmt.setString(1, username);
			stmt.setString(2, user_email);
			stmt.setInt(3, pkg_id);
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0)
			{
				userList.clear();
				String user_details_query = "Select * from user_address where email = ?";
				PreparedStatement pstmt = conn.prepareStatement(user_details_query);
				pstmt.setString(1, user_email);
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
			    	user.setStreet1(urs.getString(2));
			    	user.setStreet2(urs.getString(3));
			    	user.setCity(urs.getString(4));
			    	user.setState(urs.getString(5));
			    	user.setCountry(urs.getString(6));
			    	user.setZip(urs.getInt(7));
			    	
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
	
	
	public void fetchSubInfo()
	{
		myPkgList.clear();
		String subInfo_query = "Select * from sender_info where email = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(subInfo_query);
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				myPkgInfo = new Package();
				
				myPkgInfo.setStreet1(rs.getString("street1"));
				myPkgInfo.setStreet2(rs.getString("street2"));
				myPkgInfo.setCity(rs.getString("city"));
				myPkgInfo.setState(rs.getString("state"));
				myPkgInfo.setZip(rs.getInt("zip"));
				myPkgInfo.setPkgType(rs.getString("pkgdt"));
				myPkgInfo.setDate(rs.getDate("date").toString());
				myPkgInfo.setComment(rs.getString("comment"));
				int status = rs.getInt("status");
				if(status == 1)
					myPkgInfo.setStatus("Approved");
				else
					myPkgInfo.setStatus("Pending");
				
				myPkgList.add(myPkgInfo);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void declineRequest()
	{
		
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double selectedDistance) {
		this.distance = selectedDistance;
	}

	public int getPkg_id() {
		return pkg_id;
	}

	public void setPkg_id(int pkg_id) {
		this.pkg_id = pkg_id;
	}

	public userDetails getMySendInfo() {
		return mySendInfo;
	}

	public void setMySendInfo(userDetails mySendInfo) {
		this.mySendInfo = mySendInfo;
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
}
