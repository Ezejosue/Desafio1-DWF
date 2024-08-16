package com.boombastic.controller;

import com.boombastic.dao.DepartmentDAO;
import com.boombastic.dao.PositionDAO;
import com.boombastic.model.Department;
import com.boombastic.model.Position;
import com.boombastic.model.Recruitment;
import com.boombastic.model.TypeRecruitment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PositionsController", value = "/positions")
public class PositionsController extends HttpServlet {

    private PositionDAO positionDao = new PositionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            listPositions(request, response);
        } else if (action.equals("add")) {

        } else if (action.equals("edit")) {

        } else if (action.equals("delete")) {

        }
    }

    private void listPositions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Position> positionsList = positionDao.getAllPositions();
        request.setAttribute("positionsList", positionsList);
        request.getRequestDispatcher("views/positionsList.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/departmentsForm.jsp").forward(request, response);
    }

    private void savePositions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoge los datos del formulario
        String deptName = request.getParameter("deptName");
        String employeeName = request.getParameter("employeeName");
        String positionName = request.getParameter("positionName");
        String typeRecruitment = request.getParameter("typeRecruitment");
        String date_recr = request.getParameter("date_recr");
        double salary = Double.parseDouble(request.getParameter("salary"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        // Crea un nuevo objeto Recruitment
        Recruitment recruitment = new Recruitment();
        recruitment.setDeptName(deptName);
        recruitment.setEmployeeName(employeeName);
        recruitment.setPositionName(positionName);
        recruitment.setDate_recr(date_recr);
        recruitment.setSalary(salary);
        recruitment.setStatus(status);

        TypeRecruitment typeRecruitmentObj = new TypeRecruitment();
        typeRecruitmentObj.setType_recr(typeRecruitment);
        recruitment.setTypeRecruitment(typeRecruitmentObj);

        // Guardar la contratación (puedes implementar la lógica en el DAO)
//        departmentDao.save(recruitment);

        // Redirigir a la lista de contrataciones
        response.sendRedirect("recruitment?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}
