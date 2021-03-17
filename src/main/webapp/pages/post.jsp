<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<head>
    <title>
        Hello World!
    </title>
</head>
<jsp:include page="../_header.jsp"/>

<div class="container">
    <c:forEach items="${requestScope.posts}" var="post">
    <div class="row justify-content-center mb-5 mt-5">
        <div class="col-6">
            <div class="card">
                <div class="card-body">
                    <img class="card-img-top" src="${post.url}" alt="${post.url}">
                    <h5 class="card-title">${post.title}</h5>
                    <p class="card-text">${post.description}</p>

                    <div class="row">
                            <div class="col-auto mr-auto">
                                <a href="/viewPost?id=${post.id}" class="btn btn-outline-secondary">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                         fill="currentColor"
                                         class="bi bi-heart" viewBox="0 0 16 16">
                                        <path d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
                                    </svg>
                                </a>
                                <a href="/viewPost?id=${post.id}" class="btn btn-outline-secondary">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                         fill="currentColor"
                                         class="bi bi-chat-square" viewBox="0 0 16 16">
                                        <path d="M14 1a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1h-2.5a2 2 0 0 0-1.6.8L8 14.333 6.1 11.8a2 2 0 0 0-1.6-.8H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h2.5a1 1 0 0 1 .8.4l1.9 2.533a1 1 0 0 0 1.6 0l1.9-2.533a1 1 0 0 1 .8-.4H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
                                    </svg>
                                </a>
                                <a href="/viewPost?id=${post.id}" class="btn btn-outline-secondary">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                         fill="currentColor"
                                         class="bi bi-eye" viewBox="0 0 16 16">
                                        <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
                                        <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
                                    </svg>
                                </a>
                            </div>
                            <div class="col-auto">
                                <a href="/viewPost?id=${post.id}" class="btn btn-outline-secondary">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                         fill="currentColor"
                                         class="bi bi-bookmark" viewBox="0 0 16 16">
                                        <path d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5V2zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1H4z"/>
                                    </svg>
                                </a>
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
    </div>
</div>


</body>
</html>
