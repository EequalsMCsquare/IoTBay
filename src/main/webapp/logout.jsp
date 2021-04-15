<%--
  Created by IntelliJ IDEA.
  User: Reco
  Date: 2021/4/15
  Time: 2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.removeAttribute("user");
    response.sendRedirect("index.jsp");
%>
<html>
<head>
    <title>IoTBay - Logout</title>
</head>
<body>

</body>
</html>
