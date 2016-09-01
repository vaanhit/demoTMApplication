<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ include file="../layout/taglib.jsp"%>

<form action="<c:url value="/login" />"	method='POST'>
	<h2>
		Please sign in
	</h2>
	<input type="text" name="username" id="inputEmail" 
		placeholder="Name" required autofocus> 
	<input type="password" name="password" id="inputPassword" 
		placeholder="Password" required>
	<button type="submit">
		Submit
	</button>
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>
