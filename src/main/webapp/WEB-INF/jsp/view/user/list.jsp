<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Users List</title>
</head>
<body>
<h2>Users list</h2>
<c:choose>
    <c:when test="${fn:length(users) == 0}">
        <p>
            <i>There are no users in the DB</i>
        </p>
    </c:when>
    <c:otherwise>
        <table border="0" cellspacing="3" cellpadding="5">
            <thead>
            <tr>
                <th>Id</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Email</th>
                <th>Address</th>
                <th>Phone number</th>
                <th>Age</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.firstName}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.address}"/></td>
                    <td><c:out value="${user.phoneNumber}"/></td>
                    <td><c:out value="${user.age}"/></td>
                    <td>
                        <a href="<c:url value="view">
                                           <c:param name="id" value="${user.id}" />
                                       </c:url>">
                            View
                        </a>
                        /
                        <a href="<c:url value="edit">
                                           <c:param name="id" value="${user.id}" />
                                       </c:url>">
                            Edit
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


    </c:otherwise>
</c:choose>
<a href="<c:url value="add" />">Add user</a>
</body>
</html>
</html>
