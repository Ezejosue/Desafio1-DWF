package com.boombastic.controller;

import com.boombastic.dao.EmployeeDAO;
import com.boombastic.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "EmployeesController", value = "/employees")
public class EmployeeController extends HttpServlet{

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    /**
     * The function `processRequest` handles HTTP requests by setting character encoding, retrieving the
     * action parameter, and calling a method to handle the action.
     *
     * @param request  The `HttpServletRequest` object represents the request that a client makes to a
     *                 servlet. It contains information about the request such as parameters, headers, and cookies. The
     *                 servlet uses this object to read the client's request data.
     * @param response The `response` parameter in the `processRequest` method is of type
     *                 `HttpServletResponse`. It is used to send a response back to the client who made the request.
     *                 This response can include data, status codes, headers, and more. It is an essential part of
     *                 handling HTTP requests and providing
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            String action = request.getParameter("action");
            handleAction(action, request, response);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * The function `handleAction` processes different actions based on a given parameter and calls
     * corresponding methods to perform tasks such as listing, editing, deleting, creating, or saving
     * recruitment data.
     *
     * @param action   The `action` parameter in the `handleAction` method represents the action that the
     *                 servlet should perform. It is used to determine which operation to execute based on the value of
     *                 the action parameter passed in the request.
     * @param request  The `HttpServletRequest` object represents the request that a client sends to a
     *                 servlet. It contains information about the request such as parameters, headers, and cookies.
     * @param response The `response` parameter in the `handleAction` method is of type
     *                 `HttpServletResponse`. It is used to send a response back to the client that made the request.
     *                 This response can include data, status codes, headers, and more. In the provided code snippet,
     *                 the `response` parameter
     */
    protected void handleAction(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (action == null) {
            action = "list";
        }


        switch (action) {
            case "list":
                listEmployees(request, response);
                break;
            case "edit":
                setEditEmployeeValues(request, response);
                break;
            case "update":
                updateEmployee(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            case "new":
                showNewForm(request, response);
                break;
            case "save":
                saveEmployee(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
                break;

        }

    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Employee> employeeList = employeeDAO.getAllEmployees();
        request.setAttribute("employeeList", employeeList);
        request.getRequestDispatcher("views/employeesList.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/employeesForm.jsp").forward(request, response);
    }

    private void saveEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoge los datos del formulario
        String dui = request.getParameter("dui");
        String empName = request.getParameter("employeeName");
        String username = request.getParameter("username");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        Date birthday = Date.valueOf(request.getParameter("birthday"));

        // Crea un nuevo objeto Employee
        Employee employee = new Employee();
        employee.setDui(dui);
        employee.setEmp_name(empName);
        employee.setUsername(username);
        employee.setPhone_number(phoneNumber);
        employee.setEmail(email);
        employee.setBirthday(birthday);

        employeeDAO.save(employee);

        // Redirigir a la lista de contrataciones
        response.sendRedirect("employees?action=list");
    }

    private void setEditEmployeeValues(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoge el ID del empleado a editar
        int id = Integer.parseInt(request.getParameter("id"));

        // Obtiene el empleado a editar
        Employee employee = employeeDAO.getEmployeeById(id);

        // Envía los datos a la vista
        request.setAttribute("employee", employee);

        // Redirige a la vista de edición
        request.getRequestDispatcher("views/employeeUpdateForm.jsp").forward(request, response);
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoge los datos del formulario
        int id = Integer.parseInt(request.getParameter("id"));
        String dui = request.getParameter("dui");
        String empName = request.getParameter("employeeName");
        String username = request.getParameter("username");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        Date birthday = Date.valueOf(request.getParameter("birthday"));

        // Crea un nuevo objeto Recruitment
        Employee employee = new Employee();
        employee.setId(id);
        employee.setDui(dui);
        employee.setEmp_name(empName);
        employee.setUsername(username);
        employee.setPhone_number(phoneNumber);
        employee.setEmail(email);
        employee.setBirthday(birthday);

        employeeDAO.update(employee);

        // Redirigir a la lista de contrataciones
        response.sendRedirect("employees?action=list");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recoge el ID del empleado a eliminar
        int id = Integer.parseInt(request.getParameter("id"));

        // Ejecuta la eliminación del empleado
        employeeDAO.delete(id);

        // Redirige a la vista de lista
        response.sendRedirect("employees?action=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
