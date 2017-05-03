<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Файловый менеджер</title>
</head>
<body>

<spring:form method="post" modelAttribute="userJSP" action="check-user">

    <h2>Добро пожаловать!</h2>
    Логин: <spring:input path="login"/>
    <br/>
    Пароль: <spring:password path="password"/> <br/>
    <spring:button>Войти</spring:button>

</spring:form>

<span style="color: red; ">${message}</span>
<br/><br/><br/>

<img src="<c:url value="/resources/img/filesImg.png"/>">

<spring:form method="post" modelAttribute="userJSP" action="reg-user">

    <h2>Впервые на нашем сайте?</h2>

    <spring:button>Зарегистрироваться</spring:button>

</spring:form>


</body>

</html>