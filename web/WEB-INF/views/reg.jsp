<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>

<spring:form method="post" modelAttribute="userJSP" action="checkLogin">

    <h2>Регистрация</h2>
    Логин: <spring:input path="login"/> <br/>
    Пароль: <spring:password path="password"/> <br/>
    <spring:button>Продолжить</spring:button>
</spring:form>

<span style="color: red; ">${message}</span>


</body>
</html>
