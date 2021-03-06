<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>wir_home</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/assets/css/Navigation-with-Button1.css">
    <link rel="stylesheet" href="/assets/css/styles.css">
</head>

<body>
    <div>
        <nav class="navbar navbar-default navigation-clean-button">
            <div class="container">
                <div class="navbar-header"><a class="navbar-brand navbar-link" href="#">What I Read</a>
                    <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
                </div>
                <div class="collapse navbar-collapse" id="navcol-1">
                    <ul class="nav navbar-nav"></ul>
                    <p class="navbar-text navbar-right actions"><a class="navbar-link login" href="#">Log In</a> <a class="btn btn-default action-button" role="button" href="#">Sign Up</a></p>
                </div>
            </div>
        </nav>
    </div>
    <div>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <h1>Recently Read</h1>
                    <ul>
                        <c:forEach var="article" items="${mostRecent}">
                            <li><a href='<c:out value="${article.url}"/>'><c:out value="${article.title}"/></a> - <c:out value="${article.source}" /></li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="col-md-4">
                    <h1>Most Read</h1>
                    <ul>
                        <li>Item 1</li>
                        <li>Item 2</li>
                        <li>Item 3</li>
                        <li>Item 4</li>
                    </ul>
                </div>
                <div class="col-md-4">
                    <h1>Top Sources</h1>
                    <ul>
                        <c:forEach var="article" items="${topHosts}">
                            <li><a href='<c:out value="${article}"/>'><c:out value="${article}"/></a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>