<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Departamentos</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
<div class="container">
    <h2>Lista de departamentos</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre departamento</th>
            <th>Descripción departamento</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="dept" items="${departmentsList}">
            <tr>
                <td>${dept.id}</td>
                <td>${dept.dept_name}</td>
                <td>${dept.dept_description}</td>
                <td>
                    <a href="DepartmentController?action=edit&id=${dept.id}" class="btn btn-primary"><i class="bi bi-pencil-square"></i></a>
                    <a href="DepartmentController?action=delete&id=${dept.id}" class="btn btn-danger"><i class="bi bi-trash-fill"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>

