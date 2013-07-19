<%--
  Created by IntelliJ IDEA.
  User: azimkhan
  Date: 12.07.13
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>

    <%@include file="../WEB-INF/jspf/header.jspf"%>
    <fmt:setLocale value="${locale}"/>


    <div class="content">

        <h1><fmt:message key="auth.page.title"/> </h1>
        <p><fmt:message key="auth.page.message"/> </p>
        <form method="post" id="loginForm">
            <input type="hidden" name="command" value="login"/>
            <label for="${login_var}"><fmt:message key="auth.login_form.login"/> :</label>
            <input type="text" name="${login_var}"/>
            <label for="${password_var}"><fmt:message key="auth.login_form.password"/>:</label>
            <input type="password" name="${password_var}"/>
            <input type="submit" value="<fmt:message key="auth.login_form.submit"/>"/>
        </form>
    </div>
</body>
</html>