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
		}
		 */
	tfoot {
		display: table-header-group;
	}
	
	table td.first {
		display: none;
	}
</style>

<script>
$(document).ready(function(){
    $('#example1').dataTable();
});
</script>


<script type="text/javascript">
	$(document).ready(function() {
		//$('.nav-tabs a:first').tab('show'); // Select first tab 
		$(".triggerRemove").click(function(e) {
			e.preventDefault();
			$("#modalRemove .removebtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();   
		});
		
		$(".triggerRemoveAll").click(function(e) {
			removeRowUsingAjax();
			 /* e.preventDefault();
			$("#modalRemove .removebtn").click(function() {
				removeRowUsingAjax();	
			});
			$("#modalRemove").modal(); */  
		}); 
		
		function removeRowUsingAjax() {
			var values = new Array();
			$.each($("input[name='case[]']:checked").closest("td").next("td"),
				function() {
				 values.push($(this).text());
			});
			if (values == "") {
				alert("Please select some value");
				return;
			}
			
			var jsonString =JSON.stringify(values); 
			
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url: "<spring:url value='/contacts/removeM.html'/>",
				//dataType	 : 'json', 
				data	: jsonString,//JSON.stringify(jsonString),
				cache: false, 
				processData:false,
				success	: function (data) {
					$.each($("input[name='case[]']:checked").parents("tr").remove());
				},
				error	: function (e) {
					alert("error");
				}				
			});
		}
		
		var table = $('#example1').DataTable();
		$("#example1 tfoot th").each(function(i) {
			var select = $('<select><option value=""></option></select>')
			.appendTo($(this).empty())
			.on('change',function() {
				table.column(i).search($(this).val()).draw();
			});
			table.column(i).data().unique().sort().each(
				function(d, j) {
					select.append('<option value="'+d+'">'
									+ d
									+ '</option>')
				});
		});
		
		var currentIndex;
		
		$('#myModal1').on('shown.bs.modal', function() {
			if(currentIndex != -1) {
				$("#contactId").val($("#id" + currentIndex).text());
				$("#fName").val($("#fName" + currentIndex).text());
				$("#lName").val($("#lName" + currentIndex).text());
				$("#dob").val($("#dob" + currentIndex).text());
				$("#ssn").val($("#ssn" + currentIndex).text());
				$("#street").val($("#street" + currentIndex).text());
				$("#city").val($("#city" + currentIndex).text());
				$("#state").val($("#state" + currentIndex).text());
				$("#zip").val($("#zip" + currentIndex).text());
				$("#username").val($("#username" + currentIndex).text());
				
				//alert($("#username" + currentIndex).text());
			}
		});
		
		$('#myModal1').on('hidden.bs.modal', function () {
			$("#contactId").val("");
			$("#fName").val("");
			$("#lName").val("");
			$("#dob").val("");
			$("#ssn").val("");
			$("#street").val("");
			$("#city").val("");
			$("#state").val("");
			$("#zip").val("");
			$("#username").val("");
		}); 
		
		$(".triggerEdit").click(function(e) {
			currentIndex = $(this).data("index");
		});
		
		$(".triggerAdd").click(function(e) {
			currentIndex = -1;
		});
		
		/* Apply alpha Numaric validation and ssn numaricvalidation */
		$('#fName,#lName,#ssn,#zip').keypress(function (e) {
			var id =$(this).attr('id'); 
			var regex;
			if(id == 'ssn' || id =='zip') {
				regex = new RegExp("^[1-9]\d*$");
			} else {
				regex = new RegExp("^[a-zA-Z0-9]+$");	
			}
		    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
		    if (regex.test(str)) {
		        return true;
		    }

		    e.preventDefault();
		    return false;
		});
		
		$(".contactForm").validate({
			rules : {
				firstName : {
					required : true,
					minlength: 3
				},
				lastName : {
					required : true,
					minlength: 3
				},
				/* dob : {
					required : true,
				}, */
				ssn : {
					required : true,
					/* remote: {
						url: "<spring:url value='/contacts/available.html'/>",
						type: "get",
						data: {	
							myData: {
								"ssn":  $("#ssn").val(),
								"id": $("#contactId").val()
							}
						}	
					} */
				},
				street : {
					required : true,
				},
				city : {
					required : true,
				},
				state : {
					required : true,
				},
				zip : {
					required : true,
				},
				highlight : function(element) {
					$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
				},
				unhighlight : function(element) {
					$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
				} /* messages:{
					ssn:{
						remote: "ssn number already exist."
					}
				} */
				
			}
		});
		/* TO-DO: Will look into this issue later to fix*/
		 $(function () {
		        $("#goalDeadline").datepicker({
		            changeMonth: true,
		            changeYear: true,
		            minDate: +1
		        });
		    });
		
		  $("#dob").prop("disabled", true);
			
	});
	
