<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Файловый менеджер</title>
</head>
<body>
<spring:form method="get" modelAttribute="userJSP">
    Здравствуйте, ${userJSP.login}!<br/>
    Здесь будут храниться ваши файлы... <br/>
    <img src="<c:url value="/resources/img/dir.png"/>" width="100px" height="100px">
    <img src="<c:url value="/resources/img/file.png"/>" width="100px" height="100px">
    <br/>
</spring:form>

<spring:form method="get" modelAttribute="userJSP" action="/">
    <spring:button>Выход</spring:button>
</spring:form>
</body>
</html>
