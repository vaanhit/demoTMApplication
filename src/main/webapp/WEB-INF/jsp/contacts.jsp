<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
<style type="text/css">
/* tfoot input {
	width: 100%;
	padding: 3px;
	box-sizing: border-box;
 */
tfoot {
	display: table-header-group;
}
}
</style>


<script type="text/javascript">
	$(document).ready(function() {
		$('.nav-tabs a:first').tab('show') // Select first tab 
		$(".triggerRemove").click(function(e) {
			e.preventDefault();
			$("#modalRemove .removebtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();

		});

	});

	$(document)
			.ready(
					function() {
						var table = $('#example').DataTable();

						$("#example tfoot th")
								.each(
										function(i) {
											var select = $(
													'<select><option value=""></option></select>')
													.appendTo($(this).empty())
													.on(
															'change',
															function() {
																table
																		.column(i)
																		.search($(this).val())
																		.draw();
															});

											table
													.column(i)
													.data()
													.unique()
													.sort()
													.each(
															function(d, j) {
																select
																		.append('<option value="'+d+'">'
																				+ d
																				+ '</option>')
															});
										});
					});
</script>

<title>Insert title here</title>
</head>
<body>
	<div>
	<table id="example"
		class="table table-bordered table-hover table-striped display">
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
					<td>${contact.userName}</td>
					<td>
					<button type="button" class="btn  btn-primary"
						data-toggle="modal" data-target="#myModal1">
						Edit
					</button>
					<td><a
						href='<spring:url value="/contacts/remove/${contact.id}.html" ></spring:url>'
						class="btn btn-danger triggerRemove">Remove Contact</a>
					</td>
					
				</tr>
			</c:forEach>
	</table>

	<!-- ---------------------- Start: Contact Delete Confirmation Message ----------------  -->
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
					<a href="" class="btn btn-danger  removebtn">Delete</a>
				</div>

			</div>
		</div>
	</div>
	</div>
	<!-- ---------------------- End: Contact Delete Confirmation Message ----------------  -->
	
	<!-- ---------------------- Start: Add new Contact ----------------------------------  -->
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary btn-lg"
		data-toggle="modal" data-target="#myModal1">
		Add Contact
	</button>
	
	<!-- ------------------------- start : pop up for adding New Contact ----------------- -->
	<form:form commandName="contact" action="contacts/addContact.html" method="POST" cssClass="form-horizontal  contactForm">
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<!-- Start: Add Header in the popup -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						Add Contact
					</h4>
				</div>
				<!-- End: Add Header in the popup -->
				<!-- Start: body of the popup -->
				<div class="modal-body">
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">
							First Name:
						</label>
						<div class="col-sm-10">
							<form:input path="firstName" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="lastName" class="col-sm-2 control-label">
							Last Name:
						</label>
						<div class="col-sm-10">
							<form:input path="lastName" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="dob" class="col-sm-2 control-label">
							DOB:
						</label>
						<div class="col-sm-10">
							<form:input path="dob" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="ssn" class="col-sm-2 control-label">
							SSN:
						</label>
						<div class="col-sm-10">
							<form:input path="ssn" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="street" class="col-sm-2 control-label">
							Street:
						</label>
						<div class="col-sm-10">
							<form:input path="street" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="city" class="col-sm-2 control-label">
							City:
						</label>
						<div class="col-sm-10">
							<form:input path="city" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="state" class="col-sm-2 control-label">
							State:
						</label>
						<div class="col-sm-10">
							<form:input path="state" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="zip" class="col-sm-2 control-label">
							Zip:
						</label>
						<div class="col-sm-10">
							<form:input path="zip" cssClass="form-control" />
						</div>
					</div>
				</div>
				<!-- End: body of the popup -->
				<!-- Start: footer of the popup -->
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						Close
					</button>
					<input type="submit" class="btn btn-primary" value="Save" />

				</div>
				<!-- End: footer of the popup -->
			</div>


		</div>
	</div>
</form:form>
	<!-- ----------------------End:Add new Contact--------------------------------- -->
		
</div>
</body>
</html>