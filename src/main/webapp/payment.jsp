<%@ page import="com.eequalsmc2.IoTBay_Final.model.Customer" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Reco
  Date: 2021/4/15
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.0.1">
    <title>Customer Profile</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/jumbotron/">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="jumbotron.css" rel="stylesheet">
</head>
<body>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>

<%
    Customer user = (Customer)session.getAttribute("user");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>

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
                <button class="btn btn-primary" type="button" onclick="window.location='paymentDetail.jsp'">MyPayment</button>
            </div>
            <%
            } else {
            %>
            <div>
                <button class="btn btn-success active" type="button" onclick="window.location='customer_profile.jsp'">My Account</button>
                <a class="btn btn-warning" type="button" href="logoutServlet">Logout</a>
                <button class="btn btn-primary" type="button" onclick="window.location='paymentDetail.jsp'">MyPayment</button>
            </div>
            <%
                }
            %>
        </div>
    </div>
</header>

<main role="main">

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3">Hello, <%=user.getFirstName()%>!</h1>
            <p></p>
        </div>
    </div>

    <div class="container">
        <!-- Example row of columns -->
        <div class="row">
            <button class="btn btn-primary" type="button" onclick="add()">add</button>
        </div>
        <div class="row">
            <div class="table-responsive">
                <table class="table table-striped m-b-none" id="tbl">
                    <thead>
                    <tr>
                        <th class="text-center">id</th>
                        <th class="text-center">name</th>
                        <th class="text-center">cardNumber</th>
                        <th class="text-center">date</th>
                        <th class="text-center">action</th>
                    </tr>
                    </thead>
                    <tbody class="text-center">
                    <c:forEach items="${payments}" var="item" varStatus="xh">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.name}</td>
                            <td>${item.cardNumber}</td>
                            <td>${item.date}</td>
                            <td>
                                <button class="btn btn-info" type="button" onclick="edit(${item.id}, '${item.name}', ${item.cardNumber}, ${item.date})">edit</button>
                                <button class="btn btn-danger" type="button" onclick="del(${item.id})">del</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <hr>


        <hr>

    </div> <!-- /container -->

    <div>
        <!-- Profile Modal -->
        <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Add</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <form action="paymentServlet?action=add" method="post">
                            <div class="form-group">
                                <label for="Name">Name</label>
                                <input type="text" class="form-control" name="name" placeholder="Name">
                            </div>

                            <div class="form-group">
                                <label for="ExpireDate">Date On Card</label>
                                <input type="date" name="expireDate" class="form-control">
                            </div>

                            <div class="form-group">
                                <label for="CardNumber">Card Number</label>
                                <input type="text" name="cardNumber" class="form-control" placeholder="Card Number">
                            </div>
                            <button type="submit" class="btn btn-primary btn-lg btn-block">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Edit</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <form action="paymentServlet?action=edit" method="post">
                            <input type="hidden" class="form-control" id="id" name="id" placeholder="id">
                            <div class="form-group">
                                <label for="Name">Name</label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="Name">
                            </div>

                            <div class="form-group">
                                <label for="ExpireDate">Date On Card</label>
                                <input type="date" id="expireDate" name="expireDate" class="form-control">
                            </div>

                            <div class="form-group">
                                <label for="CardNumber">Card Number</label>
                                <input type="text" id="cardNumber" name="cardNumber" class="form-control" placeholder="Card Number">
                            </div>
                            <button type="submit" class="btn btn-primary btn-lg btn-block">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<footer class="container">
    <p>&copy; Company 2017-2020</p>
</footer>
</body>

<script>
    function add() {
        $("#addModal").modal("show");
    }

    function edit(id, name, cardNumber,expireDate) {
        $("#editModal").modal("show");
        $("#id").val(id);
        $("#name").val(name);
        $("#expireDate").val(expireDate);
        $("#cardNumber").val(cardNumber);


    }

    function del(id) {
        window.location.href = "paymentServlet?action=delete&id=" + id;
    }

</script>
</html>
