<%@ page import="by.insta.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Account</title>
</head>
<body>
<jsp:include page="../../_header.jsp"/>
<div class="container">

    <div class="row justify-content-center">
        <div class="col-6">
            <%
                User user = (User) session.getAttribute("user");
                out.print("<h1 class=\"display-6\">" + "Name: " + user.getName() + "<br>" + "  Login: " + user.getLogin() + "</h1>" + "<br>");
            %>
            <form action="/userAccount/updateName">
                <button type="submit" class="btn btn-primary">Change name</button>
            </form>

            <form action="/userAccount">
                <button type="submit" class="btn btn-primary">Change password</button>
            </form>

            <form action="/userAccount/allPosts">
                <button type="submit" class="btn btn-primary">View all post ${sessionScope.user.name}</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
