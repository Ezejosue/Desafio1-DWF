package com.boombastic.controller;

import com.boombastic.config.DBConnection;
import com.boombastic.dao.DepartmentDAO;
import com.boombastic.model.Department;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DepartmentsController", value = "/departments")
public class DepartmentController extends HttpServlet {

    private DepartmentDAO departmentDao = new DepartmentDAO();

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
                listDepartments(request, response);
                break;
            case "edit":
                setEditDepartmentValues(request, response);
                break;
            case "update":
                updateDepartment(request, response);
                break;
            case "delete":
                deleteDepartment(request, response);
                break;
            case "new":
                showNewForm(request, response);
                break;
            case "save":
                saveDepartment(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
                break;

        }

    }

    private void listDepartments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departmentsList = departmentDao.getAllDepartments();
        request.setAttribute("departmentsList", departmentsList);
        request.getRequestDispatcher("views/departmentsList.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/departmentsForm.jsp").forward(request, response);
    }

    private void saveDepartment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoge los datos del formulario
        String deptName = request.getParameter("deptName");
        String deptDescription = request.getParameter("deptDescription");

        // Crea un nuevo objeto Recruitment
        Department dept = new Department();
        dept.setDept_name(deptName);
        dept.setDept_description(deptDescription);

        // Guardar la contratación (puedes implementar la lógica en el DAO)
        departmentDao.save(dept);

        // Redirigir a la lista de contrataciones
        response.sendRedirect("departments?action=list");
    }

    private void setEditDepartmentValues(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoge el ID del departamento a editar
        int id = Integer.parseInt(request.getParameter("id"));

        // Obtiene el departamento a editar
        Department department = departmentDao.getDepartmentById(id);

        // Envía los datos a la vista
        request.setAttribute("department", department);

        // Redirige a la vista de edición
        request.getRequestDispatcher("views/departmentUpdateForm.jsp").forward(request, response);
    }

    private void updateDepartment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoge los datos del formulario
        int id = Integer.parseInt(request.getParameter("id"));
        String deptName = request.getParameter("deptName");
        String deptDesc = request.getParameter("deptDescription");

        // Crea un nuevo objeto Department
        Department department = new Department();
        department.setId(id);
        department.setDept_name(deptName);
        department.setDept_description(deptDesc);

        // Guardar el departamento
        departmentDao.update(department);

        // Redirigir a la lista de contrataciones
        response.sendRedirect("departments?action=list");
    }

    private void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        departmentDao.delete(id);
        response.sendRedirect("departments?action=list");
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
