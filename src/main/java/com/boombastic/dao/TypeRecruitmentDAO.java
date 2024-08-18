package com.boombastic.dao;

import com.boombastic.config.DBConnection;
import com.boombastic.model.TypeRecruitment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The TypeRecruitmentDAO class retrieves all type recruitment data from a database table.
 */
public class TypeRecruitmentDAO {

  /**
   * This Java function retrieves all type recruitment records from a database table and returns them
   * as a list of TypeRecruitment objects.
   * 
   * @return This method returns a list of objects of type TypeRecruitment, which contains information
   * about different types of recruitment. The method retrieves this information from a database table
   * called TipoContratacion and populates the list with the data fetched from the database.
   */
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
