<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/content/css/toggle_header.css">
</head>
<body>
<header class="header">
    <c:if test="${param.show_list}">
        <input class="toggler" type="checkbox">
        <div class="hamburger">
            <div></div>
        </div>
        <div class="menu">
            <ul>
                    <%--@elvariable id="navbar" type="java.util.Map"--%>
                <c:forEach items="${navbar}" var="item">
                    <li><a href=${item.value}>${item.key}</a></li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <h1>Stanford University</h1>
</header>

</body>
</html>
