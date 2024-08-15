package com.boombastic.controller;

import com.boombastic.config.DBConnection;
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

@WebServlet("/departments")
public class DepartmentController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Department> departments = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "select * from departamento";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("idDepartamento"));
                department.setDept_name(rs.getString("nombreDepartamento"));
                department.setDept_description(rs.getString("descripcionDepartamento"));
                departments.add(department);
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/WEB-INF/jsp/departments.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}
