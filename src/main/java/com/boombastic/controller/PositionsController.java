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
                listPositions(request, response);
                break;
            case "edit":
                setEditRecruitmentValues(request, response);
                break;
            case "update":
                updatePosition(request, response);
                break;
            case "delete":
                deletePosition(request, response);
                break;
            case "new":
                showNewForm(request, response);
                break;
            case "save":
                savePositions(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
                break;

        }

    }

    private void listPositions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Position> positionsList = positionDao.getAllPositions();
        request.setAttribute("positionsList", positionsList);
        request.getRequestDispatcher("views/positionsList.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/positionForm.jsp").forward(request, response);
    }

    private void savePositions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoge los datos del formulario
        String positionName = request.getParameter("cargo");
        String posDesc = request.getParameter("descCargo");
        int leadership = Integer.parseInt(request.getParameter("jefatura"));

        // Crea un nuevo objeto Recruitment
        Position position = new Position();
        position.setPosition(positionName);
        position.setPosition_description(posDesc);
        position.setLeadership(leadership);

        // Guardar la contratación (puedes implementar la lógica en el DAO)
        positionDao.save(position);

        // Redirigir a la lista de cargos
        response.sendRedirect("positions?action=list");
    }

    private void setEditRecruitmentValues(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoge el ID del departamento a editar
        int id = Integer.parseInt(request.getParameter("id"));

        // Obtiene el cargo a editar
        Position position = positionDao.getPositionById(id);

        // Envía los datos a la vista
        request.setAttribute("position", position);

        // Redirige a la vista de edición
        request.getRequestDispatcher("views/positionUpdateForm.jsp").forward(request, response);
    }

    private void updatePosition(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoge los datos del formulario
        int id = Integer.parseInt(request.getParameter("id"));
        String cargo = request.getParameter("cargo");
        String descCargo = request.getParameter("descCargo");
        int jefatura = Integer.parseInt(request.getParameter("jefatura"));

        // Crea un nuevo objeto Position
        Position position = new Position();
        position.setId(id);
        position.setPosition(cargo);
        position.setPosition_description(descCargo);
        position.setLeadership(jefatura);

        // Guardar el cargo
        positionDao.update(position);

        // Redirigir a la lista de contrataciones
        response.sendRedirect("positions?action=list");
    }

    private void deletePosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        positionDao.delete(id);
        response.sendRedirect("positions?action=list");
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
