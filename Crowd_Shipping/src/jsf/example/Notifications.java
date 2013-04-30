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
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;
@ViewScoped
public class Notifications 
{
	private String approveStatus, packageDirection, approve_query;
	private String rejectStatus = "Reject";
	private peopleNearBy neighbor;
	private String username, user_email;
	private PreparedStatement pstmt, zstmt, rstmt, rstmt1;
	private ResultSet rs, zrs, urs, urs2;
	private static ResultSet urs1, tempurs1;
	public static ResultSet getUrs1() {
		return urs1;
	}

	public static void setUrs1(ResultSet urs1) {
		Notifications.urs1 = urs1;
	}

	private Connection conn;
	private int user_zip, pkg_id;
	private double distance;
	private userDetails user,mySendInfo;
	private List<userDetails> userList, mySendList;
	private int notificationCount = 0;
	private HtmlDataTable dataTable;




	public int getUser_zip() {
		return user_zip;
	}

	public void setUser_zip(int user_zip) {
		this.user_zip = user_zip;
	}

	private List<Integer> nearByZips;
	private nearbyRequests nreq;
	private Package pkg, myPkgInfo, pkgRow = new Package(), rcvPkgInfo = new Package();
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
		if(!Login.isLoggedIn)
		{
			FacesContext context = FacesContext.getCurrentInstance();
			try {
				context.getExternalContext().redirect("Login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void fetchReqUserInfo()
	{
		packageList = new ArrayList<Package>();
		try
		{
			String user_zip_query = "Select zip from user_address where email = ?";
			String sender_ifo_query = "Select * from sender_info where zip = ? and status = 0 and email != ?";
			String receive_info_query = "Select * from receive_package where zip = ? and status = 0 and email!= ?";
			pstmt = conn.prepareStatement(user_zip_query);
			pstmt.setString(1, username);

			rs = pstmt.executeQuery();

			while(rs.next())
			{


				user_zip = rs.getInt("zip");
				nreq.searchNeighbors(user_zip, distance);
				nearByZips = nreq.getNearByZips();


				for (int j = 0; j < nearByZips.size(); j++) 
				{
//					System.out.println("inside for");
					zstmt = conn.prepareStatement(sender_ifo_query);
					zstmt.setInt(1, nearByZips.get(j));
					zstmt.setString(2, username);

					zrs = zstmt.executeQuery();

					while(zrs.next())
					{
						pkg = new Package();
						pkg.setId(zrs.getInt("id"));
						//this.pkg_id = zrs.getInt("id");
						pkg.setPackageOwner(zrs.getString("email"));
						pkg.setPackageDirection("Send");
						this.user_email = zrs.getString("email");
						System.out.println(user_email);
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
					
					rstmt = conn.prepareStatement(receive_info_query);
					rstmt.setInt(1, nearByZips.get(j));
					rstmt.setString(2, username);
					
					urs1 = rstmt.executeQuery();
					setUrs1(urs1);
					while(urs1.next())
					{
						
						pkg = new Package();
						pkg.setId(urs1.getInt("id"));
						//this.pkg_id = zrs.getInt("id");
						pkg.setPackageOwner(urs1.getString("email"));
						
						this.user_email = urs1.getString("email");
						System.out.println(user_email);
						pkg.setPackageDirection("Receive");
						rstmt1 = conn.prepareStatement("Select * from user_address where email = '"+ user_email +"'");
						urs2 = rstmt1.executeQuery();
					    if(urs2.next())
					    {
					    	pkg.setStreet1(urs2.getString("street1"));
					    	pkg.setStreet2(urs2.getString("street2"));
					    	pkg.setCity(urs2.getString("city"));
					    	pkg.setState(urs2.getString("state"));
					    	pkg.setZip(urs2.getInt("zip"));
					    }
					    rcvPkgInfo.setStreet1(urs1.getString("street1"));
					    rcvPkgInfo.setStreet2(urs1.getString("street2"));
					    rcvPkgInfo.setCity(urs1.getString("city"));
					    rcvPkgInfo.setState(urs1.getString("state"));
					    rcvPkgInfo.setZip(urs1.getInt("zip"));
						
					    pkg.setPkgType(urs1.getString("package_size"));
						pkg.setDate(urs1.getDate("date").toString());
						pkg.setComment(urs1.getString("comment"));

						packageList.add(pkg);
					}
					
					notificationCount++;

				}
//				notificationCount = packageList.size();

			}
//		
		}catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void editDataItem()
	{
		pkgRow = (Package)dataTable.getRowData();
		System.out.println("Getting row data");
		System.out.println(pkgRow.getId());
		this.pkg_id = pkgRow.getId();
		this.packageDirection = pkgRow.getPackageDirection();
	}

	public void selectedDistance(ValueChangeEvent ve)
	{
		this.distance = Double.parseDouble((String)ve.getNewValue());
//		if(FacesContext.getCurrentInstance().isPostback())
//			packageList.clear();
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

	public String redirectTo()
	{
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("Notifications.jsp");
//			return "Notifications";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Notifications";
	}

	public void approveRequest()
	{
		editDataItem();

		if(this.packageDirection == "Send")
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
			    	if(this.packageDirection == "Receive")
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
				notificationCount++;
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

	public int getNotificationCount() {
		return notificationCount;
	}

	public void setNotificationCount(int notificationCount) {
		this.notificationCount = notificationCount;
	}

	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}

	public Package getPkgRow() {
		return pkgRow;
	}

	public void setPkgRow(Package pkgRow) {
		this.pkgRow = pkgRow;
	}

	public String getPackageDirection() {
		return packageDirection;
	}

	public void setPackageDirection(String packageDirection) {
		this.packageDirection = packageDirection;
	}
}