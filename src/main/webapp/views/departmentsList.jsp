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

    <div class="container mt-5">
        <div class="row">
            <div class="col text-center">
                <h1>Lista de departamentos</h1>
            </div>
        </div>

        <div class="row my-4">
            <div class="col text-center">
                <a href="departments?action=new" class="btn btn-success">
                    <i class="bi bi-plus-square-fill"></i> Añadir nuevo departamento
                </a>
            </div>
        </div>

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
                        <a href="departments?action=edit&id=${dept.id}" class="btn btn-primary"><i class="bi bi-pencil-square"></i></a>
                        <a href="#" class="btn btn-danger btn-sm delete-btn" data-id="${dept.id}"><i class="bi bi-trash-fill"></i></a>
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
                    ¿Está seguro que quiere eliminar este departamento?
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
            var deptId = $(this).data('id');
            console.log("ID: "+$(this).data('id'))
            deleteUrl = 'departments?action=delete&id=' + deptId;

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

