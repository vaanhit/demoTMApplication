<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
		$(document).ready(
				function() {
					$('.nav-tabs a:first').tab('show') // Select first tab 
					$(".triggerRemove").click(
							function(e) {
								e.preventDefault();
								$("#modalRemove .removebtn").attr("href",
										$(this).attr("href"));
								$("#modalRemove").modal();

							});

				});
	</script>

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
					<td>${contact.ssn}</td>
					<td>${contact.street}</td>
					<td>${contact.city}</td>
					<td>${contact.state}</td>
					<td>${contact.zip}</td>
					<td><a
						href='<spring:url value="/contacts/remove/${Contact.id}.html" ></spring:url>'
						class="btn btn-danger triggerRemove">Remove Contact</a></td>
				</tr>
			</c:forEach>

			</td>
	</table>

	<!-- Modal -->
	<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Remove Contact(s)</h4>
				</div>
				<div class="modal-body">Do you really want to remove this
					contact(s)?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						Cancel</button>
					<a href="" class="btn btn-danger  removebtn">Save</a>
				</div>

			</div>
		</div>
	</div>
	</div>
</body>
</html>