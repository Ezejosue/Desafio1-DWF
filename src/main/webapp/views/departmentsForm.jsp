<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nuevo Departamento</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
<div class="container">
    <h2>Crear Nuevo Departamento</h2>
    <form action="departments" method="post">
        <input type="hidden" name="action" value="save">

        <div class="form-group">
            <label for="deptName">Nombre de departamento:</label>
            <input type="text" class="form-control" id="deptName" name="deptName" required>
        </div>

        <div class="form-group">
            <label for="deptDescription">Descripción del departamento:</label>
            <input type="text" class="form-control" id="deptDescription" name="deptDescription" required>
        </div>

        <button type="submit" class="btn btn-primary">Guardar</button>
        <a href="departments?action=list" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
