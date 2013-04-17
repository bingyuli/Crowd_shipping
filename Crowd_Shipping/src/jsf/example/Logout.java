package jsf.example;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class Logout 
{
	public String signout() 
	{
		System.out.println("hello");
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
	    try {
	    	System.out.println("hello2");
			FacesContext.getCurrentInstance().getExternalContext().redirect("Login.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("hello3");
			e.printStackTrace();
		}
	     return "Login";
	}
}
