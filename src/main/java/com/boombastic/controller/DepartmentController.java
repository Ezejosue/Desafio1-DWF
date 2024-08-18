package com.boombastic.controller;

import com.boombastic.config.DBConnection;
import com.boombastic.dao.DepartmentDAO;
import com.boombastic.model.Department;
import com.boombastic.model.Recruitment;
import com.boombastic.model.TypeRecruitment;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            listDepartments(request, response);
        } else if (action.equals("add")) {

        } else if (action.equals("edit")) {

        } else if (action.equals("delete")) {
            deleteDepartment(request, response);
        }  else if (action.equals("list")) {
            listDepartments(request, response);
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

    private void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        departmentDao.delete(id);
        response.sendRedirect("departments?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}
