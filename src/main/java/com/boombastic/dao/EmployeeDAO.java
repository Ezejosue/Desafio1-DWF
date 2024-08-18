package com.boombastic.dao;

import com.boombastic.config.DBConnection;
import com.boombastic.model.Department;
import com.boombastic.model.Employee;
import com.boombastic.model.Recruitment;
import com.boombastic.model.TypeRecruitment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The `EmployeeDAO` class provides methods for interacting with an employee database table including
 * retrieving, saving, updating, and deleting employee records.
 */
public class EmployeeDAO {
/**
 * This Java function retrieves all employees from a database table and returns them as a list of
 * Employee objects.
 * 
 * @return The `getAllEmployees` method returns a List of Employee objects containing information
 * retrieved from a database table named "empleados".
 */

    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        String query = "SELECT * FROM empleados";


        try {
            Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("idEmpleado"));
                employee.setDui(rs.getString("numeroDui"));
                employee.setEmp_name(rs.getString("nombrePersona"));
                employee.setUsername(rs.getString("usuario"));
                employee.setPhone_number(rs.getString("numeroTelefono"));
                employee.setEmail(rs.getString("correoInstitucional"));
                employee.setBirthday(rs.getDate("fechaNacimiento"));
                employeeList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    /**
     * The function retrieves an employee's information from a database based on their ID.
     * 
     * @param id The `getEmployeeById` method retrieves an employee's information from a database based
     * on the provided employee ID. The method constructs a SQL query to select specific columns from
     * the `empleados` table where the `idEmpleado` matches the given ID parameter.
     * @return An Employee object with the details fetched from the database for the employee with the
     * specified ID is being returned.
     */
    public Employee getEmployeeById(int id) {
        Employee employee = new Employee();
        String query = "SELECT idEmpleado, numeroDui, nombrePersona, usuario, numeroTelefono, correoInstitucional, fechaNacimiento " +
                "FROM empleados WHERE idEmpleado = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                employee.setId(rs.getInt("idEmpleado"));
                employee.setDui(rs.getString("numeroDui"));
                employee.setEmp_name(rs.getString("nombrePersona"));
                employee.setUsername(rs.getString("usuario"));
                employee.setPhone_number(rs.getString("numeroTelefono"));
                employee.setEmail(rs.getString("correoInstitucional"));
                employee.setBirthday(rs.getDate("fechaNacimiento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

/**
 * The `save` method inserts employee data into a database table using prepared statements in Java.
 * 
 * @param employee Based on the code snippet provided, the `save` method is responsible for saving an
 * `Employee` object into a database table named `empleados`. The method takes an `Employee` object as
 * a parameter and extracts various attributes from it to populate the corresponding columns in the
 * database table.
 */
    public void save(Employee employee) {
        String sql = "INSERT INTO empleados (numeroDui, nombrePersona, usuario, numeroTelefono, correoInstitucional, fechaNacimiento) " +
                "VALUES (?, ?, ?, ?, ?, ?);";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Aquí necesitas adaptar los parámetros para que se correspondan con los IDs
            stmt.setString(1, employee.getDui());
            stmt.setString(2, employee.getEmp_name());
            stmt.setString(3, employee.getUsername());
            stmt.setString(4, employee.getPhone_number());
            stmt.setString(5, employee.getEmail());
            stmt.setDate(6, employee.getBirthday());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /**
    * The `updateEmployee` method updates employee information in a database using SQL UPDATE
    * statement.
    * 
    * @param employee The `updateEmployee` method is used to update an employee record in a database
    * table. The method takes an `Employee` object as a parameter, which contains the following
    * attributes:
    */
    public void update(Employee employee) {
        String sql = "UPDATE empleados SET numeroDui = ?, nombrePersona = ?, usuario = ?, numeroTelefono = ?, correoInstitucional = ?, fechaNacimiento = ? " +
                "WHERE empleados.idEmpleado = ?;";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getDui());
            stmt.setString(2, employee.getEmp_name());
            stmt.setString(3, employee.getUsername());
            stmt.setString(4, employee.getPhone_number());
            stmt.setString(5, employee.getEmail());
            stmt.setDate(6, employee.getBirthday());
            stmt.setInt(7, employee.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 /**
  * The `delete` function deletes a record from the `empleados` table based on the provided `id`.
  * 
  * @param id The `delete` method is used to delete a record from the `empleados` table in a database
  * based on the `idEmpleado` field matching the provided `id` parameter. The `id` parameter is the
  * unique identifier of the record that needs to be deleted.
  */
    public void delete(int id) {
        String sql = "DELETE FROM empleados WHERE idEmpleado = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
