<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit user</title>
</head>
<body>
<h2>Edit user</h2>
<p>
    <a href="<c:url value="list" />">List all</a>
</p>
<p>
    <i>
        Created: <fmt:formatDate type="both" value="${user.dateCreated}" />
    </i>
</p>
<form action="edit" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    <table border="0" width="300" cellpadding="5">
        <tbody>
        <tr>
            <td>Nickname</td>
            <td><input type="text" name="nickName" value="${user.nickName}" readonly="true"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value="${user.password}"/></td>
        </tr>
        <tr>
            <td>First name</td>
            <td><input type="text" name="firstName" value="${user.firstName}"/></td>
        </tr>
        <tr>
            <td>Last name</td>
            <td><input type="text" name="lastName" value="${user.lastName}"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="${user.email}"/></td>
        </tr>
        <tr>
            <td>Address</td>
            <td><input type="text" name="address" value="${user.address}"/></td>
        </tr>
        <tr>
            <td>Phone number</td>
            <td><input type="text" name="phoneNumber" value="${user.phoneNumber}"/></td>
        </tr>
        <tr>
            <td>Birth date (21/10/1991)</td>
            <td>
                <input type="text" name="birthDate" value="<fmt:formatDate value="${user.birthDate}" pattern="dd.MM.yyyy" />"/>
            </td>
        </tr>
        <tr>
            <td>User role</td>
            <td><input type="text" name="role" value="${user.role}"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Submit"/></td>
        </tr>
        </tbody>
    </table>
</form>

</body>
</html>

