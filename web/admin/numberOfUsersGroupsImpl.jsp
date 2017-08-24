<%@ page import="java.sql.Connection" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="project.JspProcessor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    int user_id = Integer.valueOf(request.getParameter("user"));

%>

Number groups of user with id <%=user_id%> equal <%=JspProcessor.numberOfUserGroups(user_id)%>
</body>
</html>