</script>

<style>
	body.modal-open .goalDeadline {
    z-index: 1200 !important;
}
</style>	

</head>
<body>
	<!--  Registration success check. -->	
	<c:if test="${success eq 'addUpdate'}">
		<div class="alert alert-success"><spring:message code="label.contact.addUpdate.Success" /></div>
	</c:if>
	
	<th><a href="/demoTMApplication/contactExport.xls">Download Excel Document</a></th>

	<div class="table-responsive">
		<table id="example1" class="display table" width="100%">
			<thead>
				<tr>
					<th></th>
					<th style="display: none;">Id</th>
					<th><spring:message code="label.contact.fName"></spring:message></th>
					<th><spring:message code="label.contact.lName"></spring:message></th>
					<th><spring:message code="label.contact.dob"></spring:message></th>
					<th><spring:message code="label.contact.ssn"></spring:message></th>
					<th><spring:message code="label.contact.street"></spring:message></th>
					<th><spring:message code="label.contact.city"></spring:message></th>
					<th><spring:message code="label.contact.state"></spring:message></th>
					<th><spring:message code="label.contact.zip"></spring:message></th>
					<th><spring:message code="label.contact.user"></spring:message></th>
					<th><spring:message code="label.contact.edit"></spring:message></th>
					<th><spring:message code="label.remove.removeContact"></spring:message></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${contacts}" var="contact" varStatus="index">
					<tr>
						<td style="text-align:center;">
							<c:if test="${(pageContext.request.userPrincipal.name == contact.userName) || (pageContext.request.userPrincipal.name == 'admin')}">
								<input type="checkbox" name="case[]" class="case" value="checked" />
							</c:if>
						</td>
						<td id="id${index.index }" style="display: none; visibility:hidden" type="hidden">${contact.id}</td>
						<td id="fName${index.index }">${contact.firstName}</td>
						<td id="lName${index.index }">${contact.lastName}</td>
						<td id="dob${index.index }">${contact.dob}</td>
						<td id="ssn${index.index }">${contact.ssn}</td>
						<td id="street${index.index }">${contact.street}</td>
						<td id="city${index.index }">${contact.city}</td>
						<td id="state${index.index }">${contact.state}</td>
						<td id="zip${index.index }">${contact.zip}</td>
						<td id="username${index.index }">${contact.userName}</td>
						<td>
							<c:if test="${(pageContext.request.userPrincipal.name == contact.userName) || (pageContext.request.userPrincipal.name == 'admin')}">
								<button type="button" class="btn  btn-primary triggerEdit"
									data-toggle="modal" data-target="#myModal1"
									data-index="${index.index }">
									<spring:message code="label.contact.edit"></spring:message>
								</button>
							</c:if>
						</td>
						<td>
						
							<c:if test="${(pageContext.request.userPrincipal.name == contact.userName) || (pageContext.request.userPrincipal.name == 'admin')}">
								<a
									href='<spring:url value="/contacts/remove/${contact.id}.html" ></spring:url>'
									class="btn btn-danger triggerRemove"><spring:message
										code="label.remove.removeContact"></spring:message></a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>



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
					<h4 class="modal-title" id="myModalLabel"><spring:message code="label.remove.removeContacts"></spring:message></h4>
				</div>
				<div class="modal-body"><spring:message code="label.remove.removeConfirmation"></spring:message></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						Cancel</button>
					<a href="" class="btn btn-danger  removebtn"><spring:message code="label.remove.delete"></spring:message></a>
				</div>

			</div>
		</div>
	</div>
	</div>
	<!-- ---------------------- End: Contact Delete Confirmation Message ----------------  -->
	
	<!-- Start: Delete All button -->
	<button type="button" 
		class="btn btn-danger btn-primary btn-lg triggerRemoveAll"><spring:message
			code="label.remove.removeContacts"></spring:message></button>
	<!-- End: Delete All button -->
	
	<!-- ---------------------- Start: Add new Contact ----------------------------------  -->
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary btn-lg triggerAdd"
		data-toggle="modal" data-target="#myModal1">
		<spring:message code="label.contact.addContact"></spring:message>
	</button>
	
	<!-- ------------------------- start : pop up for adding New Contact ----------------- -->
	<form:form commandName="contact" action="contacts/addUpdateContact.html" method="POST" cssClass="form-horizontal  contactForm">
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
						<spring:message code="label.add.contact"></spring:message>
					</h4>
				</div>
				<!-- End: Add Header in the popup -->
				<!-- Start: body of the popup -->
				<div class="modal-body">
					<div class="form-group">
						<!-- <label path="id" id="contactId" style="visibility: hidden"/> -->
						<form:input type="hidden" path="id" id="contactId" style="visibility: hidden" cssClass="form-control" />
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">
							<spring:message code="label.add.fName"></spring:message>
						</label>
						<div class="col-sm-10">
							<form:input path="firstName" id="fName" cssClass="form-control" />
							<form:errors path="firstName"/>
						</div>
					</div>
					<div class="form-group">
						<label for="lastName" class="col-sm-2 control-label">
							<spring:message code="label.add.lName"></spring:message>
						</label>
						<div class="col-sm-10">
							<form:input path="lastName" id="lName" cssClass="form-control" />
							<form:errors path="lastName"/>
						</div>
					</div>
					<div class="form-group">
						<label for="dob" class="col-sm-2 control-label">
							<spring:message code="label.add.dob"></spring:message>
						</label>
						<div class="col-sm-10">
								<!-- <div class="controls">
									<div class="input-prepend input-append">
										<span class="add-on"></span><input class="input-medium goalDeadline"
											type="text" id="goalDeadline" name="goalDeadline">
									</div>
								</div> -->
								<form:input path="dob"  id="dob" cssClass="form-control" />
								</div>
							</div>
					<div class="form-group">
						<label for="ssn" class="col-sm-2 control-label">
							<spring:message code="label.add.ssn"></spring:message>
						</label>
						<div class="col-sm-10">
							<form:input path="ssn" id="ssn" cssClass="form-control" />
							<form:errors path="ssn"/>
						</div>
					</div>
					<div class="form-group">
						<label for="street" class="col-sm-2 control-label">
							<spring:message code="label.add.street"></spring:message>
						</label>
						<div class="col-sm-10">
							<form:input path="street" id="street" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="city" class="col-sm-2 control-label">
							<spring:message code="label.add.city"></spring:message>
						</label>
						<div class="col-sm-10">
							<form:input path="city" id="city" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="state" class="col-sm-2 control-label">
							<spring:message code="label.add.state"></spring:message>
						</label>
						<div class="col-sm-10">
							<form:input path="state" id="state" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="zip" class="col-sm-2 control-label">
							<spring:message code="label.add.zip"></spring:message>
						</label>
						<div class="col-sm-10">
							<form:input path="zip" id="zip" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
							<form:input path="userName" type="hidden" id="username" style="visibility: hidden" cssClass="form-control" />
					</div>
				</div>
				<!-- End: body of the popup -->
				<!-- Start: footer of the popup -->
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<spring:message code="label.add.close"></spring:message>
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