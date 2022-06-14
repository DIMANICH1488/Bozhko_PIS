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
        <%--@elvariable id="results" type="java.util.List"--%>
        <c:if test="${results.size() != 0}">
        <h3>Your Exam List:</h3>
        <table class="table">
            <tr>
                <th scope="col">Exam Id</th>
                <th scope="col">Name</th>
                <th scope="col">Date</th>
                <th scope="col">Examiner</th>
            </tr>
            <c:forEach var="result" items="${results}">
                <tr>
                    <td><c:out value="${result.getExam().getId()}"/></td>
                    <td><c:out value="${result.getExam().getName()}"/></td>
                    <td><c:out
                            value="Professor ${result.getExam().getProfessor().getFirstName()} ${result.getExam().getProfessor().getLastName()}"/></td>
                    <td><c:out value="${result.getGrade()}"/></td>
                </tr>
            </c:forEach>
            </c:if>
        </table>
        <h3>Exam List:</h3>
        <table class="table">
            <tr>
                <th scope="col">Exam Id</th>
                <th scope="col">Name</th>
                <th scope="col">Date</th>
                <th scope="col">Examiner</th>
            </tr>
            <%--@elvariable id="exams" type="java.util.List"--%>
            <c:forEach var="exam" items="${exams}">
                <tr>
                    <td><c:out value="${exam.getId()}"/></td>
                    <td><c:out value="${exam.getName()}"/></td>
                    <td><c:out value="${exam.getDate()}"/></td>
                    <td><c:out
                            value="Professor ${exam.getProfessor().getFirstName()} ${exam.getProfessor().getLastName()}"/></td>
                    <td>
                        <a href="?command=select-exam&exam-id=${exam.getId()}">Select</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </main>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
