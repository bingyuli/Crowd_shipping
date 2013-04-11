<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/adaria/style.css">
</head>
<body>
<div id="main_container">

       
	<div class="header">

            <div class="logo"><a href="index.html">Crowd Shipping</a></div>
            <div class="slogan">| Shipping for all</div> 
        
			<div class="header_socials">
            <a href="#"><img src="images/rss.png" alt="" title="" border="0" /></a>
            <a href="#"><img src="images/facebook.png" alt="" title="" border="0" /></a>
            <a href="#"><img src="images/linkedin.png" alt="" title="" border="0" /></a>
            <a href="#"><img src="images/twitter.png" alt="" title="" border="0" /></a>
            <a href="#"><img src="images/flickr.png" alt="" title="" border="0" /></a>
            </div>

    </div> <!--end of header-->
    </div><f:view>
	<h:form id="Signup">
	<div id="center">
	<h:panelGroup id="PanelGrp" >
	<h:panelGrid columns="4" >
		<h:outputLabel>First Name: </h:outputLabel>
		<h:inputText id="fname" value="#{Signup.fname}" onfocus="if(this.value == 'Bob') this.value=''"></h:inputText>
		<h:outputLabel>Last Name: </h:outputLabel>
		<h:inputText id="lname" value="#{Signup.lname}" onfocus="if(this.value == 'Jones') this.value=''"></h:inputText>
	</h:panelGrid>
	<h:panelGrid columns="2" >
		<h:outputLabel>Email: </h:outputLabel>
		<h:inputText id="email" value="#{Signup.email}" onfocus="if(this.value == 'myexample@abc.com') this.value=''"></h:inputText>		
	</h:panelGrid>
	<h:panelGrid columns="3" >
		<h:outputLabel>Password: </h:outputLabel>
		<h:inputSecret id="pwd" value="" onkeypress="checkPassword()" onblur="checkPassword()"></h:inputSecret>
		<h:outputLabel>Password must be 0-20 characters long and must contain a number, a letter, a capital letter and a special character. </h:outputLabel>
		<h:outputLabel>Confirm Password: </h:outputLabel>
		<h:inputSecret id="conpwd" value="" onkeypress="checkPassword()" onblur="checkPassword()"></h:inputSecret>
	</h:panelGrid>
	<h:panelGrid columns="2" >	
		<h:outputLabel>Street 1: </h:outputLabel>
		<h:inputText id="street1" value="#{Signup.street1}" onfocus="if(this.value == 'Bob') this.value=''"></h:inputText>
		<h:outputLabel>Street 2: </h:outputLabel>
		<h:inputText id="street2" value="#{Signup.street2}" onfocus="if(this.value == 'Bob') this.value=''"></h:inputText>
	</h:panelGrid>
	<h:panelGrid columns="4" >	
		<h:outputLabel>City: </h:outputLabel>
		<h:inputText id="city" value="#{Signup.city}" onfocus="if(this.value == 'Bob') this.value=''"></h:inputText>
		<h:outputLabel>State: </h:outputLabel>
		<h:inputText id="state" value="#{Signup.state}" onfocus="if(this.value == 'Bob') this.value=''"></h:inputText>
		<h:outputLabel>Country: </h:outputLabel>
		<h:inputText id="country" value="#{Signup.country}" onfocus="if(this.value == 'Bob') this.value=''"></h:inputText>
		<h:outputLabel>Zip: </h:outputLabel>
		<h:inputText id="zip" value="#{Signup.zip}" onfocus="if(this.value == 'Bob') this.value=''"></h:inputText>
	</h:panelGrid>
	<h:panelGrid columns="2" >
		<h:outputLabel>Mobile: </h:outputLabel>
		<h:inputText id="mobile" value="#{Signup.mobile}" onfocus="if(this.value == 'Bob') this.value=''"></h:inputText>
	</h:panelGrid>
		
		<h:commandButton action="#{Signup.registerUser}" value="Signup" styleClass="center"></h:commandButton>
	</h:panelGroup>
	</div>
	</h:form>

</f:view>
</body>
</html>