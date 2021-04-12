<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<head>
    <title>
        ${sessionScope.user.name}
    </title>
</head>
<jsp:include page="../../_header.jsp"/>

<div class="container">
    <div class="row justify-content-center mb-5 mt-5">


        <div class="col-6">
            <div class="row justify-content-between">
                <div class="col">
                    <div class="jumbotron">
                        <h1 class="display-4">${requestScope.user.name}</h1>
                        <hr class="my-4">
                    </div>
                </div>

                <div class="col-md-4 ms-auto">
                    <c:if test="${sessionScope.user != null}">
                        <c:if test="${sessionScope.user != requestScope.user}">
                            <c:if test="${!sessionScope.user.subscriptions.contains(requestScope.user)}">
                                <form action="/user/addSubscriber" method="post">
                                    <input type="hidden" value="${requestScope.user.login}" name="login">
                                    <button type="submit" class="btn btn-dark">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-person-plus" viewBox="0 0 16 16">
                                            <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                                            <path fill-rule="evenodd"
                                                  d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
                                        </svg>
                                        Subscribe
                                    </button>
                                </form>
                            </c:if>


                            <c:if test="${sessionScope.user.subscriptions.contains(requestScope.user)}">
                                <div class="btn-group" role="group">
                                    <form action="/user/deleteSubscriber" method="post">
                                        <input type="hidden" value="${requestScope.user.login}" name="login">
                                        <button type="submit" class="btn btn-dark"><i class="bi bi-person-x"></i>
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                 fill="currentColor" class="bi bi-person-x" viewBox="0 0 16 16">
                                                <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                                                <path fill-rule="evenodd"
                                                      d="M12.146 5.146a.5.5 0 0 1 .708 0L14 6.293l1.146-1.147a.5.5 0 0 1 .708.708L14.707 7l1.147 1.146a.5.5 0 0 1-.708.708L14 7.707l-1.146 1.147a.5.5 0 0 1-.708-.708L13.293 7l-1.147-1.146a.5.5 0 0 1 0-.708z"/>
                                            </svg>
                                            Unsubscribe
                                        </button>
                                    </form>

                                    <form action="/dialog/view" method="post">
                                        <input type="hidden" value="${requestScope.user.login}" name="fistUser">
                                        <input type="hidden" value="${sessionScope.user.login}" name="secondUser">
                                        <button type="submit" class="btn btn-outline-dark">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                                 fill="currentColor" class="bi bi-envelope" viewBox="0 0 16 16">
                                                <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2zm13 2.383l-4.758 2.855L15 11.114v-5.73zm-.034 6.878L9.271 8.82 8 9.583 6.728 8.82l-5.694 3.44A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.739zM1 11.114l4.758-2.876L1 5.383v5.73z"/>
                                            </svg>
                                        </button>
                                    </form>

                                </div>
                            </c:if>
                        </c:if>
                    </c:if>
                </div>

            </div>
        </div>

    </div>

    <c:forEach items="${requestScope.posts}" var="post">
    <div class="row justify-content-center mb-5 mt-5">
        <div class="col-6">
            <div class="card">
                <div class="card-header">
                    <h4><strong>${post.user.name}</strong></h4>
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
                            <h6>Category <span class="badge rounded-pill bg-dark">${post.category.name}</span></h6>
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