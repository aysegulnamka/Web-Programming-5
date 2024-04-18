<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie With JSP</title>
<style>
	h1 {
		margin-left: 500px
	}
	form {
		margin-left: 500px;
		margin-right: 600px;
	}
    table {
        border: 2px solid red;
  		border-radius: 8px;
  		padding: 25px 50px 75px 100px;
    }
    td {
        padding: 5px; 
    }
</style>
</head>
<body>

<h1>SIGN IN FORM</h1>
<form method="post" action="AccountController">
	<fieldset>
    <legend> Login </legend>
     
          ${error}
          <table>
              <tr>
                 <td>Username</td>
                 <td><input type="text" name="username"></td>
              </tr>
              <tr>
                 <td>Password</td>
                 <td><input type="password" name="password"></td>
              </tr>
              <tr>
                 <td>&nbsp;</td>
                 <td><input type="checkbox" name="remember">Remember Me</td>
              </tr>
              <tr>
                 <td>&nbsp;</td>
                 <td><input type="submit" value="login"></td>
              </tr>
          </table>
	</fieldset>
</form>
</body>
</html>