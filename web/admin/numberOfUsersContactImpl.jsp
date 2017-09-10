<%@ page import="java.sql.Connection" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    int user_id = Integer.valueOf(request.getParameter("user"));

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
                "SELECT COUNT (*) FROM contacts WHERE user_id = ?");
        preparedStatement.setInt(1,user_id);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        res = result.getInt("count");
    }catch (Exception e){
        e.printStackTrace();
    }
%>

Number contacts of user with id <%=user_id%> equal <%=res%>
</body>
</html>
