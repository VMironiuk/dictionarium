<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />

<ul id="main_menu">
  <li>${user.name}</li>
  <li>|</li>
  <li>Sign out</li>
</ul>
