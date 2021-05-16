<%@ page import="com.eequalsmc2.IoTBay_Final.model.Staff" %>
<%@ page import="com.eequalsmc2.IoTBay_Final.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>IoTBay - Admin</title>


    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
</head>

<%
    User user = (User) session.getAttribute("user");
    Staff staff = null;
    if (user instanceof Staff) {
        staff = (Staff) user;
    } else {
        // if not a staff redirect to login page
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
            <div>
                <button class="btn btn-success active" type="button" onclick="window.location='admin.jsp'">Admin</button>
                <a type="button" class="btn btn-warning" type="button" href="logoutServlet">Logout</a>
            </div>
        </div>
    </div>
</header>

<main role="main">

    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3">Hello, <%=staff.getFirstName()%>!</h1>
            <p style="text-transform:uppercase"><%=staff.getPosition()%> Privilege <%=staff.getPrivilege()%> </p>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <h2>Customer</h2>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Explicabo amet optio, nulla minima itaque ut placeat eum est libero incidunt molestias provident a qui, eaque non enim tenetur magnam? Nobis!</p>
                <p><a class="btn btn-success btn-block" href="#" role="button">View Customer Profile</a></p>
            </div>

            <div class="col-md-4">
                <h2>Staff</h2>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Explicabo amet optio, nulla minima itaque ut placeat eum est libero incidunt molestias provident a qui, eaque non enim tenetur magnam? Nobis!</p>
                <p><a class="btn btn-dark btn-block" href="staff_manage.jsp" role="button">Manage Staff</a></p>
            </div>

            <div class="col-md-4">
                <h2>Stock</h2>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Explicabo amet optio, nulla minima itaque ut placeat eum est libero incidunt molestias provident a qui, eaque non enim tenetur magnam? Nobis!</p>
                <p><a class="btn btn-info btn-block" href="#" role="button">Manage Product Stock</a></p>
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
                <p><a role="button" class="btn btn-warning btn-block" href="user_access.jsp">View Access Log</a></p>
            </div>

            <div class="col-md-4">
                <h2>Account</h2>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Explicabo amet optio, nulla minima itaque ut placeat eum est libero incidunt molestias provident a qui, eaque non enim tenetur magnam? Nobis!</p>
                <p><a role="button" class="btn btn-danger btn-block" href="delete_account.jsp">Cancel My Account</a></p>
            </div>
        </div>

        <hr>
        <div class="row">

<%--Device的位置--%>
            <div class="col-md-4">
                <h2>Device</h2>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Explicabo amet optio, nulla minima itaque ut placeat eum est libero incidunt molestias provident a qui, eaque non enim tenetur magnam? Nobis!</p>
                <p><a role="button" class="btn btn-success btn-block" href="device?operate=list">Device</a></p>
            </div>


        </div>

    </div> <!-- /container -->
</main>


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
                <form action="staffServlet?action=edit&id=<%=user.getId()%>" method="post">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text" class="form-control" id="email" disabled placeholder=<%=user.getEmail()%>>
                    </div>
                    <div class="form-group">
                        <label for="privilege">Privilege</label>
                        <input type="text" class="form-control" id="privilege" disabled placeholder="<%=staff.getPrivilege()%>">
                    </div>
                    <div class="form-group">
                        <label for="position">Position</label>
                        <input type="text" class="form-control" id="position" disabled placeholder="<%=staff.getPosition()%>">
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
                        <input type="text" class="form-control" disabled id="dob" onfocus="(this.type='date')" name="dob" placeholder=<%=user.getDob("yyyy-MM-dd")%>>
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

</body>


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
