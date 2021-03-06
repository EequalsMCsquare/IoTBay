<%@ page import="com.eequalsmc2.IoTBay_Final.model.Customer" %>
<%@ page import="com.eequalsmc2.IoTBay_Final.model.User" %>
<%@ page import="com.eequalsmc2.IoTBay_Final.model.Staff" %><%--
  Created by IntelliJ IDEA.
  User: Reco
  Date: 2021/5/9
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>IoTBay - Delete Account</title>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
</head>

<%
    User user = (User) session.getAttribute("user");
    if(user == null) {
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
        </div>
    </div>
</header>

<main role="main" style="margin-top: 5rem;">
    <div class="card m-auto bg-light" style="width: 28rem;">
        <div class="card-header">Are you absolutely sure?</div>
        <div class="card-body ">
            <h5 class="card-title">Unexpected bad things will happen if you don???t read this!</h5>
            <p class="card-text">This action cannot be undone. This will permanently delete the User <strong><%=user.getFirstName() + " " + user.getLastName()%></strong>
            </p>
            <p>Please type <strong><%=user.getEmail()%></strong> to confirm.</p>
            <input type="text" class="form-control" oninput="verifyConfirmInput()" id="verifyInput">
            <button type="button" class="btn btn-danger mt-2 btn-block" disabled id="deleteButton" onclick="confirmDelete()">
                I understand the consequences, delete this account.
            </button>
        </div>
    </div>
</main>
</body>

<script>
    const __input = document.getElementById("verifyInput");
    const __button = document.getElementById("deleteButton");
    function verifyConfirmInput() {
        if(__input.value == "<%=user.getEmail()%>") {
            __button.disabled = false;
        } else {
            __button.disabled = true;
        }
    }

    function confirmDelete() {
        const myForm = document.createElement("form");
        myForm.method = "post";
        <%
        if (user instanceof Customer) {
        %>
            myForm.action = "customerServlet?action=cancel";
        <%
        } else {
        %>
            myForm.action = "staffServlet?action=cancel";
        <%
            }
        %>
        myForm.style.display = "none";
        $(document.body).append(myForm);
        myForm.submit();
        return myForm;
    }
</script>

</html>
