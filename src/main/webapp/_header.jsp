<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">News feed</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <c:if test="${sessionScope.user == null}">
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Hello Guest</a>


                <a class="nav-link" href="/registration">Registration</a>

                <a class="nav-link" href="/auth">Authorization</a>

            </c:if>

            <c:if test="${sessionScope.user != null}">
                    <a class="nav-link disabled" href="#" tabindex="-1"
                       aria-disabled="true">Hello ${sessionScope.user.name}</a>
                    <a class="nav-link active" aria-current="page" href="/createPost">Create post</a>
                    <a class="nav-link active" aria-current="page" href="/logOut">Log out</a>
            </c:if>

        </div>
    </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>

</body>
</html>
