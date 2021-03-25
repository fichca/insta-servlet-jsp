<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Like view</title>
</head>
<body>
<jsp:include page="../../_header.jsp"/>
<div class="container">
    <div class="row justify-content-center mb-5 mt-5">
        <div class="col-6">
            <c:forEach items="${requestScope.likes}" var="like">
                <p class="h6">${like.user.name} <em><small>${like.date.toGMTString()}</small></em></p>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
