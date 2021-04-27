<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dialog</title>
</head>
<body>
<jsp:include page="../../_header.jsp"/>
<div class="container">
    <div class="row justify-content-center mb-5 mt-5">
        <div class="col-6">
            <c:forEach items="${requestScope.dialog.messages}" var="message">
                <p class="h6">${message.user.name}: <em><small>${message.text} </small></em></p>
            </c:forEach>
        </div>

        <form action="/addMessage" method="post">

            <div class="mb-3">
                <input type="hidden" value="${requestScope.dialog.id}" name="dialogId">
                <input type="hidden" value="${requestScope.dialog.firstUser.login}" name="fistUser">
                <input type="hidden" value="${requestScope.dialog.secondUser.login}" name="secondUser">
                <input type="text" name="message" class="form-control" id="description"
                       aria-describedby="message" minlength="3" maxlength="255" required>
            </div>
            <div class="mb-3">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>