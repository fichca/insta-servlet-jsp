<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>authorization</title>
</head>
<body>
<jsp:include page="../../_header.jsp"/>
<div class="container">

    <div class="row justify-content-center">
        <div class="col-6">
            <form action="/auth" method="post">

                <div class="mb-3">
                    <label for="login" class="form-label">Login</label>
                    <input type="text" name="login" class="form-control" id="login" aria-describedby="login"
                           minlength="3" maxlength="15" required>
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" name="password" class="form-control" id="password" minlength="3"
                           maxlength="15" required>
                </div>

                <button type="submit" class="btn btn-primary">Authorization</button>
            </form>
            <%
                if ((boolean) request.getAttribute("isErrors")) {
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
