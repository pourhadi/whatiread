<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>What I Read</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/css/jumbotron-narrow.css">
    <link rel="stylesheet" type="text/css" href="/css/home.css">
    <link rel="stylesheet" type="text/css" href="/css/jquery.growl.css"/>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="/js/jquery.growl.js" type="text/javascript"></script>
</head>

<body>

<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li class="active" id="home"><a href="#">Home</a></li>
                <li id="logout"><a href="#">Logout</a></li>
            </ul>
        </nav>
        <h3 class="text-muted">What I Read</h3>
    </div>
    <div class="jumbotron">
        <h3>Hello ${nickname}!</h3>
    </div>
    <div class="row">
        <div class="col-lg-6">
            <h4><a href="javascript: function iprl5() {var d = document, z = d.createElement('scr' + 'ipt'), b = d.body, l = d.location; try {if (!b) throw (0); d.title = '(Saving...) ' + d.title; z.setAttribute('src', l.protocol + '//whatiread.online' + '/post?code=${code}&u=' + encodeURIComponent(l.href) + '&t=' + (new Date().getTime()) + '&n=' + encodeURIComponent(d.title)); b.appendChild(z); } catch (e) {alert('Please wait until the page has loaded.'); } } iprl5(); void(0)">Add to WIR</a></h4>
        <p>Bookmark the above link and use it save articles you've read to WIR.</p>
        </div>
        <div class="col-lg-6">
            <ul>
                <c:forEach var="article" items="${articles}">
                    <li><a href='<c:out value="${article.url}"/>'><c:out value="${article.title}"/></a></li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <footer class="footer">
        <p> &copy; 2016 Company Inc</p>
    </footer>

</div>

<script type="text/javascript">
    $(function () {
        $.growl({title: "Welcome  ${userId}", message: "We hope you enjoy using this site!"});
    });
    $("#logout").click(function(e) {
        e.preventDefault();
        $("#home").removeClass("active");
        $("#password-login").removeClass("active");
        $("#logout").addClass("active");
        // assumes we are not part of SSO so just logout of local session
        window.location = "${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, '')}/logout";
    });
</script>

</body>
</html>