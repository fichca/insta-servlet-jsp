<%@ page import="by.insta.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update password</title>
</head>
<body>
<jsp:include page="../../_header.jsp"/>
<div class="container">

    <div class="row justify-content-center">
        <div class="col-6">

            <%
                User user = (User) session.getAttribute("user");
                out.print("Old password " + user.getPassword());
            %>

            <p>Old password ${sessionScope.user.password}</p>

            <form action="/userAccount/updatePassword" method="post">
                <div class="mb-3">
                    <label for="New password" class="form-label">Write new password</label>
                    <input type="text" name="New password" class="form-control" id="New password" aria-describedby="New password" minlength="3" maxlength="15" required>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <form action="/userAccount">
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
</div>
</body>
</html>
