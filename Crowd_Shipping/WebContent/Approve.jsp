<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Notifications</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/adaria/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/bootstrap-responsive.css">
</head>

<body>
 
<f:view>
<h:form id="Notifications">
<div id="main_container">

       
	<div class="header">

            <div class="logo"><a href="index.html">Crowd Shipping</a></div>
            <div class="slogan">| Shipping for all</div> 
        
			<div class="header_socials">
            <a href="#"><img src="<%=request.getContextPath()%>/CSS/adaria/images/rss.png" alt="" title="" border="0" /></a>
            <a href="#"><img src="<%=request.getContextPath()%>/CSS/adaria/images/facebook.png" alt="" title="" border="0" /></a>
            <a href="#"><img src="<%=request.getContextPath()%>/CSS/adaria/images/linkedin.png" alt="" title="" border="0" /></a>
            <a href="#"><img src="<%=request.getContextPath()%>/CSS/adaria/images/twitter.png" alt="" title="" border="0" /></a>
            <a href="#"><img src="<%=request.getContextPath()%>/CSS/adaria/images/flickr.png" alt="" title="" border="0" /></a>
            </div>

    </div> <!--end of header-->

    
	<div class="center_content_pages">
        	<div class="pages_banner" style="left: 5%; top: 20%">
            <center>
            	<h:outputFormat value="Thank You !!"></h:outputFormat>
            </center>
            </div>
        	<div class="left_content"> 
                       
                <div class="box290">
                <h2>Navigate</h2>
                    <ul class="left_menu">
                        <li><h:commandLink action="#{profile.signout}" value="SignOut" ></h:commandLink></li>
                        <li><h:commandLink action="Notifications.jsp">Notifications <span class="badge badge-important">6</span></h:commandLink> </li>
                        <li><h:commandLink action="Neighbors.jsp">Search Neighbors</h:commandLink></li>
                    </ul>
                </div>
                
                <div class="box290"> 
                                 
                </div>
            </div> <!--end of left content-->
	
<div class="right_content_modified">
              
                    <div class="form">
                    
					<h2>Package Details</h2>
					
					<h:dataTable id="dynamicTable" styleClass="table table-hover" rules="all" dir="ltr" cellpadding="5" value="#{notifications.userList}" var="userDetails" >
						<h:column> 
							<f:facet name="header">
								<h:outputLabel value="First Name"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{userDetails.fname}"></h:outputText>
						</h:column>
						<h:column> 
							<f:facet name="header">
								<h:outputLabel value="Last Name"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{userDetails.lname}"></h:outputText>
						</h:column>
						<h:column> 
							<f:facet name="header">
								<h:outputLabel value="Street1"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{userDetails.street1}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Street2"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{userDetails.street2}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="City"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{userDetails.city}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="State"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{userDetails.state}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Zip"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{userDetails.zip}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Mobile"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{userDetails.mobile}"></h:outputText>
						</h:column>
						
						
					</h:dataTable>
					
					<h:panelGrid columns="1">
						<h:commandLink action="Notifications.jsp?faces-redirect=true">Go Back</h:commandLink>
					</h:panelGrid>
				       
					</div>
            </div>
            
            
            
        <div class="clear"></div>
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

</div> <!--end of main container-->
</h:form>
</f:view>
</body>
</html>