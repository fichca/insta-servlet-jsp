<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>News feed</title>
</head>
<body>

<jsp:include page="_posts.jsp"/>

<div class="row justify-content-center">
    <div class="col-md-auto">
        <nav aria-label="Page navigation example">
            <ul class="pagination pagination-lg">

                <c:if test="${requestScope.countPages > 1}">
                    <c:forEach begin="1" end="${requestScope.countPages}" var="number">
                        <c:if test="${requestScope.numberPage == number}">
                            <li class="page-item active">
                                <a class="page-link" href="/?page=${number}">${number}</a>
                            </li>
                        </c:if>

                        <c:if test="${requestScope.numberPage != number}">
                            <li class="page-item">
                                <a class="page-link" href="/?page=${number}">${number}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                </c:if>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
