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

                    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <c:forEach items="${post.imgStringBase64}" var="img" varStatus="status">
                                <c:if test="${status.index == 0}">
                                <div class="carousel-item active">
                                    <img class="card-img-top" src="data:image/jpg;base64,${img}"
                                         alt="data:image/jpg;base64,${img}">
                                </div>
                                </c:if>
                                <c:if test="${status.index != 0}">
                                <div class="carousel-item">
                                    <img class="card-img-top" src="data:image/jpg;base64,${img}"
                                         alt="data:image/jpg;base64,${img}">
                                </div>
                                </c:if>
                            </c:forEach>

                        </div>
                        <button class="carousel-control-prev" type="button"
                                data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Предыдущий</span>
                        </button>
                        <button class="carousel-control-next" type="button"
                                data-bs-target="#carouselExampleControls" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Следующий</span>
                        </button>
                    </div>
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
