<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Empleados</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Home</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="positions">Cargos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="recruitment">Contrataciones</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="departments">Departamentos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="employees">Empleados</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-5">

    <div class="container mt-5">
        <div class="row">
            <div class="col text-center">
                <h1>Lista de empleados</h1>
            </div>
        </div>

        <div class="row my-4">
            <div class="col text-center">
                <a href="employees?action=new" class="btn btn-success">
                    <i class="bi bi-plus-square-fill"></i> Añadir nuevo empleado
                </a>
            </div>
        </div>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th># DUI</th>
                <th>Nombre</th>
                <th>Username</th>
                <th># Teléfono</th>
                <th>E-Mail</th>
                <th>Fecha nacimiento</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="emp" items="${employeeList}">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.dui}</td>
                    <td>${emp.emp_name}</td>
                    <td>${emp.username}</td>
                    <td>${emp.phone_number}</td>
                    <td>${emp.email}</td>
                    <td>${emp.birthday}</td>
                    <td>
                        <a href="employees?action=edit&id=${emp.id}" class="btn btn-primary"><i class="bi bi-pencil-square"></i></a>
                        <a href="#" class="btn btn-danger btn-sm delete-btn" data-id="${emp.id}"><i class="bi bi-trash-fill"></i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="confirmModalLabel">Confirmar acción</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    ¿Está seguro que quiere eliminar este empleado?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-danger" id="confirmDelete">Confirmar</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
        var deleteUrl;

        // Maneja el click del botón
        $('.delete-btn').on('click', function() {
            // Obtiene la id del atributo data-id y construye la URL para eliminar el empleado
            var empId = $(this).data('id');
            console.log("ID: "+$(this).data('id'))
            deleteUrl = 'employees?action=delete&id=' + empId;

            // Muestra el modal de confirmación
            $('#confirmModal').modal('show');
        });

        // Maneja el click del botón de confirmación en el modal
        $('#confirmDelete').on('click', function() {
            // Redirecciona a la URL para eliminación
            window.location.href = deleteUrl;
        });
    });
</script>
</body>
</html>

