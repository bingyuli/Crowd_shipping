package jsf.example;

import java.util.LinkedHashMap;
import java.util.Map;

public class Profile 
{
	private int[] days = new int[30];
	private int[] year = {2013,2014,2015,2016,2017,2018,2019,2020};
	private String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	private Map<String,Object> monthValue;
	private Map<Integer,Object> dayValue;
	private Map<Integer,Object> yearValue;
	private String source, destination;
	private String username;
	
	{
		monthValue = new LinkedHashMap<String, Object>();
		dayValue = new LinkedHashMap<Integer, Object>();
		yearValue = new LinkedHashMap<Integer, Object>();
		for(int i =0; i < months.length; i++)
		{
			monthValue.put(months[i], months[i]);
		}
		for(int j = 1; j <= 31; j++)
		{
			dayValue.put(j, j);
		}
		for(int k = 0; k < year.length; k++)
		{
			yearValue.put(year[k], year[k]); 
		}
	}
	
	public Map<String,Object> getMonthValue()
	{
		return this.monthValue;
	}
	
	public Map<Integer,Object> getDayValue()
	{
		return this.dayValue;
	}
	
	public Map<Integer,Object> getYearValue()
	{
		return this.yearValue;
	}
	public int[] getDays() {
		return days;
	}

	public void setDays(int[] days) {
		this.days = days;
	}

	public int[] getYear() {
		return year;
	}

	public void setYear(int[] year) {
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
	
	
}
