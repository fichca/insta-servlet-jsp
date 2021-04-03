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
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <c:if test="${sessionScope.user == null}">
                <li class="navbar-text">
                    Hello Guest
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/registration">Registration</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/auth">Authorization</a>
                </li>
            </c:if>

            <c:if test="${sessionScope.user != null}">
            <li class="navbar-text">
                Hello ${sessionScope.user.name}
            </li>
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="/createPost">Create post</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="/userAccount">Account</a>
            </li>

            <c:if test="${sessionScope.user.role.name().equals('MODERATOR')}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                       role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Moderate
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item"
                               href="/post/view/notApproved">View not approved posts</a>
                        </li>
                    </ul>
                </li>
            </c:if>
            </ul>

            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/logOut">Log out</a>
                </li>

                </c:if>
        </div>
    </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>

</body>
</html>
