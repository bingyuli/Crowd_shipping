package jsf.example;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

public class Receive {

	private static String[] days = new String[30];
	private static String[] year = {"2013","2014","2015","2016","2017","2018","2019","2020"};
	private static String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	private static String[] packageSize = {"Small","Medium","Big"};
	private static Map<String,Object> monthValue;
	private static Map<String,Object> dayValue;
	private static Map<String,Object> yearValue;
	private static Map<String,Object> packageValue;

	private static String username;
	private String fromStreet1, fromStreet2, fromCity, fromState,fromCountry, url, address_query;
	private String receivePakageQuery;
	private String selectPkgSize, selectDay, selectMonth, selectYear, selectDate, comments;

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String serviceSelected;
	private boolean isLoggedIn;
	private int fromZip;
	private String fromZipAddress;
	
//	static
//	{
//		monthValue = new LinkedHashMap<String, Object>();
//		dayValue = new LinkedHashMap<Integer, Object>();
//		yearValue = new LinkedHashMap<Integer, Object>();
//		for(int i =0; i < months.length; i++)
//		{
//			monthValue.put(months[i], months[i]);
//		}
//		for(int j = 1; j <= 31; j++)
//		{
//			dayValue.put(j, j);
//		}
//		for(int k = 0; k < year.length; k++)
//		{
//			yearValue.put(year[k], year[k]); 
//		}
//		
//	}
	
	public String getFromZipAddress() {
		return fromZipAddress;
	}

	public void setFromZipAddress(String fromZipAddress) {
		this.fromZipAddress = fromZipAddress;
	}

