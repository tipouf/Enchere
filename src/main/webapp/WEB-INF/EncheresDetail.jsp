<%--
  Created by IntelliJ IDEA.
  User: Sarah
  Date: 09/04/2021
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <form action="" method="post">
        <input type="number" name="montant">
        <input type="submit" value="Test">
    </form>

    <% String error = (String) request.getAttribute("error"); %>

    <%
        if (error != null) {
    %>
    <span class="text-danger"><%=error%></span>
    <%
        }
    %>
</head>
<body>

</body>
</html>
