<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Crear Nueva Contratación</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
<div class="container">
    <h2>Crear Nueva Contratación</h2>
    <form action="recruitment" method="post">
        <input type="hidden" name="action" value="save">

        <div class="form-group">
            <label for="deptName">Departamento:</label>
            <input type="text" class="form-control" id="deptName" name="deptName" required>
        </div>

        <div class="form-group">
            <label for="employeeName">Empleado:</label>
            <input type="text" class="form-control" id="employeeName" name="employeeName" required>
        </div>

        <div class="form-group">
            <label for="positionName">Cargo:</label>
            <input type="text" class="form-control" id="positionName" name="positionName" required>
        </div>

        <div class="form-group">
            <label for="typeRecruitment">Tipo de Contratación:</label>
            <input type="text" class="form-control" id="typeRecruitment" name="typeRecruitment" required>
        </div>

        <div class="form-group">
            <label for="date_recr">Fecha de Contratación:</label>
            <input type="date" class="form-control" id="date_recr" name="date_recr" required>
        </div>

        <div class="form-group">
            <label for="salary">Salario:</label>
            <input type="number" step="0.01" class="form-control" id="salary" name="salary" required>
        </div>

        <div class="form-group">
            <label for="status">Estado:</label>
            <select class="form-control" id="status" name="status">
                <option value="true">Activo</option>
                <option value="false">Inactivo</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Guardar</button>
        <a href="recruitment?action=list" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>
