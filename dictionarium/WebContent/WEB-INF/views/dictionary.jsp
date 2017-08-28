<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dictionary</title>
  </head>
  
  <body>
    <jsp:include page="_header.jsp" />

    <br>
    
    <a href="addWord">Add Word</a>
    
    <table border="1" cellpadding="5" cellspacing="1">
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
            <a href="editDictionaryRow?wordId=${row.wordId}">Edit</a>
          </td>
          <td>
            <a href="deleteDictionaryRow?wordId=${row.wordId}">Delete</a>
          </td>
        </tr>
      </c:forEach>
      
    </table>
  </body>
</html>