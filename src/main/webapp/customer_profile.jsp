<%@ page import="com.eequalsmc2.IoTBay_Final.model.Customer" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Reco
  Date: 2021/4/15
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>


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
            <div class="col-md-4">
                <h2>Order</h2>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Explicabo amet optio, nulla minima itaque ut placeat eum est libero incidunt molestias provident a qui, eaque non enim tenetur magnam? Nobis!</p>
                <p><a class="btn btn-success btn-block" href="#" role="button">View Order</a></p>
            </div>

            <div class="col-md-4">
                <h2>Cart </h2>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Explicabo amet optio, nulla minima itaque ut placeat eum est libero incidunt molestias provident a qui, eaque non enim tenetur magnam? Nobis!</p>
                <p><a class="btn btn-dark btn-block" href="#" role="button">View Cart</a></p>
            </div>

            <div class="col-md-4">
                <h2>Address</h2>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Explicabo amet optio, nulla minima itaque ut placeat eum est libero incidunt molestias provident a qui, eaque non enim tenetur magnam? Nobis!</p>
                <p><a class="btn btn-info btn-block" href="#" role="button">Manage Address</a></p>
            </div>
        </div>

        <hr>

        <div class="row">
            <div class="col-md-4">
                <h2>Profile</h2>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Explicabo amet optio, nulla minima itaque ut placeat eum est libero incidunt molestias provident a qui, eaque non enim tenetur magnam? Nobis!</p>
                <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#profileModal">
                    Edit My Profile
                </button>
            </div>

            <div class="col-md-4">
                <h2>Access Log</h2>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Explicabo amet optio, nulla minima itaque ut placeat eum est libero incidunt molestias provident a qui, eaque non enim tenetur magnam? Nobis!</p>
                <p><a role="button" class="btn btn-warning btn-block" href="#">View Access Log</a></p>
            </div>

            <div class="col-md-4">
                <h2>Account</h2>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Explicabo amet optio, nulla minima itaque ut placeat eum est libero incidunt molestias provident a qui, eaque non enim tenetur magnam? Nobis!</p>
                <p><a role="button" class="btn btn-danger btn-block" href="delete_account.jsp">Cancel My Account</a></p>
            </div>
        </div>

        <hr>

    </div> <!-- /container -->

    <div>
        <!-- Profile Modal -->
        <div class="modal fade" id="profileModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Edit Profile</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <form action="customerServlet?action=edit" method="post">
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="text" class="form-control" id="email" disabled placeholder=<%=user.getEmail()%>>
                            </div>
                            <div class="form-group">
                                <label for="firstName">First Name</label>
                                <input type="text" class="form-control" disabled id="firstName" name="firstName" placeholder="<%=user.getFirstName()%>">
                            </div>
                            <div class="form-group">
                                <label for="lastName">Last Name</label>
                                <input type="text" class="form-control" disabled id="lastName" name="lastName" placeholder=<%=user.getLastName()%>>
                            </div>
                            <div class="form-group">
                                <label for="password">New Password</label>
                                <input type="password" class="form-control" disabled id="password" name="password">
                            </div>
                            <div class="form-group">
                                <label for="phone">Phone Number</label>
                                <input type="text" class="form-control" disabled id="phone" name="phone" placeholder=<%=user.getPhone()%>>
                            </div>
                            <div class="form-group">
                                <label for="gender">Gender</label>
                                <input type="text" class="form-control"disabled id="gender" name="gender" placeholder=<%=user.getGender()%>>
                            </div>
                            <div class="form-group">
                                <label for="dob">Birthday</label>
                                <input type="text" class="form-control" disabled id="dob" onfocus="(this.type='date')" name="dob" placeholder=<%=sdf.format(user.getDob())%>>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Save changes</button>
                                <button type="button" class="btn btn-warning" onclick="makeProfileEditable()">Edit</button>
                            </div>
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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')</script><script src="../assets/dist/js/bootstrap.bundle.js"></script></body>

<script>
    let makeEditable = (id) => {
        document.getElementById(id).removeAttribute("disabled");
    }
    let makeProfileEditable = () => {
        makeEditable("firstName");
        makeEditable("lastName");
        makeEditable("password");
        makeEditable("phone");
        makeEditable("gender");
        makeEditable("dob");
    }
</script>
</html>
