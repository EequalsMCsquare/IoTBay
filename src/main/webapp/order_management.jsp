<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.eequalsmc2.IoTBay_Final.model.Customer" %>
<%@ page import="com.eequalsmc2.IoTBay_Final.model.User" %>
<%@ page import="com.eequalsmc2.IoTBay_Final.model.Staff" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.80.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
	integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
	crossorigin="anonymous"></script>

<title>suppliersInfo</title>

<%
    User user = (User) session.getAttribute("user");
    Staff staff = null;
    Customer customer = null;
    if(user instanceof Staff) {
        staff = (Staff) user;
    } else if(user instanceof Customer) {
        customer = (Customer)user;
    }
%>
</head>
<header>
	<div class="navbar navbar-dark bg-dark shadow-sm">
		<div class="container d-flex justify-content-between">
			<a href="index.jsp" class="navbar-brand d-flex align-items-center">
				<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-shop" viewBox="0 0 16 16">
					<path d="M2.97 1.35A1 1 0 0 1 3.73 1h8.54a1 1 0 0 1 .76.35l2.609 3.044A1.5 1.5 0 0 1 16 5.37v.255a2.375 2.375 0 0 1-4.25 1.458A2.371 2.371 0 0 1 9.875 8 2.37 2.37 0 0 1 8 7.083 2.37 2.37 0 0 1 6.125 8a2.37 2.37 0 0 1-1.875-.917A2.375 2.375 0 0 1 0 5.625V5.37a1.5 1.5 0 0 1 .361-.976l2.61-3.045zm1.78 4.275a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 1 0 2.75 0V5.37a.5.5 0 0 0-.12-.325L12.27 2H3.73L1.12 5.045A.5.5 0 0 0 1 5.37v.255a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0zM1.5 8.5A.5.5 0 0 1 2 9v6h1v-5a1 1 0 0 1 1-1h3a1 1 0 0 1 1 1v5h6V9a.5.5 0 0 1 1 0v6h.5a.5.5 0 0 1 0 1H.5a.5.5 0 0 1 0-1H1V9a.5.5 0 0 1 .5-.5zM4 15h3v-5H4v5zm5-5a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1h-2a1 1 0 0 1-1-1v-3zm3 0h-2v3h2v-3z"/>
				</svg>
				<strong style="margin-left:3px">IoTBay</strong>
			</a>
			<%
				if (user == null) {
			%>
			<div>
				<button class="btn btn-light" type="button" onclick="window.location='login.jsp'">Login</button>
				<button class="btn btn-primary" type="button" onclick="window.location='register.jsp'">Register</button>
			</div>
			<%
			} else {
			%>
			<div>
				<button class="btn btn-success active" type="button" onclick="window.location='customer_profile.jsp'">My Account</button>
				<button class="btn btn-warning" type="button" onclick="window.location='logout.jsp'">Logout</button>
			</div>
			<%
				}
			%>
		</div>
	</div>
</header>

<body>
	<fieldset>
		<legend>SEARCH</legend>
		<form action="order_management?operate=search" method="post">
			<div class="form-row">
				<div class="form-group col-md-4">
					<select id="inputState" class="form-control" name="condition">
						<option value="name">SuppliersName</option>
						<option value="company">Company</option>
					</select> <input class="form-control" name="value"
						placeholder="please input condition">
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Search</button>
		</form>

	</fieldset>

	<table class="table" id="table">
		<thead class="thead-dark">
			<tr>
				<th class="text-center" scope="col">NO.</th>
				<th scope="col">SUPPLIERNAME</th>
				<th scope="col">COMPANY</th>
				<th scope="col">PHONE</th>
				<th scope="col">EMAIL</th>
				<th scope="col">STATUS</th>
				<th scope="col">ACTION</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="item" varStatus="idx">
				<tr>
					<th scope="row">${idx.count}</th>
					<td>${item.name}</td>
					<td>${item.company}</td>
					<td>${item.phone}</td>
					<td>${item.email}</td>
					<td>${item.status}</td>
					<td><input type="button" name="name"
						onclick="editInfo(${item.id})" class="btn btn-warning"
						value="EDIT" /> <a href="orders?operate=delete&id=${item.id}"
						class="btn btn-danger">DELETE</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="input-group col-md-3">
		<br>
		<button class="btn btn-success" onclick="add()"
			style="margin-left: 3px">ADD</button>
		<br>
	</div>
	<div class="modal fade" id="edit" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">EDIT</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form class="form-group style='margin:0 auto'"
						action="orders?operate=edit" method="post">
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">id</label>
							<div class="col-xs-8">
								<input id="id" name="id" readonly="readonly"
									class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">SUPPLIERNAME</label>
							<div class="col-xs-8">
								<input id="name" name="name" class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">COMPANY</label>
							<div class="col-xs-8">
								<input id="company" name="company"
									class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">PHONE</label>
							<div class="col-xs-8">
								<input id="phone" name="phone" class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">EMAIL</label>
							<div class="col-xs-8">
								<input id="email" name="email" class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<div class="form-group">
								<label class="col-xs-4 control-label">STATUS</label> <label
									class="radio-inline"> <input type="radio"
									value="ENABLE" name="status">ENABLE
								</label> <label class="radio-inline"> <input type="radio"
									value="DISABLE" name="status">DISABLE
								</label>
							</div>
						</div>

						<br>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">COLESE</button>
							<input type="submit" class="btn btn-primary" value="SUBMIT"></input>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="add" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">ADD</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form class="form-group style='margin:0 auto'"
						action="admin?operate=create" method="post">

						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">SUPPLIERNAME</label>
							<div class="col-xs-8">
								<input id="name" name="name" class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">COMPANY</label>
							<div class="col-xs-8">
								<input id="company" name="company"
									class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">PHONE</label>
							<div class="col-xs-8">
								<input id="phone" name="phone" class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">EMAILE</label>
							<div class="col-xs-8">
								<input id="email" name="email" class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<div class="form-group">
								<label class="col-xs-4 control-label">STATUS</label> <label
									class="radio-inline"> <input type="radio"
									value="ENABLE" name="status">ENABLE
								</label> <label class="radio-inline"> <input type="radio"
									value="DISABLE" name="status">DISABLE
								</label>
							</div>
						</div>

						<br>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">CLOSE</button>
							<input type="submit" class="btn btn-primary" value="SUBMIT"></input>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">

		
		function editInfo(obj) {
			var id =obj
			$('#id').val(id);
			console.log(id)
			$('#edit').modal('show');
		}
		function add() {
			$('#add').modal('show');
		}

	
		
</script>
</body>
</html>