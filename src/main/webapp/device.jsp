<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</head>
<body>
	<%-- --%>
	<%@ include file="nav.jsp" %>

	<fieldset>
		<legend>SEARCH</legend>
		<form action="device?operate=search" method="post">
			<div class="form-row">
				<div class="form-group col-md-4">
					<select id="inputState" class="form-control" name="condition">
						<option value="deviceName">DeviceName</option>
						<option value="type">Type</option>
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
				<th scope="col">DEVICENAME</th>
				<th scope="col">TYPE</th>
				<th scope="col">UNITPRICE</th>
				<th scope="col">QUANTITY</th>
				<th scope="col">ACTION</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="item" varStatus="idx">
				<tr>
					<th scope="row">${idx.count}</th>
					<td>${item.deviceName}</td>
					<td>${item.type}</td>
					<td>${item.unitPrice}</td>
					<td>${item.quantity}</td>
					<td><input type="button" name="name"
						onclick="editInfo(${item.id})" class="btn btn-warning"
						value="EDIT" /> <a href="device?operate=delete&id=${item.id}"
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
						action="device?operate=edit" method="post">
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">id</label>
							<div class="col-xs-8">
								<input id="id" name="id" readonly="readonly"
									class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">DEVICENAME</label>
							<div class="col-xs-8">
								<input id="deviceName" name="deviceName" class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">TYPE</label>
							<div class="col-xs-8">
								<input id="type" name="type"
									class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">UNITPRICE</label>
							<div class="col-xs-8">
								<input id="unitPrice" name="unitPrice" class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">QUANTITY</label>
							<div class="col-xs-8">
								<input id="quantity" name="quantity" class="input-text form-control">
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
						action="device?operate=create" method="post">

						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">id</label>
							<div class="col-xs-8">
								<input id="id" name="id" readonly="readonly"
									class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">DEVICENAME</label>
							<div class="col-xs-8">
								<input id="deviceName" name="deviceName" class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">TYPE</label>
							<div class="col-xs-8">
								<input id="type" name="type"
									class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">UNITPRICE</label>
							<div class="col-xs-8">
								<input id="unitPrice" name="unitPrice" class="input-text form-control">
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-4 control-label">QUANTITY</label>
							<div class="col-xs-8">
								<input id="quantity" name="quantity" class="input-text form-control">
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