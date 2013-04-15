<%@page import="jsf.example.Login"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/adaria/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/bootstrap.css">
</head>

<body onload="checkLogin()">
 
<f:view>
<h:form id="Profile">
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
    <div class="menu">
        <ul>
        <li><a href="index.html">Home</a></li>
        <li><a href="about.html">About Us<!--[if IE 7]><!--></a><!--<![endif]-->
        <!--[if lte IE 6]><table><tr><td><![endif]-->
            <ul>
            <li><a href="about.html" title="">Out Team</a></li>
            <li><a href="about.html" title="">Departments</a></li>
            <li><a href="about.html" title="">Locations</a></li>
            </ul>
        <!--[if lte IE 6]></td></tr></table></a><![endif]-->
        </li>
        <li><a href="#">Services<!--[if IE 7]><!--></a><!--<![endif]-->
        <!--[if lte IE 6]><table><tr><td><![endif]-->
            <ul>
            <li><a href="" title="">Webdesign</a></li>
            <li><a href="" title="">Programming</a></li>
            <li><a href="" title="">Development</a></li>
            <li><a href="" title="">Coding</a></li>
            </ul>
        <!--[if lte IE 6]></td></tr></table></a><![endif]-->
        </li>
        <li><a href="">Portfolio</a></li>
        <li><a href="">Testimonials</a></li>
        <li><a href="">Blog</a></li>
        <li><a href="contact.html">Contact</a></li>
        </ul>
    </div>
    
	<div class="center_content_pages">
        	<div class="pages_banner" style="left: 5%; top: 20%">
            <center>
            	<h:outputFormat value="Welcome #{login.username}"></h:outputFormat>
            </center>
            </div>
        	<div class="left_content"> 
                       
                <div class="box290">
                <h2>Navigate</h2>
                    <ul class="left_menu">
                        <li><h:commandLink action="#{profile.signout}" value="SignOut" ></h:commandLink></li>
                        <li>Notifications <span class="badge badge-important">6</span></li>
                        <li><a href="about.html" title="" >Locations</a></li>
                    </ul>
                </div>
                
                <div class="box290">
                  <h2>What clients say?</h2>
                      <div class="testimonial">
                            <div class="testimonial_quotes">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            <a href="#">John doe - company name</a>
                            </div>
                      </div> 
                      <div class="testimonial">
                            <div class="testimonial_quotes">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            <a href="#">John doe - company name</a>
                            </div>
                      </div>           
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
					
				<h2> Tell us about your plans</h2>
				<p> Where are you headed ?</p>
				<h:panelGrid columns="4">
					<h:outputLabel>From: </h:outputLabel>
					<h:inputText id="source" value="#{profile.fromCity}"></h:inputText>
					
					<h:outputLabel>Zip: </h:outputLabel>
					<h:inputText id="sourceZip" value="#{profile.fromZip}"></h:inputText>
					
					<h:outputLabel>To: </h:outputLabel>
					<h:inputText id="destination" value="#{profile.destination}"></h:inputText>
					
					<h:outputLabel>Zip: </h:outputLabel>
					<h:inputText id="destinationZip" value="#{profile.destination}"></h:inputText>
				</h:panelGrid>
				<h:panelGrid columns="4">
					<h:outputLabel>When: </h:outputLabel>
					<h:selectOneMenu style="width:auto">
						<f:selectItems value="#{profile.dayValue}"/>
					</h:selectOneMenu>
					<h:selectOneMenu style="width:auto">
						<f:selectItems value="#{profile.monthValue}"/>
					</h:selectOneMenu>
					<h:selectOneMenu style="width:auto">
						<f:selectItems value="#{profile.yearValue}"/>
					</h:selectOneMenu>
				</h:panelGrid>
				<h:panelGrid>
					<h:commandButton value="Submit" styleClass="btn btn-success" style="width:auto; left:50%"></h:commandButton>
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