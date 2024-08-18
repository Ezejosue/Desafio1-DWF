package com.boombastic.controller;

import com.boombastic.dao.*;
import com.boombastic.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * The `RecruitmentController` class in Java handles recruitment-related actions such as listing,
 * editing, deleting, and saving recruitments using servlets.
 */
@WebServlet(name = "RecruitmentController", value = "/recruitment")
public class RecruitmentController extends HttpServlet {

    // Create DAO objects
    private RecruitmentDAO recruitmentDAO = new RecruitmentDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    private PositionDAO positionDAO = new PositionDAO();
    private TypeRecruitmentDAO typeRecruitmentDAO = new TypeRecruitmentDAO();


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
                listRecruitments(request, response);
                break;
            case "edit":
                setEditRecruitmentValues(request, response);
                break;
            case "update":
                updateRecruitment(request, response);
                break;
            case "delete":
                deleteRecruitment(request, response);
                break;
            case "new":
                showNewForm(request, response);
                break;
            case "save":
                saveRecruitment(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
                break;

        }

    }

    /**
     * The function retrieves a list of recruitments from the database and forwards it to a JSP view
     * for display.
     *
     * @param request  The `HttpServletRequest` object represents the request that a client sends to a
     *                 servlet. It contains information about the client request such as parameters, headers, and
     *                 cookies.
     * @param response The `response` parameter in the `listRecruitments` method is of type
     *                 `HttpServletResponse`. It is used to send the response back to the client, typically a web
     *                 browser, after processing the request. This response can include data, HTML content, or other
     *                 information that the client needs.
     */
    private void listRecruitments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Recruitment> recruitmentList = recruitmentDAO.getAllRecruitment();
        request.setAttribute("recruitmentList", recruitmentList);
        request.getRequestDispatcher("views/recruitmentList.jsp").forward(request, response);

    }

    /**
     * The function `showNewForm` retrieves a list of departments and forwards the request to a
     * recruitment form JSP view.
     *
     * @param request  The `HttpServletRequest` object represents the request that a client sends to a
     *                 servlet. It contains information about the client's request such as parameters, headers, and
     *                 cookies.
     * @param response The `response` parameter in the `showNewForm` method is an object of the
     *                 `HttpServletResponse` class. It is used to send the response back to the client, typically a web
     *                 browser, after processing the request. This response object allows you to set response headers,
     *                 cookies, and content
     */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Department> departmentList = departmentDAO.getAllDepartments();
        List<Position> positionList = positionDAO.getAllPositions();
        List<TypeRecruitment> typeRecruitmentList = typeRecruitmentDAO.getAllTypeRecruitment();
        List<Employee> employeeList = employeeDAO.getAllEmployees();


        request.setAttribute("departmentList", departmentList);
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("positionList", positionList);
        request.setAttribute("typeRecruitmentList", typeRecruitmentList);
        request.getRequestDispatcher("views/recruitmentForm.jsp").forward(request, response);
    }

    /**
     * The `saveRecruitment` function in Java retrieves form data, creates a new Recruitment object with
     * the data, saves it using a DAO, and then redirects to the list of recruitments.
     *
     * @param request  The `saveRecruitment` method you provided is used to save recruitment data from a
     *                 form submitted via a `HttpServletRequest`. Let me explain the parameters you are extracting from
     *                 the request:
     * @param response The `response` parameter in the `saveRecruitment` method is of type
     *                 `HttpServletResponse`. It is used to send a response back to the client (browser) after
     *                 processing the request. In this method, `response.sendRedirect("recruitment?action=list");` is
     *                 used to redirect the client to
     */
    private void saveRecruitment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoge los datos del formulario
        String deptName = request.getParameter("deptName");
        String employeeName = request.getParameter("employeeName");
        String positionName = request.getParameter("positionName");
        String typeRecruitment = request.getParameter("typeRecr");
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

    private void setEditRecruitmentValues(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoge el ID de la contratación a editar
        int id = Integer.parseInt(request.getParameter("id"));

        // Recoge el nombre del empleado
        String employeeName = request.getParameter("employeeName");

        // Obtiene la contratación a editar
        Recruitment recruitment = recruitmentDAO.getRecruitmentById(id);

        // Obtiene la lista de departamentos, posiciones y tipos de contratación
        List<Department> departmentList = departmentDAO.getAllDepartments();
        List<Position> positionList = positionDAO.getAllPositions();
        List<TypeRecruitment> typeRecruitmentList = typeRecruitmentDAO.getAllTypeRecruitment();

        // Envía los datos a la vista
        request.setAttribute("recruitment", recruitment);
        request.setAttribute("departmentList", departmentList);
        request.setAttribute("positionList", positionList);
        request.setAttribute("typeRecruitmentList", typeRecruitmentList);
        request.setAttribute("employeeName", employeeName);

        // Redirige a la vista de edición
        request.getRequestDispatcher("views/recruitmentUpdateForm.jsp").forward(request, response);
    }

    private void updateRecruitment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoge los datos del formulario
        int id = Integer.parseInt(request.getParameter("id"));
        String deptName = request.getParameter("deptName");
        String employeeName = request.getParameter("employeeName");
        String positionName = request.getParameter("positionName");
        String typeRecruitment = request.getParameter("typeRecr");
        String date_recr = request.getParameter("date_recr");
        double salary = Double.parseDouble(request.getParameter("salary"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        // Crea un nuevo objeto Recruitment
        Recruitment recruitment = new Recruitment();
        recruitment.setId(id);
        recruitment.setDeptName(deptName);
        recruitment.setEmployeeName(employeeName);
        recruitment.setPositionName(positionName);
        recruitment.setDate_recr(date_recr);
        recruitment.setSalary(salary);
        recruitment.setStatus(status);

        TypeRecruitment typeRecruitmentObj = new TypeRecruitment();
        typeRecruitmentObj.setType_recr(typeRecruitment);
        recruitment.setTypeRecruitment(typeRecruitmentObj);

        System.out.println(recruitment);
        // Guardar la contratación (puedes implementar la lógica en el DAO)
        recruitmentDAO.update(recruitment);

        // Redirigir a la lista de contrataciones
        response.sendRedirect("recruitment?action=list");
    }

    /**
     * This function deletes a recruitment record based on the provided ID and redirects to the
     * recruitment list page.
     *
     * @param request  The `HttpServletRequest` object represents the request that a client makes to a
     *                 servlet. It contains information about the request such as parameters, headers, and other data
     *                 sent by the client.
     * @param response The `response` parameter in the `deleteRecruitment` method is an object of the
     *                 `HttpServletResponse` class. It is used to send a response back to the client (usually a web
     *                 browser) that made the request. In this method, the `response` object is used to redirect the
     */
    private void deleteRecruitment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        recruitmentDAO.delete(id);
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
