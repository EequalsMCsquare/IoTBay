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
        response.sendRedirect("index.jsp");
    }
    if (staff.getPrivilege() < 5) {
        out.print("<script type='text/javascript'>alert('Your privilege is too low to edit staff!');</script>");
        out.print("<script type='text/javascript'>window.history.go(-1);</script>");
        out.flush();
        out.close();
    }
    DB db = new DB();
    StaffManager sm = new StaffManager(db);

    // get data
    List<Staff> staffList = null;
    String query = request.getQueryString();
    if (query != null){
        String[] qs = query.split("&");
        if (qs.length == 0) {
            // get all staff
            staffList = sm.getAll();
        } else if (qs.length == 2) {
            String filter = qs[0].split("=")[1];
            String by = qs[1].split("=")[1];
            if(by == null || by == "") {
                staffList = sm.getAll();
            } else {
                staffList = sm.findAll(filter, by);
            }
        }
    } else {
        staffList = sm.getAll();
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
                <a class="btn btn-warning" type="button" href="logoutServlet">Logout</a>
            </div>
        </div>
    </div>
</header>

<div role="main">
    <div class="row mt-2">
        <div class="col-md-2"></div>

        <div class="col-md-8">
            <form action="staffServlet" method="post">
                <div class="input-group flex-nowrap">
                    <div class="input-group-prepend">
                        <button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#add-staff">Add</button>
                        <select class="custom-select" id="search-filter" id="filter">
                            <option selected value="email">Email</option>
                            <option value="position">Position</option>
                            <option value="first_name">First Name</option>
                            <option value="last_name">Last Name</option>
                        </select>
                    </div>
                    <input type="text" class="form-control" placeholder="search ..." id="search-contain">
                    <div class="input-group-append">
                        <button class="btn btn-outline-primary" type="button" onclick="onClickSearch()">Search</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="col-md-2"></div>
    </div>

    <div class="row mt-2">
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
                                <a type="button" class="btn btn-warning" href="staff_edit.jsp?staff_id=<%=e.getId()%>">Edit</a>
                                <a type="button" class="btn btn-danger" href="staffServlet?action=delete&id=<%=e.getId()%>">Delete</a>
                                <%
                                    if (e.getId() != user.getId()) {
                                        if(e.isActivated()) {
                                %>
                                    <a type="button" class="btn btn-secondary" href="staffServlet?action=deactivate&id=<%=e.getId()%>">Deactivate</a>
                                <%
                                    }
                                    if(!e.isActivated()) {
                                %>
                                    <a type="button" class="btn btn-primary" href="staffServlet?action=activate&id=<%=e.getId()%>">Activate</a>
                                <%
                                        }
                                    }
                                %>
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


<div class="modal fade" tabindex="-1" aria-hidden="true" id="add-staff">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Edit Profile</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <form action="staffServlet?action=register" method="post">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <div class="input-group mb-3">
                            <input type="text" name="email" class="form-control" id="email" placeholder=>
                            <div class="input-group-append">
                                <span class="input-group-text" id="basic-addon2">@staff.iotbay.com</span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstName">First Name</label>
                        <input type="text" class="form-control" id="firstName" name="firstName">
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last Name</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" >
                    </div>
                    <div class="form-group">
                        <label for="password">New Password</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone Number</label>
                        <input type="text" class="form-control" id="phone" name="phone">
                    </div>
                    <div class="form-group">
                        <label for="gender">Gender</label>
                        <select name="gender" id="gender" class="form-control">
                            <option value="preferNotToTell">Prefer not to tell</option>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="other">Other</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="dob">Birthday</label>
                        <input type="date" class="form-control" id="dob" name="dob">
                    </div>
                    <div class="form-group">
                        <label for="privilege">Privilege</label>
                        <input type="number" name="privilege" class="form-control" id="privilege" value="1">
                    </div>
                    <div class="form-group">
                        <label for="position">Position</label>
                        <select name="position" id="position" class="form-control">
                            <option value="salesperson" selected>Salesperson</option>
                            <option value="manager">Manager</option>
                            <option value="repair">Repair Service</option>
                            <option value="accountant">Accountant</option>
                            <option value="support">Support</option>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-success">Add Staff</button>
                    </div>
                </form>
            </div>
        </div>
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

    function onClickSearch() {
        const by = document.getElementById("search-contain").value;
        const filter = document.getElementById("search-filter").value;
        window.location = "staff_manage.jsp?filter=" + filter + "&by=" + by;
    }
</script>

</html>
