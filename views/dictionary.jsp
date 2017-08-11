<%@ page isELIgnored="false" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:query var="tbl" dataSource="jdbc/dictionarium">
  select userid, username, userpassword, usertable from users
</sql:query>

<html>
  <head>
    <title>Dictionarium</title>
  </head>
  <body>

  <h2>Contents of the table 'users'</h2>

<c:forEach var="row" items="${tbl.rows}">
    User ID:       ${row.userid}<br/>
    User name:     ${row.username}<br/>
    User password: ${row.userpassword}<br/>
    User table:    ${row.usertable}<br/>
</c:forEach>

  </body>
</html> 
 
