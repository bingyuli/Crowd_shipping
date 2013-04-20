<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5 Transitional//EN" "http://www.w3.org/TR/html5/loose.dtd">
<html xmlns:o="http://omnifaces.org/ui"
	  xmlns:of="http://omnifaces.org/functions"
	  xmlns:hx="http://myface.apache.org/html5/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/adaria/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/bootstrap-responsive.css">

<script type="text/javascript" src="<%=request.getContextPath()%>/CSS/adaria/js/jquery-1.3.2.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/CSS/adaria/js/jquery-ui.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/CSS/bootstrap.js" ></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#featured > ul").tabs({fx:{opacity: "toggle"}}).tabs("rotate", 5000, true);
});

</script>

<script type="text/javascript">
	
	function setPlaceHolders()
	{
		document.getElementById("login:uname").setAttribute("placeholder", "test@abc.com");
		document.getElementById("login:uname").setAttribute("type", "email");
		document.getElementById("login:pwd").setAttribute("placeholder", "Password");
		var elements = document.getElementsByTagName("input");
		for (var i=0; i<elements.length; i++) 
		{
			elements[i].setAttribute("required", "required");
		}
	}
	
</script>

<!-- Cufon START  -->
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script src="js/GeosansLight_500.font.js" type="text/javascript"></script>
<script type="text/javascript">
Cufon.replace('.logo', { fontFamily: 'GeosansLight' });
Cufon.replace('h1', { fontFamily: 'GeosansLight' });
Cufon.replace('h2', { fontFamily: 'GeosansLight' });
Cufon.replace('.pages_banner', { fontFamily: 'GeosansLight' });
</script>    
<!-- Cufon END  -->

</head> 
<body onload="setPlaceHolders()">  
    
<f:view>
<h:form id="login">
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
    

	<div class="center_content_pages" align="center">
	<div class="pages_banner" style="left: 5%; top: 20%">
		<center>Login</center>
	</div>
		<h:panelGrid columns="2" style="panel-layout:fixed">
			<h:outputLabel id="email" styleClass="more">E-mail: </h:outputLabel>
			<h:inputText id="uname" value="#{login.username}" required="true" requiredMessage="E-mail is required" styleClass="form_input">
			</h:inputText>
				
			<h:outputLabel styleClass="more">Password: </h:outputLabel>
			<h:inputSecret id="pwd" value="#{login.password}" required="true" requiredMessage="Password is required" styleClass="form_input"></h:inputSecret>
			
		</h:panelGrid>
	<h:panelGrid >
		<h:commandButton id="btnLogin" action="#{login.checkUser}" value="Login" styleClass="btn btn-inverse" style="left: 50%"></h:commandButton>
	</h:panelGrid>
	
	<h:outputLabel id="error1" style="visibility: hidden"></h:outputLabel>
	<h:messages layout="table"> 
	
	</h:messages>		
	
	<div class="clear"></div>
	</div>
<br/><br/><br/><br/><br/><br/><br/><br/>	
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