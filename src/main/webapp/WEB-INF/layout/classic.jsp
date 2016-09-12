<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="securitys"%>
	
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
<!-- Latest compiled and minified JavaScript -->

<title><tiles:getAsString name="title"></tiles:getAsString></title>
</head>
<body>
	<tilesx:useAttribute name="current" />
	<!-- ----------------------------------------- -->
	
	<div class="container">
		<!-- Static navbar -->
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
					
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="${current == 'index' ? active :''}"><a
							href='<spring:url value="/"  />'><spring:message code="label.header.home"></spring:message></a>
						</li>
						<securitys:authorize access="isAuthenticated()">
							<li class="${current == 'contacts' ? active :''}"><a
								href='<spring:url value="/contacts.html" ></spring:url>'><spring:message code="label.header.contactList"></spring:message></a></li>
						</securitys:authorize >
						
						<securitys:authorize access="hasRole('ROLE_ADMIN')">
							<li class="${current == 'activity' ? active :''}"><a
								href='<spring:url value="/adminConsole.html" ></spring:url>'><spring:message code="label.header.activityLog"></spring:message></a></li>
						</securitys:authorize >
						
						<securitys:authorize access="! isAuthenticated()">
							<li><a href='<spring:url value="/login.html" >
							</spring:url>'><spring:message code="label.header.login"></spring:message></a>
							</li>
						</securitys:authorize> 
						
						<securitys:authorize access="isAuthenticated()">
							<li><a href='<spring:url value="/logout" >
							</spring:url>'><spring:message code="label.header.logout"></spring:message></a></li>
						</securitys:authorize></li>

					<!--  Language dropdown -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false"><spring:message
								code="label.header.language" /><span class="caret"></span> </a>

						<ul class="dropdown-menu" role="menu">
							<li><a href="?language=en"><spring:message
										code="label.header.english" /></a></li>
							<li><a href="?language=zh_CN"><spring:message
										code="label.header.chinese" /></a></li>
							<li><a href="?language=de"><spring:message
										code="label.header.other" /></a></li>
						</ul>
						
						<li>
							<c:if test="${pageContext.request.userPrincipal.name != null}">
									<a href=''>${pageContext.request.userPrincipal.name}</a>
							</c:if>
						</li>

				</ul>
				</div>
			</div>
				<!--/.container-fluid -->
		</nav>
		
		<div class="container">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
</body>
</html>