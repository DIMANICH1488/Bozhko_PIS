
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stanford University</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/content/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/content/css/index.css">
</head>
<body>
<div class="main_container">
    <jsp:include page="header.jsp">
        <jsp:param name="show_list" value="false"/>
    </jsp:include>
    <main class="content">
        <h1> Welcome back!</h1>
        <div class="role_cards">
            <div class="role_card">
                <a href="student"></a>
                <img src="${pageContext.request.contextPath}/content/images/student.png" alt="Student Photo">
                <h4>Students</h4>
            </div>
            <div class="role_card">
                <a href="professor"></a>
                <img src="${pageContext.request.contextPath}/content/images/professor.png" alt="Professor Photo"/>
                <h4>Professors</h4>
            </div>
        </div>
    </main>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
