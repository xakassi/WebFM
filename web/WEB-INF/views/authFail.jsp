<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Аутентификация</title>
</head>
<body>
Не удается войти. <br/>
Пожалуйста, проверьте правильность написания логина и пароля. <br/>
Возможно, нажата клавиша CAPS-lock?<br/>
Может быть, у Вас включена неправильная раскладка? (русская или английская)<br/>
Попробуйте набрать свой пароль в текстовом редакторе и скопировать в графу «Пароль».<br/>

<spring:form method="get" modelAttribute="userJSP" action="/">
    <spring:button>Попробовать снова</spring:button>
</spring:form>

<br/>
</body>
</html>