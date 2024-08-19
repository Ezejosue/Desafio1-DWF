<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nuevo Empleado</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
</head>
<body>
<div class="container">
    <h2>Crear Nuevo Empleado</h2>
    <form action="employees" method="post">
        <input type="hidden" name="action" value="save">

        <div class="form-group">
            <label for="dui">DUI:</label>
            <input type="text"
                   class="form-control"
                   id="dui"
                   name="dui"
                   required
                   pattern="\d{8}-\d{1}"
                   maxlength="10"
                   placeholder="XXXXXXXX-X">
        </div>

        <div class="form-group">
            <label for="employeeName">Nombre del empleado:</label>
            <input type="text" class="form-control" id="employeeName" name="employeeName" required>
        </div>

        <div class="form-group">
            <label for="username">Nombre de usuario:</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>

        <div class="form-group">
            <label for="phoneNumber">Número de teléfono:</label>
            <input type="text"
                   class="form-control"
                   id="phoneNumber"
                   name="phoneNumber"
                   required
                   pattern="\d{4}-\d{4}"
                   maxlength="9"
                   placeholder="XXXX-XXXX">
        </div>

        <div class="form-group">
            <label for="email">E-Mail:</label>
            <input type="text" class="form-control" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="birthday">Fecha de nacimiento</label>
            <input type="date" class="form-control" id="birthday" name="birthday" required>
        </div>

<%--        <div class="form-group">--%>
<%--            <label for="birthday">Fecha de nacimiento:</label>--%>
<%--            <div class="input-group date" id="birthdayPicker">--%>
<%--                <input type="text" class="form-control" id="birthday" name="birthday" placeholder="YYYY-MM-DD">--%>
<%--                <div class="input-group-append">--%>
<%--                    <span class="input-group-text"><i class="fa fa-calendar"></i></span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>

        <button type="submit" class="btn btn-primary">Guardar</button>
        <a href="employees?action=list" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script>
    $(document).ready(function(){
        $('#birthdayPicker').datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayHighlight: true
        });
    });

    // Validación DUI
    $(document).ready(function(){
        $('#dui').on('keypress', function(e) {
            // Permite solo números
            if (!/^\d$/.test(e.key)) {
                e.preventDefault();
            }
        }).on('input', function() {
            var input = $(this).val().replace(/\D/g, '');  // Remueve todos los caracteres no numéricos que se hayan colado
            if (input.length > 8) {
                input = input.slice(0, 8) + '-' + input.slice(8, 9);
            }
            $(this).val(input);
        });
    });

    // Validación número de teléfono
    $(document).ready(function(){
        $('#phoneNumber').on('keypress', function(e) {
            // Permite solo números
            if (!/^\d$/.test(e.key)) {
                e.preventDefault();
            }
        }).on('input', function() {
            var input = $(this).val().replace(/\D/g, '');  // Remueve todos los caracteres no numéricos que se hayan colado
            if (input.length > 4) {
                input = input.slice(0, 4) + '-' + input.slice(4, 8);
            }
            $(this).val(input);
        });
    });
</script>
</body>
</html>
