<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<body bgcolor="green">

<h1>Hi ${username} !!</h1>

<form:form action="logout" method="POST">

    <input type="submit" value="Logout">

</form:form>


</body>
</html>