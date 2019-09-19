<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add user</title>
</head>
<body>
<h2>New user</h2>
<form action="add" method="post">
    <table border="0" width="300" cellpadding="5">
        <tbody>
        <tr>
            <td>Nickname</td>
            <td><input type="text" name="nickName"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>First name</td>
            <td><input type="text" name="firstName"/></td>
        </tr>
        <tr>
            <td>Last name</td>
            <td><input type="text" name="lastName"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td>Address</td>
            <td><input type="text" name="address"/></td>
        </tr>
        <tr>
            <td>Phone number</td>
            <td><input type="text" name="phoneNumber"/></td>
        </tr>
        <tr>
            <td>Birth date (21.10.1991)</td>
            <td><input type="text" name="birthDate"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Submit"/></td>
        </tr>
        </tbody>
    </table>

</form>
<p>
    <a href="<c:url value="list" />">List users</a>
</p>

</body>
</html>
