<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exam List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/content/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/content/css/table.css">
</head>
<body>
<div class="main_container">
    <jsp:include page="header.jsp">
        <jsp:param name="show_list" value="true"/>
    </jsp:include>
    <main class="content table_page">
        <h3>Specialization List:</h3>
        <table class="table">
            <tr>
                <th scope="col">Specialization Id</th>
                <th scope="col">Name</th>
                <th scope="col">Maximum Students</th>
            </tr>
            <%--@elvariable id="specializations" type="java.util.List"--%>
            <c:forEach var="spec" items="${specializations}">
                <tr>
                    <td><c:out value="${spec.getId()}"/></td>
                    <td><c:out value="${spec.getName()}"/></td>
                    <td><c:out value="${spec.getMaxStudentNumber()}"/></td>
                </tr>
            </c:forEach>
        </table>
    </main>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
