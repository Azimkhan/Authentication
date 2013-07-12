<%--
  Created by IntelliJ IDEA.
  User: azimkhan
  Date: 12.07.13
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
    <%@include file="jspf/header.jspf"%>
    <div id="content">
        <c:if test="${error != null}">
            <c:out value="${error}"/>
        </c:if>

        <h1>Authentication:</h1>
        <p>Please enter the system using your login and password</p>
        <form method="post" id="loginForm">
            <input type="hidden" name="command" value="login"/>
            <label for="${login_var}">Login:</label>
            <input type="text" name="${login_var}"/>
            <label for="${password_var}">Password:</label>
            <input type="password" name="${password_var}"/>
            <input type="submit" value="Log in"/>
        </form>
    </div>
</body>
</html>