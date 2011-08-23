<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="javax.jdo.Query" %>
<%@ page import="com.google.appengine.api.datastore.Blob" %>
<%@ page import="gozolabs.com.PMF" %>
<%@ page import="gozolabs.com.entities.AroundImages" %>
<%@ page import="java.util.Iterator" %>

<html>
  <head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
  </head>

  <body>
  
<%
	    PersistenceManager pm = PMF.get().getPersistenceManager();
	    Query query = pm.newQuery(AroundImages.class);
	    List<AroundImages> results = (List<AroundImages>)query.execute();

   if (results.isEmpty()) {
        %>
        <p>AroundImages has no images.</p>
        <%
    } else {
    	Iterator itr = results.iterator();
    	while (itr.hasNext()) {
    	    AroundImages myImage = (AroundImages)itr.next();
        	Blob image = myImage.getImage();

        %>
        <p><img src="/doimage?name=<%=myImage.getFileName()%> ">
        <% 
        	} 
        }%>
  </body>
</html>