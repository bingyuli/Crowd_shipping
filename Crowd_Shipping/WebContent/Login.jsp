<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5 Transitional//EN" "http://www.w3.org/TR/html5/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/adaria/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/bootstrap.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/CSS/adaria/js/jquery-1.3.2.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/CSS/adaria/js/jquery-ui.min.js" ></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#featured > ul").tabs({fx:{opacity: "toggle"}}).tabs("rotate", 5000, true);
});
</script>
</head> 
<body>  
    
<f:view>
<h:form id="login">

<script type="text/javascript">
	function checkPassword()
	{
		var pwd = document.getElementById("login:pwd").value;
		
		if(/[a-z]/.test(pwd) && /[A-Z]/.test(pwd) && /\d/.test(pwd) && /[_\W]/.test(pwd))
		{
			document.getElementById("login:error1").style.display = 'none';
			return true;
		}
		else
		{
			//document.write("Its inside else!");
			document.getElementById("login:error1").style.visibility = 'visible';
			document.getElementById("login:error1").innerHTML = "Error in password";
			return false;
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

	<div class="center_content_pages" align="center">
	<div class="pages_banner" style="left: 5%; top: 20%">
		<center>Login</center>
	</div>
		<h:panelGrid columns="2" style="panel-layout:fixed">
			<h:outputLabel id="email" styleClass="more">E-mail: </h:outputLabel>
			<h:inputText id="uname" value="#{login.username}" required="true" requiredMessage="E-mail is required" onfocus="if(this.value == 'myexample@abc.com') this.value=''" styleClass="form_input">
			</h:inputText>
			<h:outputLabel styleClass="more">Password: </h:outputLabel>
			<h:inputSecret id="pwd" value="#{login.password}" onkeypress="checkPassword()" onblur="checkPassword()" styleClass="form_input"></h:inputSecret>
		</h:panelGrid>
	<h:panelGrid >
		<h:commandButton id="btnLogin" action="#{login.checkUser}" value="Login" styleClass="btn btn-success" style="position: fixed; left: 50%"></h:commandButton>
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