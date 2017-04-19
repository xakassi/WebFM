<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
Вы успешно зарегистрированы!
<spring:form method="get" modelAttribute="userJSP" action="/">
    <spring:button>Продолжить</spring:button>
</spring:form>

</body>
</html>
