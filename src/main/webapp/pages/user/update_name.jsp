<%@ page import="by.insta.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update name</title>
</head>
<body>
<jsp:include page="../../_header.jsp"/>
<div class="row justify-content-center">
    <div class="col-6">

        <%
            User user = (User) session.getAttribute("user");
            out.print("Old name " + user.getName());
        %>

        <form action="/userAccount/updateName" method="post">
            <div class="mb-3">
                <label for="New name" class="form-label">Write new name</label>
                <input type="text" name="New name" class="form-control" id="New name" aria-describedby="New name" minlength="3" maxlength="15" required>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        <form action="/account">
            <button type="submit" class="btn btn-primary">Back</button>
        </form>

        <%
            if ((boolean)request.getAttribute("isErrors")){
                out.print("<div class=\"alert alert-warning\" role=\"alert\">\n" +
                        "                <p>" + request.getAttribute("result") + "</p>\n" +
                        "            </div>");
            }
        %>

    </div>
</div>
</body>
</html>
