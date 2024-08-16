package com.boombastic.controller;

import com.boombastic.dao.RecruitmentDAO;
import com.boombastic.model.Recruitment;
import com.boombastic.model.TypeRecruitment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RecruitmentController", value = "/recruitment")
public class RecruitmentController extends HttpServlet {
    private RecruitmentDAO recruitmentDAO = new RecruitmentDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {
            listRecruitments(request, response);
        } else if (action.equals("edit")) {
            // Lógica para editar una contratación
        } else if (action.equals("delete")) {
            // Lógica para eliminar una contratación
        } else if (action.equals("new")) {
            showNewForm(request, response);
        } else if (action.equals("save")) {
            saveRecruitment(request, response);
        }

    }

    private void listRecruitments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Recruitment> recruitmentList = recruitmentDAO.getAllRecruitment();
        request.setAttribute("recruitmentList", recruitmentList);
        request.getRequestDispatcher("views/recruitmentList.jsp").forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/recruitmentForm.jsp").forward(request, response);
    }

    private void saveRecruitment(HttpServletRequest request, HttpServletResponse response)
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
        recruitmentDAO.save(recruitment);

        // Redirigir a la lista de contrataciones
        response.sendRedirect("recruitment?action=list");
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
