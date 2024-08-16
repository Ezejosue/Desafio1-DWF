package com.boombastic.dao;

import com.boombastic.config.DBConnection;
import com.boombastic.model.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
