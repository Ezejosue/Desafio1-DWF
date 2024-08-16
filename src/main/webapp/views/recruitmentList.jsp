<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Contrataciones</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
<div class="container">
    <a href="recruitment?action=new" class="btn btn-success">Nueva Contratación</a>
    <h2>Lista de Contrataciones</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Departamento</th>
            <th>Empleado</th>
            <th>Cargo</th>
            <th>Tipo de Contratación</th>
            <th>Fecha de Contratación</th>
            <th>Salario</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="recruitment" items="${recruitmentList}">
            <tr>
                <td>${recruitment.deptName}</td>
                <td>${recruitment.employeeName}</td>
                <td>${recruitment.positionName}</td>
                <td>${recruitment.typeRecruitment.type_recr}</td>
                <td>${recruitment.date_recr}</td>
                <td>${recruitment.salary}</td>
                <td>${recruitment.status ? 'Activo' : 'Inactivo'}</td>
                <td>
                    <a href="recruitment?action=edit&id=${recruitment.id}" class="btn btn-primary">Editar</a>
                    <a href="recruitment?action=delete&id=${recruitment.id}" class="btn btn-danger"
                       onclick="return confirm('¿Estás seguro de que deseas eliminar esta contratación?');">Eliminar</a>
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

