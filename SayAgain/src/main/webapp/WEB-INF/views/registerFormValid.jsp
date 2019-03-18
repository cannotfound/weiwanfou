<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>register</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/main.css" />">
</head>
<body>

	<h1>
		<s:message code="spitter.registerForm.title" />
	</h1>
	<sf:form method="POST" commandName="spitter">
		<sf:errors path="*" element="div" cssClass="errors" />
		<sf:label path="firstName" cssErrorClass="error">First Name: </sf:label>
		<sf:input cssErrorClass="error" type="text" path="firstName" />
		<br />
		<sf:label path="lastName" cssErrorClass="error">Last Name: </sf:label>
		<sf:input cssErrorClass="error" type="text" path="lastName" />
		<br />
		<sf:label path="email" cssErrorClass="error">Email: </sf:label>
		<sf:input cssErrorClass="error" type="email" path="email" />
		<br />
		<sf:label path="username" cssErrorClass="error">Username: </sf:label>
		<sf:input cssErrorClass="error" type="text" path="username" />
		<br />
		<sf:label path="password" cssErrorClass="error">Password: </sf:label>
		<sf:password cssErrorClass="error" path="password" />
		<br />
		<input type="submit" value="Register" />
	</sf:form>

	<div>
		<li><a href="<s:url value="/spitter/jb"></s:url>">查看JB</a></li>

		<li><s:url value="/spittles" htmlEscape="true">
				<s:param name="max" value="1" />
				<s:param name="count" value="10" />
			</s:url></li>

		<li><s:url value="/spittles" htmlEscape="true">
				<s:param name="max" value="5" />
				<s:param name="count" value="20" />
			</s:url></li>
			
			<s:url value="/spitter/jc" var="jcUrl" />
			<s:url value="/spitter/jcc" var="max5Url" javaScriptEscape="true"></s:url>
            <a href="${jcUrl}">jcUrl</a>
			
			<div>
			<s:escapeBody htmlEscape="true">
			<h2>wo ding ni ge fei.</h2>
			<ul>shasha</ul>
			</s:escapeBody>
			</div>
	</div>
	
	<script>
	var myjcUrl = "${jcUrl}";
	
	var max5Url = "${max5Url}";
    
	</script>
</body>
</html>