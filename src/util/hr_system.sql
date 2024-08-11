-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS hr_system;
USE hr_system;

-- Crear la tabla de Departamentos
CREATE TABLE Departamento (
    idDepartamento INT AUTO_INCREMENT PRIMARY KEY,
    nombreDepartamento VARCHAR(50),
    descripcionDepartamento TEXT
);

-- Crear la tabla de TipoContratacion
CREATE TABLE TipoContratacion (
    idTipoContratacion INT AUTO_INCREMENT PRIMARY KEY,
    tipoContratacion VARCHAR(100)
);

-- Crear la tabla de Cargos
CREATE TABLE Cargos (
    idCargo INT AUTO_INCREMENT PRIMARY KEY,
    cargo VARCHAR(50),
    descripcionCargo TEXT,
    jefatura BOOLEAN
);

-- Crear la tabla de Empleados
CREATE TABLE Empleados (
    idEmpleado INT AUTO_INCREMENT PRIMARY KEY,
    numeroDui VARCHAR(9),
    nombrePersona VARCHAR(50),
    usuario VARCHAR(50),
    numeroTelefono VARCHAR(9),
    correoInstitucional VARCHAR(50),
    fechaNacimiento DATE
);

-- Crear la tabla de Contrataciones
CREATE TABLE Contrataciones (
    idContratacion INT AUTO_INCREMENT PRIMARY KEY,
    idDepartamento INT,
    idEmpleado INT,
    idCargo INT,
    idTipoContratacion INT,
    fechaContratacion DATE,
    salario DECIMAL(10,2),
    estado BOOLEAN,
    FOREIGN KEY (idDepartamento) REFERENCES Departamento(idDepartamento),
    FOREIGN KEY (idEmpleado) REFERENCES Empleados(idEmpleado),
    FOREIGN KEY (idCargo) REFERENCES Cargos(idCargo),
    FOREIGN KEY (idTipoContratacion) REFERENCES TipoContratacion(idTipoContratacion)
);

-- Insertar datos en la tabla Departamento
INSERT INTO Departamento (nombreDepartamento, descripcionDepartamento) VALUES
('Recursos Humanos', 'Departamento encargado de la gestión de personal'),
('Tecnología', 'Departamento encargado de la infraestructura tecnológica'),
('Finanzas', 'Departamento encargado de la gestión financiera');

-- Insertar datos en la tabla TipoContratacion
INSERT INTO TipoContratacion (tipoContratacion) VALUES
('Tiempo Completo'),
('Medio Tiempo'),
('Contrato Temporal');

-- Insertar datos en la tabla Cargos
INSERT INTO Cargos (cargo, descripcionCargo, jefatura) VALUES
('Gerente', 'Responsable del departamento', TRUE),
('Analista', 'Encargado de análisis y reportes', FALSE),
('Desarrollador', 'Encargado de desarrollo de software', FALSE);

-- Insertar datos en la tabla Empleados
INSERT INTO Empleados (numeroDui, nombrePersona, usuario, numeroTelefono, correoInstitucional, fechaNacimiento) VALUES
('12345678-9', 'Juan Perez', 'jperez', '55555555', 'jperez@empresa.com', '1985-01-15'),
('23456789-0', 'Maria Gomez', 'mgomez', '55556666', 'mgomez@empresa.com', '1990-03-22'),
('34567890-1', 'Carlos Ruiz', 'cruiz', '55557777', 'cruiz@empresa.com', '1988-07-11');

-- Insertar datos en la tabla Contrataciones
INSERT INTO Contrataciones (idDepartamento, idEmpleado, idCargo, idTipoContratacion, fechaContratacion, salario, estado) VALUES
(1, 1, 1, 1, '2020-02-01', 1500.00, TRUE),
(2, 2, 3, 2, '2021-05-15', 800.00, TRUE),
(3, 3, 2, 3, '2022-11-30', 1200.00, TRUE);