	public Receive()
	{
		monthValue = new LinkedHashMap<String, Object>();
		dayValue = new LinkedHashMap<String, Object>();
		yearValue = new LinkedHashMap<String, Object>();
		packageValue = new LinkedHashMap<String, Object>();
		for(int i =0; i < months.length; i++)
		{
			monthValue.put(months[i], months[i]);
		}
		for(int j = 1; j <= 31; j++)
		{
			dayValue.put((String.valueOf(j)), (String.valueOf(j)));
		}
		for(int k = 0; k < year.length; k++)
		{
			yearValue.put(year[k], year[k]); 
		}
		
		for(int i = 0; i < packageSize.length; i++)
		{
			packageValue.put(packageSize[i], packageSize[i]); 
		}
		username = String.valueOf(((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("username"));
		
		getUserInfo();
		
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
	
	public String signout() 
	{
		System.out.println("hello");
//		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//		
//		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(username);
//		
		HttpSession session = ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true));
		if(session != null)
		{
			System.out.println("Session not null");
			session.removeAttribute(username);
			session.invalidate();
			Login.isLoggedIn = false;
		}
//	    try {
//	    	System.out.println("hello2");
//			FacesContext.getCurrentInstance().getExternalContext().redirect("Login.jsp?faces-redirect=true");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.out.println("hello3");
//			e.printStackTrace();
//		}
//		try {
//			((HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse()).sendRedirect("Login.jsp");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	     return "Login?faces-redirect=true";
	}
	
	public String check()
	{
		System.out.println("aa to chale che laa");
		return "Hello";
	}
	
//	public boolean equals(Object obj)
//	{
//		if(!(obj instanceof Map<?, ?>))
//		{
//			return false;
//		}
//		Map<Integer, Object> value = (Map<Integer, Object>)obj;
//		return (this.id == value.id);
//	}
	
	public Map<String,Object> getMonthValue()
	{
		return this.monthValue;
	}
	
	public Map<String,Object> getDayValue()
	{
		return this.dayValue;
	}
	
	public Map<String,Object> getYearValue()
	{
		return this.yearValue;
	}
	
	public Map<String,Object> getPackageValue()
	{
		return this.packageValue;
	}
	
	public String[] getDays() {
		return days;
	}

	public void setDays(String[] days) {
		this.days = days;
	}

	public String[] getYear() {
		return year;
	}

	public void setYear(String[] year) {
		this.year = year;
	}

	public String[] getMonths() {
		return months;
	}

	public void setMonths(String[] months) {
		this.months = months;
	}

	public static String[] getPackageSize() {
		return packageSize;
	}

	public static void setPackageSize(String[] packageSize) {
		Receive.packageSize = packageSize;
	}

	public String getUsername() {
		this.username = new Login().getUsername();
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getFromStreet1() {
		return fromStreet1;
	}

	public void setFromStreet1(String fromStreet1) {
		this.fromStreet1 = fromStreet1;
	}

	public String getFromStreet2() {
		return fromStreet2;
	}

	public void setFromStreet2(String fromStreet2) {
		this.fromStreet2 = fromStreet2;
	}

	public String getFromState() {
		return fromState;
	}

	public void setFromState(String fromState) {
		this.fromState = fromState;
	}

	public String getFromCountry() {
		return fromCountry;
	}

	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getSelectPkgSize() {
		return selectPkgSize;
	}

	public void setSelectPkgSize(String selectPkgSize) {
		this.selectPkgSize = selectPkgSize;
	}

	public String getSelectDay() {
		return selectDay;
	}

	public void setSelectDay(String selectDay) {
		this.selectDay = selectDay;
	}

	public String getSelectMonth() {
		return selectMonth;
	}

	public void setSelectMonth(String selectMonth) {
		this.selectMonth = selectMonth;
	}

	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public int getFromZip() {
		return fromZip;
	}

	public void setFromZip(int fromZip) {
		this.fromZip = fromZip;
	}

	public String getSelectDate() {
		return selectDate;
	}

	public void setSelectDate(String selectDate) {
		this.selectDate = selectDate;
	}

	public void submitPackageRequest(ValueChangeEvent ve)
	{
	
		this.selectPkgSize = ve.getNewValue().toString();
	}
	
	public void submitDayRequest(ValueChangeEvent ve)
	{
	
		this.selectDay = ve.getNewValue().toString();
	}
	
	public void submitMonthRequest(ValueChangeEvent ve)
	{
	
		this.selectMonth = ve.getNewValue().toString();
	}
	
	public void submitYearRequest(ValueChangeEvent ve)
	{
	
		this.selectYear = ve.getNewValue().toString();
	}
	
	public String transformPrevDate(String datoe) throws ParseException {
		
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MMM/dd");
	    SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");

	    return dateFormat2.format(dateFormat.parse(datoe));
	    
	}
	
	public void submitRequest()
	{
		System.out.println("Perfect "+username+" " +fromStreet1+ " "+fromStreet2+" "+fromState+" "+fromZipAddress+" "+fromCountry+" "+selectDay+" "+selectMonth+" "+selectYear+" "+selectPkgSize+" " +comments);
		
		int year = Integer.parseInt(selectYear);
	    String month = selectMonth;
	    int day = Integer.parseInt(selectDay);

	    String date = year + "/" + month + "/" + day;
	    java.util.Date utilDate = null;

	    try {

	      selectDate = transformPrevDate(date);
	      SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		  utilDate = formatter.parse(selectDate);
	    
	    } catch (ParseException e) {
	      System.out.println(e.toString());
	      e.printStackTrace();
	    }
	    
		url = "jdbc:mysql://localhost:3306/crowd_shipping";
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","root");
			
			receivePakageQuery = "insert into receive_package (`email`, `street1`, `street2`, `city`, `state`, `country`, `zip`, `package_size`, `date`,`comment`) values(?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(receivePakageQuery);
			pstmt.setString(1, username);
			pstmt.setString(2, fromStreet1);
			pstmt.setString(3, fromStreet2);
			pstmt.setString(4, fromCity);
			pstmt.setString(5, fromState);
			pstmt.setString(6, fromCountry);
			pstmt.setInt(7, Integer.parseInt(fromZipAddress));
			pstmt.setString(8, selectPkgSize);
			pstmt.setString(10, comments);
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    
			pstmt.setDate(9, sqlDate);
			
			  
			int state;
			state = pstmt.executeUpdate();
//			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				fromCity = rs.getString("city");
				fromZip = rs.getInt("zip");
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void getUserInfo()
	{
		url = "jdbc:mysql://localhost:3306/crowd_shipping";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","root");
			
			address_query = "select city, zip from user_address where email = ?";
			
			pstmt = conn.prepareStatement(address_query);
			pstmt.setString(1, username);
//			pstmt.setString(2, password);
//			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
//				fromCity = rs.getString("city");
//				fromZip = rs.getInt("zip");
			}
//		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getServiceSelected() {
		return serviceSelected;
	}

	public void setServiceSelected(String serviceSelected) {
		
		this.serviceSelected = serviceSelected;
		
	}
	
	public void serviceRedirect(ValueChangeEvent ve)
	{
		this.serviceSelected = (String)ve.getNewValue();
		if(this.serviceSelected.equalsIgnoreCase("Send"))
		{
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("Send.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(this.serviceSelected.equalsIgnoreCase("Receive"))
		{
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("Receive.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
