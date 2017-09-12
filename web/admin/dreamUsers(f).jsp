<%@ page import="java.sql.Connection" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
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
    Set<String> res = new HashSet<String>();

    try {
        initialContext = new InitialContext();
        dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/postgres");
        connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(
                "SELECT u.login from users u join(select user_id from contacts group by user_id having count(*)<10) c on u.id=c.user_id");
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            res.add(result.getString("login"));
        }
    }catch (Exception e){
        e.printStackTrace();
    }

%>
Inactive users: <%=res %>
</body>
</html>
