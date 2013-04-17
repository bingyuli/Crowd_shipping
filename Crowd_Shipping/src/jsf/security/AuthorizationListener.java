package jsf.security;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class AuthorizationListener implements PhaseListener {
	 
public void afterPhase(PhaseEvent event) {
 
FacesContext facesContext = event.getFacesContext();
String currentPage = facesContext.getViewRoot().getViewId();
 
boolean isLoginPage = (currentPage.lastIndexOf("Login.jsp") > -1);
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
 
if(session==null){
NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
nh.handleNavigation(facesContext, null, "loginpage");
}
 
else{
Object currentUser = session.getAttribute("username");
 
if (!isLoginPage && (currentUser == null || currentUser == "")) {
NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
nh.handleNavigation(facesContext, null, "loginpage");
}
}
}
 

 
public PhaseId getPhaseId() {
return PhaseId.RESTORE_VIEW;
}



@Override
public void beforePhase(PhaseEvent arg0) {
	// TODO Auto-generated method stub
	
}
}