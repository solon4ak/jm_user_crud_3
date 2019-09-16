<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View user</title>
</head>
<body>
<h2>View user</h2>

<a href="<c:url value="list" />">List all</a>
<br/>
<br/>
<table border="0" width="300" cellpadding="5">
    <tbody>
    <tr>
        <td>First name</td>
        <td>${user.firstName}</td>
    </tr>
    <tr>
        <td>Last name</td>
        <td>${user.lastName}</td>
    </tr>
    <tr>
        <td>Email</td>
        <td>${user.email}</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>${user.address}</td>
    </tr>
    <tr>
        <td>Phone number</td>
        <td>${user.phoneNumber}</td>
    </tr>
    <tr>
        <td>Age</td>
        <td>${user.age}</td>
    </tr>
    </tbody>
</table>
<br/>
<a href="<c:url value="edit">
               <c:param name="id" value="${user.id}" />
           </c:url>">
    Edit
</a> / <a href="<c:url value="delete">
               <c:param name="id" value="${user.id}" />
           </c:url>">
    Delete
</a>
</form>
</body>
</html>
