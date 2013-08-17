Crowd_shipping
==============

CmpE 272- Group_8_Spirit

Installation Steps:

- Download and Install Tomcat 7
- Download and Install MySQL version 5 or higher
- Download and unzip twitter Bootstrap(though it is in CSS folder under Web Content)
- Download jstl and jsf libraries (though they are present in WEB-INF/lib folder)
- Download and  Install XAMPP (for MySQL administration through phpMyAdmin)
- Download and Install any IDE for Java EE development (Eclipse Indigo preferred)

The zip file of the source contains the following:
src - contains all the packages along with their respective java files i.e. source codes

Web Content 
  - CSS -- contains all style sheets and javascripts used for responsive UI
  - META-INF -- contains all the configuration files for this web application including the path for servlets and facelets
  - WEB-INF -- contains all the libraries needed by this web application along with the web configuration files
-- JSP files 

classpath and various other settings files.

You can import this project directly into your Eclipse IDE using EGit for eclipse. The read only URL for this project repo is at 
git://github.com/kashyap34/Crowd_shipping.git

To directly run this web app, create a war file using Eclipse (right click on project --> Export --> War file) and
then copy the war file in <Tomcat Installation Directory>/webapps. Restart Tomcat and access it in your browser through
http://localhost:8080/Crowd_Shipping/faces/Home.jsp
NOTE: Don't forget to add faces in URL else it will not be able to find the facelets path.

This code is free to copy, reuse and distribute without any consent. You can also contribute to make it better by working
with us.
