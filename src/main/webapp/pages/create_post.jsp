
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create post</title>
</head>
<body>
<jsp:include page="../_header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-sm">

            <form action="/createPost" method="post">

                <div class="mb-3">
                    <label for="URL" class="form-label">Insert URL</label>
                    <input type="url" name="URL" class="form-control" id="URL" aria-describedby="URL" required>
                </div>

                <div class="mb-3">
                    <label for="title" class="form-label">Write title</label>
                    <input type="text" name="title" class="form-control" id="title" aria-describedby="title"
                           minlength="3" maxlength="15" required>
                </div>

                <div class="mb-3">
                    <label for="description" class="form-label">Write description</label>
                    <input type="text" name="description" class="form-control" id="description"
                           aria-describedby="description" minlength="3" maxlength="255" required>
                </div>

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
