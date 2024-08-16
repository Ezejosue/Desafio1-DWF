package com.boombastic.dao;

import com.boombastic.config.DBConnection;
import com.boombastic.model.Department;
import com.boombastic.model.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PositionDAO {

    public List<Position> getAllPositions() {
        List<Position> positionList = new ArrayList<>();

        String query = "SELECT * FROM cargos";


        try {
            Connection con = new DBConnection().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Position posi = new Position();
                posi.setId(rs.getInt("idCargo"));
                posi.setPosition(rs.getString("cargo"));
                posi.setPosition_description(rs.getString("descripcionCargo"));
                posi.setLeadership(rs.getString("jefatura"));
                positionList.add(posi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return positionList;
    }
}
