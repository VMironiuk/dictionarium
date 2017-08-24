<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dictionarium</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/style.css" />" />
  </head>
  
  <body>
    <c:if test="${not empty errorString}">
      <p style="color: red; text-align: center">${errorString}</p>
    </c:if>
    
    <div class="login-wrap">
      <div class="login-html">
        
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
        <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
        
        <div class="login-form">
          <form method="POST" action="doLogin">
            <div class="sign-in-htm">
              <div class="group">
                <label for="user" class="label">Username</label>
                <input type="text" id="user" name="userName" value="${user.userName}" class="input" />
              </div>
              
              <div class="group">
                <label for="pass" class="label">Password</label>
                <input type="password" id="pass" name="password" value="${user.password}" class="input" />
              </div>
              
              <div class="group">
                <input type="checkbox" id="check" name="rememberMe" value="Y" class="check" checked />
                <label for="check"><span class="icon"></span> Keep me Signed in</label>
              </div>
              
              <div class="group">
                <input type="submit" value="Sign In" class="button" />
              </div>
              
              <div class="hr"></div>
              <div class="foot-lnk">
                <a href="#forgot">Forgot Password?</a>
              </div>
            </div>
          </form>
			
          <form method="POST" action="doRegister">
            <div class="sign-up-htm">
              <div class="group">
                <label for="user" class="label">Username</label>
                <input type="text" name="userName" value="${user.userName}" class="input" />
              </div>
                
              <div class="group">
                <label for="pass" class="label">Password</label>
                <input type="password" name="password" class="input" />
              </div>
                
              <div class="group">
                <label for="pass" class="label">Repeat Password</label>
                <input type="password" name="repeatPassword" class="input" />
              </div>
                
              <div class="group">
                <label for="pass" class="label">Email Address</label>
                <input type="text" name="email" value="${user.email}" class="input /">
              </div>
                
              <div class="group">
                <input type="submit" value="Sign Up" class="button" />
              </div>
                
              <div class="hr"></div>
              <div class="foot-lnk">
                <label for="tab-1">Already Member?</label>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div> 
  </body>
</html>