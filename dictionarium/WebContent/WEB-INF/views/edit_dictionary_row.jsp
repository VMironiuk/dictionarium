<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Word</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
  </head>
  
  <body>
    <div id="container">
      <jsp:include page="_header.jsp" />
      <jsp:include page="_menu.jsp" />
      
      <p style="color: red">${errorString}</p>
      
      <c:if test="${not empty dictionaryRow}">
        <form method="POST" action="doEditDictionaryRow">
          <div class="fieldset">
            <input type="hidden" name="wordId" value="${dictionaryRow.wordId}" />
            <table border="0" width="100%">
              <tr>
                <td style="text-align: right">Word</td>
                <td><input type="text" name="word" value="${dictionaryRow.word}" /></td>
              </tr>
          
              <tr>
                <td style="text-align: right">Transcription</td>
                <td><input type="text" name="transcription" value="${dictionaryRow.transcription}" /></td>
              </tr>

              <tr>
                <td style="text-align: right">Translation</td>
                <td><input type="text" name="translation" value="${dictionaryRow.translation}" /></td>
              </tr>
          
              <tr>
                <td colspan="2" style="text-align: center">
                  <input type="submit" class="button" value="Submit" />
                  <a href="${pageContext.request.contextPath}/dictionary">Cancel</a>
                </td>
              </tr>
            </table>
          </div>
        </form>
      </c:if>
    </div>
  </body>
</html>