<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%
    HttpSession session = request.getSession(false);
    String username = null;
    if (session != null) {
        username = (String) session.getAttribute("username");
    }
    if (username == null) {
        response.sendRedirect("login.html");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <style>
        body {
            font-family: Arial;
            background: #f0f0f0;
            text-align: center;
            margin-top: 100px;
        }
        .box {
            display: inline-block;
            background: white;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
        }
        a {
            text-decoration: none;
            color: #007BFF;
        }
    </style>
</head>
<body>
    <div class="box">
        <h2>Welcome, <%= username %> ?</h2>
        <p>You have successfully logged in.</p>
        <a href="logout.jsp">Logout</a>
    </div>
</body>
</html>

