<%@page import="jsf.example.Login"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Neighbors</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/adaria/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/bootstrap-responsive.css">
</head>
<script type="text/javascript">
	function setPlaceHolders()
	{
		var placeholders = ["XXXXX"];
		var elements = document.getElementsByTagName("input");
		for (var i=0; i<elements.length; i++) 
		{
			elements[i].setAttribute("required", "required");
			elements[i].setAttribute("placeholder", placeholders[i]);
		}
		
	}
</script>

<body onload="setPlaceHolders()">
 
<f:view>
<h:form id="search">
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
            	<h:outputFormat value="Search Neighborhood"></h:outputFormat>
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
                
                <div class="box290"> </div>           
                </div>
            </div> <!--end of left content-->
	
<div class="right_content">
              
                    <div class="form">
					<h:panelGrid columns="2" width="auto">
						<h:outputLabel value="Enter Zip Code: "></h:outputLabel>
						<h:inputText value="#{peopleNearBy.zip}" id="zip"></h:inputText>
					<h:outputText value="Select search distance: "></h:outputText>
					<h:selectOneMenu style="width:auto" valueChangeListener="#{peopleNearBy.selectedDistance}">
						<f:selectItem itemValue="5" value="5" itemLabel="5"/>
						<f:selectItem itemValue="10" value="10" itemLabel="10"/>
						<f:selectItem itemValue="25" value="25" itemLabel="25"/>
						<f:selectItem itemValue="50" value="50" itemLabel="50"/>
						<f:selectItem itemValue="100" value="100" itemLabel="100"/>
					</h:selectOneMenu>
					</h:panelGrid>
					<h:panelGrid>
						<h:commandButton action="#{peopleNearBy.searchNeighbors}" styleClass="btn btn-inverse"></h:commandButton>
					</h:panelGrid>
					</div>
			</h:form>
			<h:form>
			
			<div class = "form">
			<h:dataTable value="#{peopleNearBy.userList}" border="2" var="userDetails" styleClass="table table-hover" rules="all" dir="ltr" cellpadding="5">
				<h:column>
					<f:facet name="header">
						<h:column>
							<h:outputFormat style="font-weight: bold">First Name</h:outputFormat>
						</h:column>
					</f:facet>
					<h:outputText value="#{userDetails.fname}"></h:outputText>
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:column>
							<h:outputFormat style="font-weight: bold">Last Name</h:outputFormat>
						</h:column>
					</f:facet>
					<h:outputText value="#{userDetails.lname}"></h:outputText>
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:column>
							<h:outputFormat style="font-weight: bold">Street 1</h:outputFormat>
						</h:column>
					</f:facet>
					<h:outputText value="#{userDetails.street1}"></h:outputText>
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:column>
							<h:outputFormat style="font-weight: bold">Street 2</h:outputFormat>
						</h:column>
					</f:facet>
					<h:outputText value="#{userDetails.street2}"></h:outputText>
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:column>
							<h:outputFormat style="font-weight: bold">State</h:outputFormat>
						</h:column>
					</f:facet>
					<h:outputText value="#{userDetails.state}"></h:outputText>
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:column>
							<h:outputFormat style="font-weight: bold">City</h:outputFormat>
						</h:column>
					</f:facet>
					<h:outputText value="#{userDetails.city}"></h:outputText>
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:column>
							<h:outputFormat style="font-weight: bold">Zip</h:outputFormat>
						</h:column>
					</f:facet>
					<h:outputText value="#{userDetails.zip}"></h:outputText>
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:column>
							<h:outputFormat style="font-weight: bold">Distance from you</h:outputFormat>
						</h:column>
					</f:facet>
					<h:outputText value="#{userDetails.difference}"></h:outputText>
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:column>
							<h:outputFormat style="font-weight: bold">Mobile</h:outputFormat>
						</h:column>
					</f:facet>
					<h:outputText value="#{userDetails.mobile}"></h:outputText>
				</h:column>
				
			</h:dataTable>
			
			
			</div>
            </div>
            
            
            
        <div class="clear"></div>
          

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