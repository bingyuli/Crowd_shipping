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
<script type="text/javascript" src="<%=request.getContextPath()%>/CSS/bootstrap-dropdown.js" ></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#featured > ul").tabs({fx:{opacity: "toggle"}}).tabs("rotate", 5000, true);
});

</script>

<script type="text/javascript">
	
	function setPlaceHolders()
	{
		document.getElementById("home:uname").setAttribute("placeholder", "test@abc.com");
		document.getElementById("home:uname").setAttribute("type", "email");
		document.getElementById("home:pwd").setAttribute("placeholder", "Password");
		
	 	var elements = document.getElementsByTagName("input");
		for (var i=0; i<elements.length; i++) 
		{
			elements[i].setAttribute("required", "required");
		} 
		//$('.dropdown-toggle').toggle();
	}
	
</script>
<script type="text/javascript">
	$(document).ready(function(){
		$('.dropdown-toggle').click(function(){
			$('.dropdown-menu').toggle();	
		});
		
	});

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
	<h:form id="home">
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
    <!-- <div class="masthead">
        <ul class="nav nav-pills">
          <li class="active"><a href="#">Home</a></li>
          <li><a href="#">About</a></li>
          <li><a href="#">Contact</a></li>
        </ul>
        
    </div> -->
    
    <hr>
    
    
    <div class="jumbotron">
        <h1 align="center" >Crowd Shipping </h1>
             
             
                <h2 align="center" style="font-family:arial;color:black;font-size:20px;" >Error</h2>
        <p class="lead" style="font-size:20px;" ><i> There was a problem in processing your request please click on GO Back to continue.</i></p>
        <center><a class="btn btn-large btn-inverse" href="Login.jsp?faces-redirect=true">Go Back</a></center>
      </div>
	
	</div>
	</h:form>

</f:view>
</body>
</html>