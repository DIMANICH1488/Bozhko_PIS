<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>SignIn</title>
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/content/css/style.css">
    <script crossorigin="anonymous" src="https://kit.fontawesome.com/9d8814d242.js"></script>
</head>

<body>
<div class="main_container">
    <jsp:include page="header.jsp"/>
    <main class="content">
        <jsp:include page="signInForm.jsp">
            <jsp:param name="formTitle" value="Sign In"/>
        </jsp:include>
    </main>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>