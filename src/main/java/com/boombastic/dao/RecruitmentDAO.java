package com.boombastic.dao;

import com.boombastic.config.DBConnection;
import com.boombastic.model.Recruitment;
import com.boombastic.model.TypeRecruitment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
