<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="../layout/taglib.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>DOB</th>
				<th>SSN</th>
				<th>Street</th>
				<th>City</th>
				<th>State</th>
				<th>Zip</th>
				<th>User</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${contacts}" var="contact">
				<tr>
					<td>${contact.firstName}</td>
					<td>${contact.lastName}</td>
					<td>${contact.dob}</td>
					<td>${contact.SSN}</td>
					<td>${contact.street}</td>
					<td>${contact.city}</td>
					<td>${Contact.state}</td>
					<td>${Contact.zip}</td>
					<td><a
						href='<spring:url value="/contacts/remove/${Contact.id}.html" ></spring:url>'
						class="btn btn-danger triggerRemove">Remove Contact</a></td>
				</tr>
			</c:forEach>

			</td>
	</table>

</body>
</html>