package com.boombastic.dao;

import com.boombastic.config.DBConnection;
import com.boombastic.model.Recruitment;
import com.boombastic.model.TypeRecruitment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecruitmentDAO {

    public List<Recruitment> getAllRecruitment() {
        List<Recruitment> recruitmentList = new ArrayList<>();

        String query = "SELECT c.idContratacion, d.nombreDepartamento, e.nombrePersona, ca.cargo, " +
                "t.tipoContratacion, c.fechaContratacion, c.salario, c.estado " +
                "FROM Contrataciones c " +
                "JOIN Departamento d ON c.idDepartamento = d.idDepartamento " +
                "JOIN Empleados e ON c.idEmpleado = e.idEmpleado " +
                "JOIN Cargos ca ON c.idCargo = ca.idCargo " +
                "JOIN TipoContratacion t ON c.idTipoContratacion = t.idTipoContratacion";


        try {
            Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Recruitment recruitment = new Recruitment();
                recruitment.setId(rs.getInt("idContratacion"));
                recruitment.setDeptName(rs.getString("nombreDepartamento"));
                recruitment.setEmployeeName(rs.getString("nombrePersona"));
                recruitment.setPositionName(rs.getString("cargo"));

                TypeRecruitment typeRecruitment = new TypeRecruitment();
                typeRecruitment.setType_recr(rs.getString("tipoContratacion"));
                recruitment.setTypeRecruitment(typeRecruitment);

                recruitment.setDate_recr(rs.getString("fechaContratacion"));
                recruitment.setSalary(rs.getDouble("salario"));
                recruitment.setStatus(rs.getBoolean("estado"));
                recruitmentList.add(recruitment);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recruitmentList;
    }

    public void save(Recruitment recruitment) {
        String sql = "INSERT INTO Contrataciones (idDepartamento, idEmpleado, idCargo, idTipoContratacion, fechaContratacion, salario, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Aquí necesitas adaptar los parámetros para que se correspondan con los IDs
            stmt.setInt(1, getDepartmentIdByName(recruitment.getDeptName())); // Convertir nombre a ID
            stmt.setInt(2, getEmployeeIdByName(recruitment.getEmployeeName())); // Convertir nombre a ID
            stmt.setInt(3, getPositionIdByName(recruitment.getPositionName())); // Convertir nombre a ID
            stmt.setInt(4, getTypeRecruitmentIdByName(recruitment.getTypeRecruitment().getType_recr())); // Convertir nombre a ID
            stmt.setString(5, recruitment.getDate_recr());
            stmt.setDouble(6, recruitment.getSalary());
            stmt.setBoolean(7, recruitment.isStatus());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Contrataciones WHERE idContratacion = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos auxiliares para convertir nombres a IDs (implementación pendiente)
    private int getDepartmentIdByName(String deptName) {
        int id = 0;
        String sql = "SELECT idDepartamento FROM Departamento WHERE nombreDepartamento = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, deptName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("idDepartamento");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private int getEmployeeIdByName(String employeeName) {
        int id = 0;
        String sql = "SELECT idEmpleado FROM Empleados WHERE nombrePersona = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employeeName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("idEmpleado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;

    }

    private int getPositionIdByName(String positionName) {
        int id = 0;
        String sql = "SELECT idCargo FROM Cargos WHERE cargo = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, positionName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("idCargo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private int getTypeRecruitmentIdByName(String typeRecruitment) {
        int id = 0;
        String sql = "SELECT idTipoContratacion FROM TipoContratacion WHERE tipoContratacion = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, typeRecruitment);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("idTipoContratacion");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
