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
    Connection connection = null;
    InitialContext initialContext = null;
    DataSource dataSource = null;
    double res = 0;

    try {
        initialContext = new InitialContext();
        dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/postgres");
        connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(
                "SELECT avg(foo)  FROM (select count(*) as foo from contacts group by user_id) as t");
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        res = result.getDouble("avg");
    }catch (Exception e){
        e.printStackTrace();
    }

%>
Avg number of contacts of user = <%=res %>
</body>
</html>
