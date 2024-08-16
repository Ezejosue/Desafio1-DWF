<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <!-- Brand or Home Link -->
    <a class="navbar-brand" href="#">Home</a>

    <!-- Toggler/collapsible Button for mobile view -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <!-- Navbar Links -->
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="#">Cargos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="recruitment">Contrataciones</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="departments">Departamentos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Empleados</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <a href="#" class="grid-item">
                Cargos
            </a>
        </div>
        <div class="col-md-5">
            <a href="recruitment" class="grid-item">
                Contrataciones
            </a>
        </div>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-5">
            <a href="departments" class="grid-item">
                Departamentos
            </a>
        </div>
        <div class="col-md-5">
            <a href="#" class="grid-item">
                Empleados
            </a>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>