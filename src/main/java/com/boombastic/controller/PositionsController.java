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
            deletePosition(request, response);
        } else if (action.equals("list")) {
            listPositions(request, response);
        }
    }

    private void listPositions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Position> positionsList = positionDao.getAllPositions();
        request.setAttribute("positionsList", positionsList);
        request.getRequestDispatcher("views/positionsList.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/positionsForm.jsp").forward(request, response);
    }

    private void savePositions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoge los datos del formulario
        String positionName = request.getParameter("positionName");
        String posDesc = request.getParameter("posDescription");
        String leadership = request.getParameter("leadership");

        // Crea un nuevo objeto Recruitment
        Position position = new Position();
        position.setPosition(positionName);
        position.setPosition_description(positionName);
        position.setLeadership(leadership);

        // Guardar la contratación (puedes implementar la lógica en el DAO)
        positionDao.save(position);

        // Redirigir a la lista de contrataciones
        response.sendRedirect("positions?action=list");
    }

    private void deletePosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        positionDao.delete(id);
        response.sendRedirect("positions?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}
