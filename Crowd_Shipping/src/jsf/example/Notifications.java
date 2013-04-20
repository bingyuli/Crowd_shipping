package jsf.example;

import java.io.IOException;

import javax.faces.context.FacesContext;

public class Notifications 
{
	private String approveStatus = "Approve";
	private String rejectStatus = "Reject";
	
	
	public Notifications()
	{
		System.out.print("Constructor called");
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
}
