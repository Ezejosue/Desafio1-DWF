<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Contrataciones</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
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
                    <a href="RecruitmentController?action=edit&id=${recruitment.id}" class="btn btn-primary">Editar</a>
                    <a href="RecruitmentController?action=delete&id=${recruitment.id}" class="btn btn-danger">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

