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
            	<h:outputFormat value="Notifications"></h:outputFormat>
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
                    <h:panelGrid columns="2">
                    <h:outputText value="Select search distance: "></h:outputText>
					<h:selectOneMenu style="width:auto" valueChangeListener="#{notifications.selectedDistance}" onchange="this.form.submit();" >
						<f:selectItem itemValue="Select a distance" value="Select distance" itemLabel="Select Distance"/>
						<f:selectItem itemValue="5" itemLabel="5"/>
						<f:selectItem itemValue="10" itemLabel="10"/>
						<f:selectItem itemValue="25" itemLabel="25"/>
						<f:selectItem itemValue="50" itemLabel="50"/>
						<f:selectItem itemValue="100" itemLabel="100"/>
					</h:selectOneMenu>
                    </h:panelGrid>
                    
					<h2>Requests for you</h2>
					
					<h:dataTable id="dynamicTable" binding="#{notifications.dataTable}" border="2" styleClass="table table-hover" rules="all" dir="ltr" cellpadding="5" value="#{notifications.packageList}" var="pkgDetails" >
						<h:column> 
							<f:facet name="header">
								<h:outputLabel value="Street1" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{pkgDetails.street1}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Street2" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{pkgDetails.street2}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="City" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{pkgDetails.city}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="State" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{pkgDetails.state}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Zip" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{pkgDetails.zip }"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Package Type" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{pkgDetails.pkgType }"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Delivery Date" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{pkgDetails.date }"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Comment" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{pkgDetails.comment }"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
							<h:column>
								<h:outputLabel value="Action" style="font-weight:bold"></h:outputLabel>
							</h:column>
							</f:facet>
							<h:panelGrid columns="4">
								<h:commandLink value="Approve" action="#{notifications.approveRequest}" id="approve">
									<f:setPropertyActionListener value="#{packageList}" target="#{notifications.packageList }"/>
								</h:commandLink>
								
							</h:panelGrid>	
						</h:column>
						
						
					</h:dataTable>
					
					
				<h2> Your Submitted Requests</h2>
				<h:dataTable id="dynamicTableReq" border="2" styleClass="table table-hover" rules="all" value="#{notifications.myPkgList }" var="myPkgDetails">
						<h:column> 
							<f:facet name="header">
								<h:outputLabel value="Street 1" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{myPkgDetails.street1}"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Street 2" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{myPkgDetails.street2 }"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="City" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{myPkgDetails.city }"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="State" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{myPkgDetails.state }"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="City" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{myPkgDetails.city }"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Zip" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{myPkgDetails.zip }"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Package Type" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{myPkgDetails.pkgType }"></h:outputText>
						</h:column>
						
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Delivery Date" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{myPkgDetails.date }"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Comment" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{myPkgDetails.comment }"></h:outputText>
						</h:column>
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Status" style="font-weight:bold"></h:outputLabel>
							</f:facet>
							<h:outputText value="#{myPkgDetails.status }"></h:outputText>
						</h:column>
						
					</h:dataTable>
						
					
                       
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