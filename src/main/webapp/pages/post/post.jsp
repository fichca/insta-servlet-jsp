<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<head>
    <title>
        News feed
    </title>
</head>
<jsp:include page="../../_header.jsp"/>

<div class="container">

    <c:forEach items="${requestScope.posts}" var="post">
    <div class="row justify-content-center mb-5 mt-5">
        <div class="col-6">
            <div class="card">
                <div class="card-body">
                    <p><strong>${post.user.name}</strong></p>
                    <img class="card-img-top" src="${post.url}" alt="${post.url}">
                    <h5 class="card-title">${post.title}</h5>
                    <p class="card-text">${post.description}</p>

                    <div class="row justify-content-between">
                        <div class="col-1">
                            <a href="/viewPost?id=${post.id}" class="btn btn-outline-secondary">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                     fill="currentColor"
                                     class="bi bi-eye" viewBox="0 0 16 16">
                                    <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
                                    <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
                                </svg>
                            </a>
                        </div>
                        <div class="col-4">
                            <h6>Category <a class="badge rounded-pill bg-dark" href="/?category=${post.category.name}"
                                            role="button">
                                <span class="badge rounded-pill bg-dark">${post.category.name}</span></a></h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach>

        <div class="row justify-content-center">
            <div class="col-md-auto">
                <nav aria-label="Page navigation example">
                    <ul class="pagination pagination-lg">

                        <c:if test="${requestScope.countPages > 1}">
                            <c:forEach begin="1" end="${requestScope.countPages}" var="number">
                                <c:if test="${requestScope.numberPage == number}">
                                    <li class="page-item active">
                                        <c:if test="${requestScope.category != null}">
                                            <a class="page-link"
                                               href="/?page=${number}&category=${requestScope.category.name}">${number}</a>
                                        </c:if>
                                        <c:if test="${requestScope.category == null}">
                                            <a class="page-link" href="/?page=${number}">${number}</a>
                                        </c:if>
                                    </li>
                                </c:if>
                                <c:if test="${requestScope.numberPage != number}">
                                    <li class="page-item">
                                        <c:if test="${requestScope.category != null}">
                                            <a class="page-link"
                                               href="/?page=${number}&category=${requestScope.category.name}">${number}</a>
                                        </c:if>
                                        <c:if test="${requestScope.category == null}">
                                            <a class="page-link" href="/?page=${number}">${number}</a>
                                        </c:if>
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
