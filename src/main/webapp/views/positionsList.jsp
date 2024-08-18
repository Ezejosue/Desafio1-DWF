<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Posiciones</title>
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
                <h1>Lista de posiciones</h1>
            </div>
        </div>

        <div class="row my-4">
            <div class="col text-center">
                <a href="positions?action=new" class="btn btn-success">
                    <i class="bi bi-plus-square-fill"></i> Añadir nueva posición
                </a>
            </div>
        </div>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Posición</th>
                <th>Descripción posición</th>
                <th>Jefatura</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="pos" items="${positionsList}">
                <tr>
                    <td>${pos.id}</td>
                    <td>${pos.position}</td>
                    <td>${pos.position_description}</td>
                    <td>
                        <c:choose>
                            <c:when test="${pos.leadership == 1}">
                                Sí
                            </c:when>
                            <c:otherwise>
                                No
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <a href="positions?action=edit&id=${pos.id}" class="btn btn-primary"><i class="bi bi-pencil-square"></i></a>
                        <a href="#" class="btn btn-danger btn-sm delete-btn" data-id="${pos.id}"><i class="bi bi-trash-fill"></i></a>
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
                    ¿Está seguro que quiere eliminar este cargo?
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

        // Handle delete button click
        $('.delete-btn').on('click', function() {
            // Get the ID from the data-id attribute and build the delete URL
            var posId = $(this).data('id');
            console.log("ID: "+$(this).data('id'))
            deleteUrl = 'positions?action=delete&id=' + posId;

            // Show the confirmation modal
            $('#confirmModal').modal('show');
        });

        // Handle confirmation button click in the modal
        $('#confirmDelete').on('click', function() {
            // Redirect to the delete URL
            window.location.href = deleteUrl;
        });
    });
</script>
</body>
</html>

