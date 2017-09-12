<%--
  Created by IntelliJ IDEA.
  User: Olimjon
  Date: 10.09.2017
  Time: 06:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action = "/MyServlet" method="post">
    <p><input type="submit" value="Contact Book" ></p>
</form>

<form action = "admin/numberOfUsers(a).jsp">
    <p><input type="submit" value="Number Of Users" ></p>
</form>

<form action = "admin/numberOfUsersContacts(b).jsp">
    <p><input type="submit" value="Number contacts of user" ></p>
</form>

<form action = "admin/numberOfUsersGroups(c).jsp">
    <p><input type="submit" value="Number groups of user" ></p>
</form>

<form action = "admin/AvgContactsInGroup(d).jsp">
    <p><input type="submit" value="Avg number of contacts in group" ></p>
</form>

<form action = "admin/AvgNumUsersContacts(e).jsp">
    <p><input type="submit" value="Avg number of contacts of user" ></p>
</form>

<form action = "admin/dreamUsers(f).jsp">
    <p><input type="submit" value="Inactive users" ></p>
</form>

</body>
</html>
