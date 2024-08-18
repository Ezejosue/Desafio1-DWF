<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nuevo Cargo</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
<div class="container">
    <h2>Crear Nuevo Cargo</h2>
    <form action="positions" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${position.id}">

        <div class="form-group">
            <label for="cargo">Nombre del cargo:</label>
            <input type="text" class="form-control" id="cargo" name="cargo" value="${position.position}" required>
        </div>

        <div class="form-group">
            <label for="descCargo">Descripción del cargo:</label>
            <input type="text" class="form-control" id="descCargo" name="descCargo" value="${position.position_description}" required>
        </div>

        <div class="form-group">
            <label for="jefatura">¿Está en posición de liderazgo?</label>
            <select id="jefatura" class="form-control" name="jefatura">
                <c:choose>
                    <c:when test="${position.leadership == 1}">
                        <option value="1" selected>Sí</option>
                        <option value="0">No</option>
                    </c:when>
                    <c:otherwise>
                        <option value="1">Sí</option>
                        <option value="0" selected>No</option>
                    </c:otherwise>
                </c:choose>
            </select>
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
