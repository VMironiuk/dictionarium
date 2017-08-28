<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dictionary</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/dictionary_style.css" />" />
  </head>
  
  <body>
    <div id="container">
      <jsp:include page="_header.jsp" />
      <jsp:include page="_menu.jsp" />
      
      <p style="color: red">${errorString}</p>
      
      <div class="fieldset">
        <form action="addWord">
          <input type="submit" class="button" value="Add New Word" />
        </form>
      
        <table border="0" cellpadding="5" cellspacing="1">
          <tr>
            <th>Word</th>
            <th>Transcription</th>
            <th>Translation</th>
            <th>Edit</th>
            <th>Delete</th>
          </tr>
      
          <c:forEach var="row" items="${dictionary}">
            <tr>
              <td>${row.word}</td>
              <td>${row.transcription}</td>
              <td>${row.translation}</td>
              <td>
                <a href="editDictionaryRow?wordId=${row.wordId}">
                  <img src="<c:url value="/image/edit.png" />"  alt="Edit" >
                </a>
              </td>
              <td>
                <a href="doDeleteDictionaryRow?wordId=${row.wordId}">
                  <img src="<c:url value="/image/delete.png" />"  alt="Delete" >
                </a>
              </td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>
  </body>
</html>