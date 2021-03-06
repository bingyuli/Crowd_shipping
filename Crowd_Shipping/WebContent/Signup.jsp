<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/adaria/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/bootstrap-responsive.css">

</head>
<body onload="setPlaceHolders()">
<f:view>
<h:form id="Signup">
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
		var placeholders = ["Larry","Page","test@abc.com","Password","Confirm Password", "San Jose State University", "One Washington Square", "San Jose", "CA", "USA", "XXXXX", "XXXXXXXXXX"];
		var elements = document.getElementsByTagName("input");
		for (var i=0; i<elements.length; i++) 
		{
			elements[i].setAttribute("required", "required");
			elements[i].setAttribute("placeholder", placeholders[i]);
		}
		document.getElementById("Signup:email").setAttribute("type", "email");
	}
	
</script>

	
	<div class="center_content_pages" > 
	<div class="pages_banner" style="left: 5%; top: 20%">
		<center>Sign up with the crowd</center>
	</div>
	<table style="width:auto" align="center">
	<tr>
		<td ><h:outputLabel >First Name: </h:outputLabel></td>
		<td><h:inputText id="fname" value="#{Signup.fname}"  styleClass="form_input" onfocus="if(this.value == 'Bob') this.value=''"></h:inputText></td>
		<td align="right"><h:outputLabel style="">Last Name: </h:outputLabel></td>
		<td><h:inputText id="lname" value="#{Signup.lname}" styleClass="form_input" onfocus="if(this.value == 'Jones') this.value=''"></h:inputText></td>
	</tr>
	<tr>
		<td><h:outputLabel>Email: </h:outputLabel></td>
		<td><h:inputText id="email" value="#{Signup.email}" styleClass="form_input" onfocus="if(this.value == 'myexample@abc.com') this.value=''"></h:inputText></td>		
	</tr>
	<tr>
		<td><h:outputLabel>Password: </h:outputLabel></td>
		<td><h:inputSecret id="pwd" value="#{Signup.password}" styleClass="form_input"></h:inputSecret></td>
	
		<!--  <td><h:outputLabel>Password must be 0-20 characters long and must contain a number, a letter, a capital letter and a special character. </h:outputLabel></td>-->
		<td><h:outputLabel>Confirm Password: </h:outputLabel></td>
		<td><h:inputSecret id="conpwd" value="" styleClass="form_input"></h:inputSecret></td>
	</tr>
	<tr>	
		<td><h:outputLabel>Street 1: </h:outputLabel></td>
		<td><h:inputText id="street1" value="#{Signup.street1}" styleClass="form_input"></h:inputText></td>
		<td align="right"><h:outputLabel>Street 2: </h:outputLabel></td>
		<td><h:inputText id="street2" value="#{Signup.street2}" styleClass="form_input"></h:inputText></td>
	</tr>
	<tr>	
		<td><h:outputLabel>City: </h:outputLabel></td>
		<td><h:inputText id="city" value="#{Signup.city}" styleClass="form_input"></h:inputText></td>
		<td align="right"><h:outputLabel>State: </h:outputLabel></td>
		<td><h:inputText id="state" value="#{Signup.state}" styleClass="form_input"></h:inputText></td>
	<tr>	
		<td><h:outputLabel>Country: </h:outputLabel></td>
		<td><h:inputText id="country" value="#{Signup.country}" styleClass="form_input"></h:inputText></td>
		<td align="right"><h:outputLabel>Zip: </h:outputLabel></td>
		<td><h:inputText id="zip" value="#{Signup.zip}" maxlength="5" styleClass="form_input"></h:inputText></td>
	</tr>
	<tr>
		<td><h:outputLabel>Mobile: </h:outputLabel></td>
		<td><h:inputText id="mobile" value="#{Signup.mobile}" maxlength="10" styleClass="form_input"></h:inputText></td>
	</tr>
	</table>
		<div style="text-align: center">
		<h:commandButton id="btnsignup" action="#{Signup.registerUser}" styleClass="btn btn-inverse" value="Signup"></h:commandButton>
		</div>
		<h:outputLabel id="error1" style="visibility: hidden"></h:outputLabel>
		
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
	
	</div>
	</h:form>

</f:view>
</body>
</html>