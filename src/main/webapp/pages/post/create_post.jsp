<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create post</title>
</head>
<body>
<jsp:include page="../../_header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-sm">

            <form action="/createPost" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="formFile1" class="form-label">Upload a picture</label>
                    <input class="form-control" name="img1"  accept="image/*" type="file" id="formFile1">
                </div>

                <div class="mb-3">
                    <label for="formFileMultiple2" class="form-label">Upload a picture</label>
                    <input class="form-control" name="img2" accept="image/*" type="file" id="formFileMultiple2">
                </div>
                    <div class="mb-3">
                        <label for="formFileMultiple3" class="form-label">Upload a picture</label>
                        <input class="form-control" name="img3" accept="image/*" type="file" id="formFileMultiple3">
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
                    <label for="category" class="form-label">Select a category</label>
                    <select class="form-select" id="category" name="category" aria-label="Default select example">
                        <option disabled>Select a category</option>
                        <c:forEach items="${requestScope.categories}" var="category">
                            <option value=${category.id}>${category.name}</option>
                        </c:forEach>
                    </select>
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
