<%@ page import="com.eequalsmc2.IoTBay_Final.model.Customer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=order-width, initial-scale=1, shrink-to-fit=no">
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

<%
    Customer user = (Customer) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
    }
%>

<body>


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
                <a href="logoutServlet" type="button" class="btn btn-warning">Logout</a>
            </div>
            <%
                }
            %>
        </div>
    </div>
</header>

<div class="mt-2">

<ul class="nav nav-tabs col-md-8">
    <li class="nav-item"><a class="nav-link active" href="admin?operate=list">Suppliers</a>
    </li>
    <li class="nav-item"><a class="nav-link" href="device?operate=list">Devides</a></li>
    <li class="nav-item"><a class="nav-link" href="device?operate=list">Link</a></li>
    <%-- 下面为回到首页的代码--%>
    <li class="nav-item"><a class="nav-link" href="/IoTBay_Final_war_exploded">HOME</a>
    </li>
</ul>

<fieldset>
    <legend>SEARCH</legend>
    <form action="order?operate=search" method="post">
        <div class="form-row">
            <div class="form-group col-md-4">
                <select id="inputState" class="form-control" name="condition">
                    <option value="id">orderID</option>
                    <option value="date">date</option>
                </select>
                <input class="form-control" name="value"
                       placeholder="please input condition">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>

</fieldset>
<%--<a href="customer_profile.jsp">Back to profile</a>--%>
<table class="table" id="table">
    <thead class="thead-dark">
    <tr>
        <th class="text-center" scope="col">NO.</th>
        <th scope="col">CUSTOMERID</th>
        <th scope="col">ORDERITEMSID</th>
        <th scope="col">TOTALPRICE</th>
        <th scope="col">DATE</th>
        <th scope="col">STATUS</th>
        <th scope="col">SHIPMENTID</th>
        <th scope="col">ACTION</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="item" varStatus="idx">
        <tr>
            <th scope="row">${item.id}</th>
            <td>${item.customerId}</td>
            <td>${item.orderItemsId}</td>
            <td>${item.totalPrice}</td>
            <td>${item.date}</td>

            <c:if test="${item.status eq 0 }">
                <td>paid</td>
            </c:if>
            <c:if test="${item.status eq 1 }">
                <td>unpaid</td>
            </c:if>
            <c:if test="${item.status eq 2 }">
                <td>ordered</td>
            </c:if>

            <c:if test="${item.status eq 3 }">
                <td>completed</td>
            </c:if>

            <td>${item.shipmentId}</td>
            <td><input type="button" name="name"
                       onclick="editInfo(${item.id})" class="btn btn-warning"
                       value="EDIT" /> <a href="order?operate=delete&id=${item.id}"
                                          class="btn btn-danger">DELETE</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
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
                      action="order?operate=add" method="post">
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">id</label>
                        <div class="col-xs-8">
                            <input  name="id" readonly="readonly"
                                    class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">CUSTOMERID</label>
                        <div class="col-xs-8">
                            <input id="customerId" name="customerId" class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">ORDERITEMSID</label>
                        <div class="col-xs-8">
                            <input id="orderItemsId" name="orderItemsId"
                                   class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">TOTALPRICE</label>
                        <div class="col-xs-8">
                            <input id="totalPrice" name="totalPrice" class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">DATE</label>
                        <div class="col-xs-8">
                            <input type="date" id="date" name="date" class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">STATUS</label>
                        <div class="col-xs-8">
                            <select name="status">
                                <option value="0">ordered</option>
                                <option value="1">pending</option>
                                <option value="2">ordering</option>
                                <option value="3">completed</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">SHIPMENTID</label>
                        <div class="col-xs-8">
                            <input id="shipmentId" name="shipmentId" class="input-text form-control">
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
                      action="order?operate=edit" method="post">
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">id</label>
                        <div class="col-xs-8">
                            <input id="id" name="id" readonly="readonly"
                                   class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">CUSTOMERID</label>
                        <div class="col-xs-8">
                            <input id="customerId" name="customerId" class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">ORDERITEMSID</label>
                        <div class="col-xs-8">
                            <input id="orderItemsId" name="orderItemsId"
                                   class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">TOTALPRICE</label>
                        <div class="col-xs-8">
                            <input id="totalPrice" name="totalPrice" class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">DATE</label>
                        <div class="col-xs-8">
                            <input type="date" id="date" name="date" class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">STATUS</label>
                        <div class="col-xs-8">
                            <select name="status">
                                <option value="0">paid</option>
                                <option value="1">unpaid</option>
                                <option value="2">ordered</option>
                                <option value="3">completed</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">SHIPMENTID</label>
                        <div class="col-xs-8">
                            <input id="shipmentId" name="shipmentId" class="input-text form-control">
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
<div class="input-group col-md-3">
    <br>
    <button class="btn btn-success" onclick="add()"
            style="margin-left: 3px">ADD</button>
    <br>
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