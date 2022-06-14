
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cabinet ${name}</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/content/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/content/css/main_page.css">
</head>
<body>
<div class="main_container">
    <jsp:include page="header.jsp">
        <jsp:param name="show_list" value="true"/>
    </jsp:include>
    <main class="content">
        <h1> Welcome back </h1>
        <div class="slogan">
            <div></div>
            <h2>${name}</h2>
            <div></div>
        </div>
    </main>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
