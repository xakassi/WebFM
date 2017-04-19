<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
Введенный логин уже занят!
<spring:form method="post" modelAttribute="userJSP" action="reg-user">
    <spring:button>Попробовать снова</spring:button>
</spring:form>

</body>
</html>
