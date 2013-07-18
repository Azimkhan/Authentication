<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<%@include file="../WEB-INF/jspf/header.jspf"%>
<fmt:setBundle basename="kz.enu.epam.azimkhan.auth.resource.message"/>

<div class="content">

    <h1><fmt:message key="main.intro.title"/></h1>
    <p><fmt:message key="main.intro.body"/></p>
</div>
</body>
</html>