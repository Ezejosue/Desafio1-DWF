package com.boombastic.dao;

import com.boombastic.config.DBConnection;
import com.boombastic.model.Department;
import com.boombastic.model.Recruitment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

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
