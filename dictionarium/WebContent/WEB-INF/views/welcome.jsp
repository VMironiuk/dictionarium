<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dictionary</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
  </head>
  
  <body>
    <div id="container">
      <jsp:include page="_header.jsp" />

      <div style="text-align: center">
        <h2>Welcome to Dictionarium!</h2>
        <h5>You can <a href="${pageContext.request.contextPath}/login">login</a> freely now.</h5>
      </div>
     </div>
  </body>
</html>