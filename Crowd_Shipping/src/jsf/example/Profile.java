package jsf.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Profile 
{
	private static String[] days = new String[30];
	private static String[] year = {"2013","2014","2015","2016","2017","2018","2019","2020"};
	private static String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	private static Map<String,Object> monthValue;
	private static Map<String,Object> dayValue;
	private static Map<String,Object> yearValue;
	private static String source, destination;
	private static String username;
	private String fromCity, toCity, url, address_query;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String serviceSelected;
	private boolean isLoggedIn;
	
	private int fromZip, toZip;
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
	
	public Profile()
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
		
		monthValue = new LinkedHashMap<String, Object>();
		dayValue = new LinkedHashMap<String, Object>();
		yearValue = new LinkedHashMap<String, Object>();
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
		
		username = String.valueOf(((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("username"));
		
		getUserInfo();
		
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
			//session.removeAttribute(username);
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
				fromCity = rs.getString("city");
				fromZip = rs.getInt("zip");
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
