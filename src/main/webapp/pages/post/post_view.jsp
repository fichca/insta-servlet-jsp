<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<jsp:include page="../../_header.jsp"/>
<div class="container">
    <div class="row justify-content-md-center">


        <div class="col-6">
            <p><strong>${requestScope.post.user.name}</strong></p>
            <img class="card-img-top" src="${requestScope.post.url}" alt="${requestScope.post.url}">

            <div class="row">
                <div class="col-1">
                    <c:if test="${sessionScope.user != null}">
                    <a class="btn btn-dark" href="/addLike?postId=${requestScope.post.id}" role="button">
                        </c:if>

                        <c:if test="${sessionScope.user == null}">
                        <a class="btn btn-dark" href="#" role="button">
                            </c:if>
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                 fill="currentColor"
                                 class="bi bi-heart" viewBox="0 0 16 16">
                                <path d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
                            </svg>
                        </a>
                </div>
                <div class="col">

                    <c:if test="${sessionScope.user != null}">
                        <div class="dropdown">
                            <a class="btn btn-dark dropdown-toggle" href="/likesView?postId=${requestScope.post.id}"
                               role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                                    ${requestScope.post.likes.size()}
                            </a>

                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                <li><a class="dropdown-item"
                                       href="/likesView?postId=${requestScope.post.id}"><em>Show all likes</em></a>
                                </li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <c:forEach items="${requestScope.post.likes}" var="like">
                                    <li><a class="dropdown-item" href="#">${like.user.name}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>

                    <c:if test="${sessionScope.user == null}">
                        <p> ${requestScope.post.likes.size()}</p>
                    </c:if>
                </div>
            </div>

            <h1 class="display-4">${requestScope.post.title}</h1>
            <p class="lead"><em>${requestScope.post.description}</em></p><br>
        </div>


        <div class="col col-3">
            <div class="row">
                <div class="mb-3">
                    <c:if test="${sessionScope.user != null}">
                        <form action="/addComment" method="post">
                            <input type="hidden" name="postId" value="${requestScope.post.id}">
                            <div class="input-group mb-3">
                                <textarea name="comment" cols="30" rows="10" required minlength="1" maxlength="255"></textarea>
                                <div class="input-group-append">
                                    <button type="submit" class="btn btn-outline-secondary">Submit</button>
                                </div>
                            </div>
                        </form>
                    </c:if>
                </div>
                <div class="mb-3">
                    <c:if test="${sessionScope.user == null}">
                        <br>
                    </c:if>
                    <c:forEach items="${requestScope.post.comments}" var="comment">
                        <p><em>${comment.user.name}:</em> ${comment.comment} <em><small>(${comment.date.toGMTString()})</small></em></p>
                    </c:forEach>
                </div>
            </div>

            <div class="col-md-auto">
                <nav aria-label="Page navigation example">
                    <ul class="pagination pagination-sm">

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
        </div>
    </div>
</div>
</body>
</html>
