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
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col">
                            <form action="/userView" method="post">
                                <input type="hidden" value="${requestScope.post.user.login}" name="login">
                                <button type="submit" class="badge rounded-pill bg-dark"><h4><span
                                        class="badge rounded-pill bg-dark">${requestScope.post.user.name}</span></h4>
                                </button>
                            </form>
                        </div>


                        <img class="card-img-top" src="${requestScope.post.url}" alt="${requestScope.post.url}">

                        <div class="row justify-content-between">

                            <c:if test="${sessionScope.user != null}">
                                <div class="col-4">
                                    <div class="row">
                                        <div class="col-3">

                                            <form action="/addLike" method="post">
                                                <input type="hidden" name="postId" value="${requestScope.post.id}">
                                                <button type="submit" class="btn btn-outline-dark">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="23" height="23"
                                                         fill="currentColor"
                                                         class="bi bi-heart" viewBox="0 0 16 16">
                                                        <path d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
                                                    </svg>
                                                </button>
                                            </form>
                                        </div>

                                        <div class="col">
                                            <div class="dropdown">
                                                <a class="btn btn-dark dropdown-toggle"
                                                   href="/likesView?postId=${requestScope.post.id}"
                                                   role="button" id="dropdownMenuLink" data-bs-toggle="dropdown"
                                                   aria-expanded="false">
                                                        ${requestScope.post.likes.size()}
                                                </a>

                                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                                    <li><a class="dropdown-item"
                                                           href="/likesView?postId=${requestScope.post.id}"><em>Show all
                                                        likes</em></a>
                                                    </li>
                                                    <li>
                                                        <hr class="dropdown-divider">
                                                    </li>
                                                    <c:forEach items="${requestScope.post.likes}" var="like">
                                                        <li>
                                                            <form action="/userView" method="post">
                                                                <input type="hidden" value="${like.user.login}"
                                                                       name="login">
                                                                <button class="dropdown-item"
                                                                        type="submit">${like.user.name}</button>
                                                            </form>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${sessionScope.user == null}">
                                <div class="col-4">
                                    <p>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor"
                                             class="bi bi-heart" viewBox="0 0 16 16">
                                            <path d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
                                        </svg>
                                            ${requestScope.post.likes.size()}</p>
                                </div>
                            </c:if>
                            <div class="col-2">
                                <p class="text-end">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                         fill="currentColor"
                                         class="bi bi-eye" viewBox="0 0 16 16">
                                        <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
                                        <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
                                    </svg>
                                    ${requestScope.post.countView.size()}</p>
                            </div>
                        </div>

                        <h1 class="display-4">${requestScope.post.title}</h1>
                        <p class="lead"><em>${requestScope.post.description}</em></p><br>
                    </div>
                </div>
            </div>
        </div>


        <div class="col col-3">
            <div class="row">
                <div class="mb-3">
                    <c:if test="${sessionScope.user != null}">
                        <form action="/addComment" method="post">
                            <input type="hidden" name="postId" value="${requestScope.post.id}">
                            <div class="input-group mb-3">
                                <textarea name="comment" cols="30" rows="10" required minlength="1"
                                          maxlength="255"></textarea>
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
                        <p>
                            <a class="badge rounded-pill bg-dark" href="/userView?login=${comment.user.login}"
                               role="button">
                                <span class="badge rounded-pill bg-dark">${comment.user.name}</span></a>
                            : ${comment.comment}
                            <br><em><small>(${comment.date.toGMTString()})</small></em></p>
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
