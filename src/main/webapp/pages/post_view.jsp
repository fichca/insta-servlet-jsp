<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<jsp:include page="../_header.jsp"/>
<div class="container">
<%--        <div class="row justify-content-center">--%>
    <%--        <div class="col-6"> --%>
    <div class="row justify-content-md-center">
        <div class="col-6">
            <img class="card-img-top" src="${requestScope.post.url}" alt="${requestScope.post.url}">
            <h1 class="display-4">${requestScope.post.title}</h1>
            <p class="lead"><em>${requestScope.post.description}</em></p><br>
        </div>
        <div class="col col-3">
            <div class="mb-3">
                <form action="/addComment" method="post">
                    <input type="hidden" name="postId" value="${requestScope.post.id}">
                    <%--                    <label for="comment" class="form-label">Write comment</label>--%>

                    <div class="input-group mb-3">
                        <input type="text" placeholder="Write comment" name="comment" class="form-control" id="comment"
                               aria-describedby="comment" minlength="3" maxlength="16" required>
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-outline-secondary">Submit</button>
                        </div>
                    </div>
                </form>

                <c:forEach items="${requestScope.post.comments}" var="comment">
                    ${comment.comment}
                    <br>
                </c:forEach>

            </div>

            <%--        <div class="row justify-content-center">--%>
            <div class="col-md-auto">
                <nav aria-label="Page navigation example">
                    <ul class="pagination pagination-lg">

                        <c:if test="${requestScope.countCommentPages > 1}">
                            <c:forEach begin="1" end="${requestScope.countCommentPages}" var="number">
                                <c:if test="${requestScope.numberCommentPage == number}">
                                    <li class="page-item active">
                                        <a class="page-link"
                                           href="/viewPost?id=${requestScope.post.id}&commentPage=${number}">${number}</a>
                                    </li>
                                </c:if>
                                <c:if test="${requestScope.numberCommentPage != number}">
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="/viewPost?id=${requestScope.post.id}&commentPage=${number}">${number}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </ul>
                </nav>
            </div>
            <%--        </div>--%>
        </div>
    </div>
</div>
</body>
</html>
