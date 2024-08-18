package com.boombastic.dao;

import com.boombastic.config.DBConnection;
import com.boombastic.model.Department;
import com.boombastic.model.Employee;
import com.boombastic.model.Recruitment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The DepartmentDAO class provides methods for interacting with a database table storing department
 * information, including retrieving all departments, getting a department by ID, saving a new
 * department, updating an existing department, and deleting a department by ID.
 */
public class DepartmentDAO {

   /**
    * This Java function retrieves all departments from a database table and returns them as a list of
    * Department objects.
    * 
    * @return This method returns a List of Department objects containing information about all
    * departments retrieved from the database table "departamento".
    */
    public List<Department> getAllDepartments() {
        List<Department> departmentList = new ArrayList<>();

        String query = "SELECT * FROM departamento";


        try {
            Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Department dept = new Department();
                dept.setId(rs.getInt("idDepartamento"));
                dept.setDept_name(rs.getString("nombreDepartamento"));
                dept.setDept_description(rs.getString("descripcionDepartamento"));
                departmentList.add(dept);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return departmentList;
    }

   /**
    * This Java function retrieves a department from a database based on the provided department ID.
    * 
    * @param id The `id` parameter in the `getDepartmentById` method is an integer value representing
    * the unique identifier of the department that you want to retrieve from the database. This method
    * fetches department details such as department ID, name, and description based on the provided
    * `id`.
    * @return The method `getDepartmentById` is returning a `Department` object populated with data
    * fetched from the database based on the provided `id`.
    */
    public Department getDepartmentById(int id) {
        Department department = new Department();
        String query = "SELECT idDepartamento, nombreDepartamento, descripcionDepartamento " +
                "FROM departamento WHERE idDepartamento = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                department.setId(rs.getInt("idDepartamento"));
                department.setDept_name(rs.getString("nombreDepartamento"));
                department.setDept_description(rs.getString("descripcionDepartamento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

   /**
    * The `save` method inserts a new department into a database table using the department's name and
    * description as parameters.
    * 
    * @param department - dept_name: The name of the department to be saved.
    */
    public void save(Department department) {
        String sql = "INSERT INTO departamento (nombreDepartamento, descripcionDepartamento) VALUES (?, ?);";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Aquí necesitas adaptar los parámetros para que se correspondan con los IDs
            stmt.setString(1, department.getDept_name());
            stmt.setString(2, department.getDept_description());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /**
    * The `update` method updates a Department object in the database by setting the department name
    * and description based on the provided ID.
    * 
    * @param department The `department` parameter in the `update` method represents an object of the
    * `Department` class. It contains information about a department such as its name, description, and
    * ID.
    */
    public void update(Department department) {
        String sql = "UPDATE departamento SET nombreDepartamento = ?, descripcionDepartamento = ? " +
                "WHERE idDepartamento = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, department.getDept_name());
            stmt.setString(2, department.getDept_description());
            stmt.setInt(3, department.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /**
    * The `delete` method deletes a record from the "departamento" table based on the provided ID.
    * 
    * @param id The `id` parameter in the `delete` method represents the unique identifier of the
    * record that you want to delete from the `departamento` table in the database. This method
    * prepares a SQL query to delete a record from the table where the `idDepartamento` column matches
    * the provided `id
    */
    public void delete(int id) {
        String sql = "DELETE FROM departamento WHERE idDepartamento = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
