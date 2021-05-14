<%@ page import="com.eequalsmc2.IoTBay_Final.model.User" %>
<%@ page import="com.eequalsmc2.IoTBay_Final.model.Staff" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eequalsmc2.IoTBay_Final.model.dao.StaffManager" %>
<%@ page import="com.eequalsmc2.IoTBay_Final.utils.DB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>IoTBay - Staff Management</title>

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
    // TODO: check privilege

    DB db = new DB();
    StaffManager sm = new StaffManager(db);

    // get all staff
    List<Staff> staffList = sm.getAll();
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
                <button class="btn btn-warning" type="button" onclick="window.location='logout.jsp'">Logout</button>
            </div>
        </div>
    </div>
</header>

<div role="main">
    <div class="row">
<%--        TODO: Add toobar--%>
    </div>

    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">

            <table class="table table-hover table-striped table-responsive">
                <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Phone</th>
                    <th>Gender</th>
                    <th>D.O.B</th>
                    <th>Privilege</th>
                    <th>Position</th>
                    <th>#Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Staff e: staffList) {
                %>
                    <tr>
                        <td><%=e.getId()%></td>
                        <td><%=e.getEmail()%></td>
                        <td><%=e.getFirstName()%></td>
                        <td><%=e.getLastName()%></td>
                        <td><%=e.getPhone()%></td>
                        <td><%=e.getGender()%></td>
                        <td><%=e.getDob("yyyy-MM-dd")%></td>
                        <td><%=e.getPrivilege()%></td>
                        <td><%=e.getPosition()%></td>
                        <td>
                            <div class="btn-group btn-group-sm" role="group">
                                <button type="button" class="btn btn-warning">Edit</button>
                                <button type="button" class="btn btn-danger">Delete</button>
                            </div>
                        </td>
                    </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>

</body>

<style>
    td {
        vertical-align: center !important;
    }
</style>

<script>
    function deleteStaff(staffId) {
        const tmpForm = document.createElement("form");
        tmpForm.method = "post";
        tmpForm.action = "staffServlet?action=delete"
        const tmpInput = document.createElement("input");
        tmpInput.setAttribute("name", "id");
        tmpInput.setAttribute("value", staffId);
        tmpForm.appendChild(tmpInput);
        $(document.body).append(tmpForm);
        tmpForm.submit();
    }
</script>

</html>