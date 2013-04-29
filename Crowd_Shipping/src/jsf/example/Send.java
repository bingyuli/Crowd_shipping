package jsf.example;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.text.ParseException;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
public class Send 
{
	private static String[] days = new String[30];
	private static String[] year = {"2013","2014","2015","2016","2017","2018","2019","2020"};
	private static String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	private static String[] pkgdt = {"Small","Medium","Big"};
	private static Map<String,Object> monthValue;
	private static Map<String,Object> dayValue;
	private static Map<String,Object> yearValue;
	private static Map<String,Object> packageValue;
	private static String source, destination;
	private static String username;
	private String sender_info_query, send_request_query, fromStreet1, fromStreet2, fromCity, fromState, fromCountry, toStreet1, toStreet2, toCity, toState, toCountry, url, address_query;
	private Connection conn;
	private PreparedStatement pstmt, si_pstmt;
	private ResultSet rs;
	private String serviceSelected, email, comments;
	private Date dateinfo;
	private boolean isLoggedIn;
	private String selectPkgSize, selectDay, selectMonth, selectYear, selectDate;
	private int si_rs, fromZip, toZip;

   
        

	
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
//	public String getPkgdt() {
//		return pkgdt;
//	}
	
//	public void setPkgdt(String pkgdt) {
//		this.pkgdt = pkgdt;
//	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public Send()
	{
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
			System.out.println("Inside else");
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
		
		for(int i =0; i < pkgdt.length; i++)
		{
			packageValue.put(pkgdt[i], pkgdt[i]);
		}
		
		username = String.valueOf((FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username")));
		System.out.println(username);
		getUserInfo();
		System.out.println("**********checking login credentials");
		
			
	}
	
	public String signout() 
	{
		System.out.println("hello");
		
		HttpSession session = ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true));
		if(session != null)
		{
			System.out.println("Session not null");
			session.removeAttribute(username);
			session.invalidate();
			Login.isLoggedIn = false;
		}

	     return "Login?faces-redirect=true";
	}
	
	public String check()
	{
		System.out.println("aa to chale che laa");
		return "Hello";
	}
	
	public String getSelectPkgSize() {
		return selectPkgSize;
	}

	public void setSelectPkgSize(String selectPkgSize) {
		this.selectPkgSize = selectPkgSize;
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
	
	
	public Map<String,Object> getMonthValue()
	{
		return this.monthValue;
	}
	
	public Map<String,Object> getPkgdtValue()
	{
		return this.packageValue;
	}
	
	public Map<String,Object> getDayValue()
	{
		return this.dayValue;
	}
	
	public Map<String,Object> getYearValue()
	{
		return this.yearValue;
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
	
	public String[] getPkgdt() {
		return pkgdt;
	}

	public void setMonths(String[] months) {
		this.months = months;
	}

	public void setPkgdt(String[] pkgdt) {
		this.pkgdt = pkgdt;
	}
	
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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
	
	public String getToStreet1() {
		return toStreet1;
	}

	public void setToStreet1(String toStreet1) {
		this.toStreet1 = toStreet1;
	}
	
	public String getFromStreet2() {
		return fromStreet2;
	}
	
	public void setFromStreet2(String fromStreet2) {
		this.fromStreet2 = fromStreet2;
	}
	
	public String getToStreet2() {
		return toStreet2;
	}

	public void setToStreet2(String toStreet2) {
		this.toStreet2 = toStreet2;
	}
	
	public String getFromCity() {
		return fromCity;
	}
	
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public String getFromState() {
		return fromState;
	}
	
	public void setFromState(String fromState) {
		this.fromState = fromState;
	}

	public String getToState() {
		return toState;
	}

	public void setToState(String toState) {
		this.toState = toState;
	}
	
	public String getFromCountry() {
		return fromCountry;
	}
	
	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}

	public String getToCountry() {
		return toCountry;
	}

	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}
	
	public int getFromZip() {
		return fromZip;
	}

	public void setFromZip(int fromZip) {
		this.fromZip = fromZip;
	}

	public int getToZip() {
		return toZip;
	}

	public void setToZip(int toZip) {
		this.toZip = toZip;
	}
	
	public String getSelectDate() {
		return selectDate;
	}

	public void setSelectDate(String selectDate) {
		this.selectDate = selectDate;
	}

	 public String transformPrevDate(String datoe) throws ParseException 
	    {
	        
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MMM/dd");
	        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");

	        return dateFormat2.format(dateFormat.parse(datoe));
	        
	    }
	 

	
	public void getUserInfo()
	{
			
		
		url = "jdbc:mysql://localhost:3306/crowd_shipping";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","root");
			
			address_query = "select street1, street2, city, state, country, zip from user_address where email = ?";
			
			pstmt = conn.prepareStatement(address_query);
		    pstmt.setString(1, username);
			
			
//			pstmt.setString(2, password);
//			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
                fromStreet1 = rs.getString("street1");
                fromStreet2 = rs.getString("street2");
                fromCity = rs.getString("city");
                fromState = rs.getString("state");
                fromCountry = rs.getString("country");
				fromZip = rs.getInt("zip");
			}
//		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public String sendRequest()
	{
		

		int year = Integer.parseInt(selectYear);
		//int year = 2013;
	    String month = selectMonth;
		//String month = "Jan";
	    int day = Integer.parseInt(selectDay);
	    //int day = 22;

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
			
			sender_info_query = "insert into sender_info (`email`,`street1`,`street2`,`city`,`state`,`country`,`zip`,`pkgdt`,`date`,`comment`) values(?,?,?,?,?,?,?,?,?,?)";
			
			
			si_pstmt = conn.prepareStatement(sender_info_query);
			si_pstmt.setString(1, username);
			si_pstmt.setString(2, toStreet1);
			si_pstmt.setString(3, toStreet2);
			si_pstmt.setString(4, toCity);
			si_pstmt.setString(5, toState);
			si_pstmt.setString(6, toCountry);
			si_pstmt.setInt(7, toZip);
    		si_pstmt.setString(8, selectPkgSize);
			//si_pstmt.setString(8, "Medium");
			
			
			
			
			
			 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
	            
	            si_pstmt.setDate(9, sqlDate);
			 
	            si_pstmt.setString(10, comments);
	            //si_pstmt.setString(10, "Handle with care");
	            
	            si_rs = si_pstmt.executeUpdate();
			
	            if(si_rs > 0){
	            	System.out.println("*********sql statement succeded");
				return "Success";
				
	            }
			else {
				System.out.println("*********sql statement failed");
				return "Failed";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Failed";
		}
		
	}
	
	
	
	public String getServiceSelected() {
		return serviceSelected;
	}

	public void setServiceSelected(String serviceSelected) {
		
		this.serviceSelected = serviceSelected;
		
	}
}
	
/*	public void serviceRedirect(ValueChangeEvent ve)
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

	*/