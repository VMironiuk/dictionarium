<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />

<ul id="main_menu">
  <li><a href="${pageContext.request.contextPath}/userProfile">${user.name}</a></li>
  <li>|</li>
  <li><a href="${pageContext.request.contextPath}/signOut">Sign out</a></li>
</ul>
