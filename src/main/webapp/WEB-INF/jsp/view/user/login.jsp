<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login page</title>
</head>
<body>
<h2>Login</h2>

<c:if test="${loginFailed}">
    <p>
        <i>
            The username or password you entered are not correct. <br />
            Please supply the correct data.
        </i>
    </p>
</c:if>
<form action="login" method="post">
    <table border="0" width="300" cellpadding="5">
        <tbody>
        <tr>
            <td>Login</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Submit"/></td>
        </tr>
        </tbody>
    </table>
</form>

</body>
</html>
