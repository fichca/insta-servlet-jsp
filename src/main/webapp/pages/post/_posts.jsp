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
    <div class="row justify-content-center mb-5 mt-5">
        <div class="col-6">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="/">All posts</a>
                            </li>

                            <c:if test="${sessionScope.user != null}">
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="/posts/bySubscriptions">Subscriptions</a>
                                </li>
                            </c:if>

                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="/posts/byCategory" id="navbarDropdown"
                                   role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Category
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <c:forEach items="${requestScope.categories}" var="category">
                                        <li><a class="dropdown-item"
                                               href="/posts/byCategory?category=${category.name}">${category.name}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>

                            <c:if test="${requestScope.category != null}">
                                <li class="nav-item">
                                    <a class="nav-link disabled" href="#" tabindex="-1"
                                       aria-disabled="true">Category: ${requestScope.category}</a>
                                </li>
                            </c:if>

                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </div>

    <c:forEach items="${requestScope.posts}" var="post">
    <div class="row justify-content-center mb-5 mt-5">
        <div class="col-6">

            <div class="card">
                <div class="card-header">
                    <a href="/userView?id=${post.user.id}" class="badge rounded-pill bg-dark" role="button"
                       aria-disabled="true"><h4><span
                            class="badge rounded-pill bg-dark">${post.user.name}</span></h4></a>
                </div>
                <div class="card-body">

                    <img class="card-img-top" src="${post.url}" alt="${post.url}">
                    <h5 class="card-title">${post.title}</h5>
                    <p class="card-text">${post.description}</p>
                </div>
                <div class="card-footer text-muted">
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
                            <h6>Category <a class="badge rounded-pill bg-dark"
                                            href="/posts/byCategory?category=${post.category.name}"
                                            role="button">
                                <span class="badge rounded-pill bg-dark">${post.category.name}</span></a></h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
