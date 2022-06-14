
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/content/css/style.css">
</head>
<body>
<form class="form" action="j_security_check">
    <input type="hidden" name="command" value="login">
    <div class="form_title">
        <h2>${param.formTitle}</h2>
    </div>
    <fieldset>
        <div class="sign_parameter" id="email_parameter">
            <label for="email">Email</label>
            <input class="text_input" id="email" name="j_username">
        </div>

        <div class="sign_parameter">
            <label for="password">Password</label>
            <input class="text_input" id="password" type="password" name="j_password">
        </div>
    </fieldset>
    <input class="red_button" name="Sign In" type="submit" value="Submit">
</form>

</body>
</html>
