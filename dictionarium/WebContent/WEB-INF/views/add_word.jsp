<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Word</title>
  </head>
  
  <body>
    <form method="POST" action="doAddWord">
      <table border="0">
        <tr>
          <td>Word</td>
          <td><input type="text" name="word" /></td>
        </tr>
        
        <tr>
          <td>Transcription</td>
          <td><input type="text" name="transcription" /></td>
        </tr>
        
        <tr>
          <td>Translation</td>
          <td><input type="text" name="translation" /></td>
        </tr>
        
        <tr>
          <td colspan="2">
            <input type="submit" value="Submit" />
            <a href="dictionary">Cancel</a>
          </td>
        </tr>
      </table>
    </form>
  </body>
</html>