<%@ page import="java.sql.Connection" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Olimjon
  Date: 23.08.2017
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Connection connection = null;
    InitialContext initialContext = null;
    DataSource dataSource = null;
    int res = 0;

    try {
        initialContext = new InitialContext();
        dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/postgres");
        connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(
                "SELECT COUNT (*) FROM users");
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        res = result.getInt("count");
    }catch (Exception e){
        e.printStackTrace();
    }

%>
Number of users = <%=res %>
</body>
</html>
