<%@page import="jsf.example.Login"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sender Information</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/adaria/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/bootstrap.css">

</head>

<body onload="setPlaceHolders()">
<f:view>
<h:form id="Send">
<div id="main_container">

       
	<div class="header">

            <div class="logo">
            <img src="<%=request.getContextPath()%>/CSS/adaria/images/cs.jpg" width="70" height="70" alt="" title="" border="0" />
            <a>Crowd Shipping</a></div>
            <div class="slogan">| Shipping for all</div> 
        
			<div class="header_socials">
            <a href="#"><img src="<%=request.getContextPath()%>/CSS/adaria/images/rss.png" alt="" title="" border="0" /></a>
            <a href="#"><img src="<%=request.getContextPath()%>/CSS/adaria/images/facebook.png" alt="" title="" border="0" /></a>
            <a href="#"><img src="<%=request.getContextPath()%>/CSS/adaria/images/linkedin.png" alt="" title="" border="0" /></a>
            <a href="#"><img src="<%=request.getContextPath()%>/CSS/adaria/images/twitter.png" alt="" title="" border="0" /></a>
            <a href="#"><img src="<%=request.getContextPath()%>/CSS/adaria/images/flickr.png" alt="" title="" border="0" /></a>
            </div>

    </div> <!--end of header-->
    
    <script type="text/javascript">
	
    function setPlaceHolders()
	{
		var placeholders = ["221 N","4th Street","San Jose","CA","USA","95116"];
		var elements = document.getElementsByTagName("input");
		for (var i=0; i<elements.length; i++) 
		{
			elements[i].setAttribute("required", "required");
			elements[i].setAttribute("placeholder", placeholders[i]);
		}
	}
	
	</script>
	
    <div class="left_content"> 
                       
                <div class="box290">
                <h2>Navigate</h2>
                    <ul class="left_menu">
                        <li><h:commandLink action="#{profile.signout}" value="SignOut" ></h:commandLink></li>
                        <li><h:commandLink action="Notifications.jsp?faces-redirect=true">Notifications 
                        <span class="badge badge-important">
                        	<h:outputText value="#{notifications.notificationCount }"></h:outputText>
                        </span></h:commandLink> </li>
                        <li><h:commandLink action="Neighbors.jsp?faces-redirect=true">Search Neighbors</h:commandLink></li>
                        <li><h:commandLink action="Profile.jsp?faces-redirect=true">Profile</h:commandLink>
                    </ul>
                </div>
                
            </div> <!--end of left content-->
    
    
  <div class="right_content">
        <div class="form">
			<h2>Select a service</h2>
			
			<h:selectOneMenu value="#{profile.serviceSelected}" valueChangeListener="#{profile.serviceRedirect}" onchange="this.form.submit();">
				<f:selectItem itemValue="Select a service" value="Select a service" itemLabel="Select a service"/>
				<f:selectItem itemValue="Send" value="Send" itemLabel="Send"/>
				<f:selectItem itemValue="Receive" value="Receive" itemLabel="Receive"/>
			</h:selectOneMenu>
	    
	        <h2> Send Request </h2>
	    	
					
				<h3> To Address:</h3>
				<h:panelGrid columns="4">
				<h:outputLabel>Street 1: </h:outputLabel>
				<h:inputText id="tostreet1" value="#{send.toStreet1}"  ></h:inputText>
				
				<h:outputLabel>Street2: </h:outputLabel>
				<h:inputText id="tostreet2" value="#{send.toStreet2}"  ></h:inputText>
				
				<h:outputLabel>City: </h:outputLabel>
				<h:inputText id="tocity" value="#{send.toCity}"   ></h:inputText>
				
				<h:outputLabel>State: </h:outputLabel>
				<h:inputText id="tostate" value="#{send.toState}"  ></h:inputText>
				
				<h:outputLabel>Country: </h:outputLabel>
				<h:inputText id="tocountry" value="#{send.toCountry}"   ></h:inputText>
				
				<h:outputLabel>Zip: </h:outputLabel>
				<h:inputText id="tozip" value="#{send.toZipAddress}"   ></h:inputText>
					
				</h:panelGrid>
			
<%--	
<h3>Package Size: </h3>
	<form method="post" action="select.jsp">
<select name="sel">
<option value="Small">Small</option>
<option value="Medium">Medium</option>
<option value="Large">Large</option>
</select>
<br>
</form> 

--%>


 <!--  
    <h:selectOneMenu style="width:auto">
						<f:selectItems value="#{send.pkgdtValue}"  />
					</h:selectOneMenu>
					
				
-->		
					<h3> Package Size:</h3>				
					<h:panelGrid columns="4">
						<h:selectOneMenu style="width:auto" valueChangeListener="#{send.submitPackageRequest}">
							<f:selectItems value="#{send.pkgdtValue}"/>
						</h:selectOneMenu>
					</h:panelGrid>
		
  <!--  
    <h:selectOneMenu style="width:auto">
						<f:selectItems value="#{send.dayValue}"  />
					</h:selectOneMenu>
					<h:selectOneMenu style="width:auto">
						<f:selectItems value="#{send.monthValue}" />
					</h:selectOneMenu>
					<h:selectOneMenu style="width:auto">
						<f:selectItems value="#{send.yearValue}" />
					</h:selectOneMenu>
				
	-->
								
					<h3> Date:</h3>				

						<h:panelGrid columns="4">
						<h:selectOneMenu style="width:auto" valueChangeListener="#{send.submitDayRequest}">
							<f:selectItems value="#{send.dayValue}" />
						</h:selectOneMenu>
						<h:selectOneMenu style="width:auto" valueChangeListener="#{send.submitMonthRequest}" >
							<f:selectItems value="#{send.monthValue}"/>
						</h:selectOneMenu>
						<h:selectOneMenu style="width:auto" valueChangeListener="#{send.submitYearRequest}">
							<f:selectItems value="#{send.yearValue}"/>
						</h:selectOneMenu>
					</h:panelGrid>
					
	
				
    <h3>Comments(if any): </h3>
    <h:panelGrid>
         <h:inputTextarea id="comments" value="#{send.comments}" styleClass="form_input" />
    </h:panelGrid>
	
					
			<h:panelGrid >
		<h:commandButton id="btnSubmit" action="#{send.sendRequest}" value="Submit" styleClass="btn btn-success" style="left: 50%"></h:commandButton>
	</h:panelGrid>
		
						
    </div>
    </div>
    
	
	<div class="footer">
    <div class="copyrights">
    Group 8 Spirit 
    </div>
    <div class="footer_right">
    <a href="#">Home</a>
    <a href="#">About Us</a>
    <a href="#">Privacy Policy</a>
    <a href="#">Contact Us</a>
    </div>
    
    
    <div class="clear"></div>
</div>

</div>
</h:form>
</f:view>
</body>
</html>