<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<head>
    <title>
        Not approve posts
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

                    <div class="row">
                        <div class="col-auto mr-auto">

                            <form action="/post/approved" method="post">
                                <input type="hidden" value="${post.id}" name="id">
                                <button type="submit" class="badge rounded-pill bg-dark"><h4><span
                                        class="badge rounded-pill bg-dark">approved</span></h4></button>
                            </form>
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
