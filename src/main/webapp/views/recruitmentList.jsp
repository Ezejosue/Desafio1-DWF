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

    <div class="container mt-5">
        <div class="row">
            <div class="col text-center">
                <h1>Lista de Contrataciones</h1>
            </div>
        </div>

        <div class="row my-4">
            <div class="col text-center">
                <a href="recruitment?action=new" class="btn btn-success">
                    <i class="bi bi-plus-square-fill"></i>Nueva Contratación
                </a>
            </div>
        </div>

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
                        <a href="recruitment?action=edit&employeeName=${recruitment.employeeName}&id=${recruitment.id}" class="btn btn-primary"><i class="bi bi-pencil-square"></i></a>
                        <a href="#" class="btn btn-danger btn-sm delete-btn" data-id="${recruitment.id}"><i class="bi bi-trash-fill"></i></a>
                        <!-- <a href="recruitment?action=delete&id= ${recruitment.id}" class="btn btn-danger"
                           onclick="return confirm('¿Estás seguro de que deseas eliminar esta contratación?');">Eliminar</a> -->
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
                    ¿Está seguro que quiere eliminar esta contratacion?
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
            var recruitmentId = $(this).data('id');
            console.log("ID: "+$(this).data('id'))
            deleteUrl = 'recruitment?action=delete&id=' + recruitmentId;

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

