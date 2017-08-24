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

Number contacts of user with id <%=user_id%> equal <%=JspProcessor.numberOfUsersContacts(user_id)%>
</body>
</html>
