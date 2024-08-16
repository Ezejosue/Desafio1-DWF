package com.boombastic.controller;

import com.boombastic.config.DBConnection;
import com.boombastic.dao.DepartmentDAO;
import com.boombastic.model.Department;
import com.boombastic.model.Recruitment;
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

        }
    }

    private void listDepartments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Department> departmentsList = departmentDao.getAllDepartments();
        request.setAttribute("departmentsList", departmentsList);
        request.getRequestDispatcher("views/departmentsList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}
