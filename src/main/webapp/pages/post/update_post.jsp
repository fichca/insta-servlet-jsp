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
            <form action="/updatePost?postId=${requestScope.post.id}" method="post">

                <p><strong>${requestScope.post.user.name}</strong></p>





                <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-inner">

                        <c:forEach items="${requestScope.post.imgStringBase64}" var="img" varStatus="status">
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





<%--                <div class="mb-3">--%>
<%--                    &lt;%&ndash;                    <label for="URL" class="form-label">Insert URL</label>&ndash;%&gt;--%>
<%--                    <input type="url" value="${requestScope.post.url}" name="URL" class="form-control" id="URL"--%>
<%--                           aria-describedby="URL" required>--%>
<%--                </div>--%>


                <h1 class="display-4">${requestScope.post.title}</h1>

                <textarea type="text" value="${requestScope.post.description}" name="description" class="form-control"
                          id="description"
                          aria-describedby="description" minlength="3" maxlength="255" required></textarea>

                <div class="mb-3">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>

                <c:if test="${requestScope.isErrors}">
                    <div class="alert alert-warning" role="alert">
                        <p>${requestScope.result}</p>
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</div>

</body>
</html>
