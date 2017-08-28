<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome!</title>
  </head>
  
  <body>
    <div style="text-align: center">
      <h2>Welcome to Dictionarium, ${user.name}!</h2>
      <h5>You can <a href="${pageContext.request.contextPath}/login">login</a> freely now.</h5>
    </div>
  </body>
</html>