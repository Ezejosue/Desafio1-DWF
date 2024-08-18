package com.boombastic.dao;

import com.boombastic.config.DBConnection;
import com.boombastic.model.TypeRecruitment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeRecruitmentDAO {

    public List<TypeRecruitment> getAllTypeRecruitment() {
        List<TypeRecruitment> typeRecruitmentList = new ArrayList<>();

        String query = "SELECT * FROM TipoContratacion";

        try {
            Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TypeRecruitment typeRecruitment = new TypeRecruitment();
                typeRecruitment.setId(rs.getInt("idTipoContratacion"));
                typeRecruitment.setType_recr(rs.getString("tipoContratacion"));

                typeRecruitmentList.add(typeRecruitment);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return typeRecruitmentList;


    }
}
