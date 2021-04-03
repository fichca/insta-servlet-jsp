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
                <img class="card-img-top" src="${requestScope.post.url}" alt="${requestScope.post.url}">

                <div class="mb-3">
                    <%--                    <label for="URL" class="form-label">Insert URL</label>--%>
                    <input type="url" value="${requestScope.post.url}" name="URL" class="form-control" id="URL"
                           aria-describedby="URL" required>
                </div>


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
