<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
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
<body>
<f:view>
	<h:form id="home">
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
    <div class="masthead">
        <ul class="nav nav-pills">
          <li class="active"><a href="#">Home</a></li>
          <li><a href="#">About</a></li>
          <li><a href="#">Contact</a></li>
        </ul>
        
    </div>
    <hr>
    <div class="jumbotron">
        <h1 align="center">Crowd Shipping!!</h1>
        <h2 align="center">Where people work for other people</h2>
        <p class="lead">We had a vision that if I can help my friends get their packages delivered to my place of visit why cant I make new friends by doing delivery on their behalf. After all some day some one will do the same for me.</p>
        <center><a class="btn btn-large btn-inverse" href="Signup.jsp">Sign up today</a></center>
      </div>
	
	</div>
	</h:form>

</f:view>
</body>
</html>