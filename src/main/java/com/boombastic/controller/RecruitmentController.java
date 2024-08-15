package com.boombastic.controller;

import com.boombastic.dao.RecruitmentDAO;
import com.boombastic.model.Recruitment;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            listRecruitments(request, response);
        } else if (action.equals("add")) {

        } else if (action.equals("edit")) {

        } else if (action.equals("delete")) {

        }
    }

    private void listRecruitments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Recruitment> recruitmentList = recruitmentDAO.getAllRecruitment();
        request.setAttribute("recruitmentList", recruitmentList);
        request.getRequestDispatcher("views/recruitmentList.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Código para agregar o actualizar una contratación
    }
}
