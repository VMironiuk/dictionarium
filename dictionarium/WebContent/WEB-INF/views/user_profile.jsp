<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Profile</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
  </head>
  
  <body>
    <div id="container">
      <jsp:include page="_header.jsp" />
      
      <p style="color: red;">${errorString}</p>
      
      <div class="fieldset">
      <p>
        User: ${user.name}
      </p>
      
      <p>
        <a href="${pageContext.request.contextPath}/deleteUserProfile">Delete user profile</a>
        <a href="${pageContext.request.contextPath}/dictionary">   Go to dictionary</a>
        <a href="${pageContext.request.contextPath}/signOut">   Sign out</a>
      </p>
      </div>
     </div>
  </body>
</html>