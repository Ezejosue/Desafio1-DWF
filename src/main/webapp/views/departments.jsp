<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Departments</title>
</head>
<body>
<h2>Departments List</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
    </tr>
    <c:forEach var="dept" items="${departments}">
        <tr>
            <td>${dept.id}</td>
            <td>${dept.dept_name}</td>
            <td>${dept.dept_description}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
