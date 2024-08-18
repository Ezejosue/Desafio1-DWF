package com.boombastic.dao;

import com.boombastic.config.DBConnection;
import com.boombastic.model.Department;
import com.boombastic.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

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

    public void updateEmployee(Employee employee) {
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
