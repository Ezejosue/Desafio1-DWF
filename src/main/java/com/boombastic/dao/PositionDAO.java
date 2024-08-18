package com.boombastic.dao;

import com.boombastic.config.DBConnection;
import com.boombastic.model.Department;
import com.boombastic.model.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                posi.setLeadership(rs.getInt("jefatura"));
                positionList.add(posi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return positionList;
    }

    public Position getPositionById(int id) {
        Position position = new Position();
        String query = "SELECT idCargo, cargo, descripcionCargo, jefatura FROM cargos " +
                "WHERE idCargo = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                position.setId(rs.getInt("idCargo"));
                position.setPosition(rs.getString("cargo"));
                position.setPosition_description(rs.getString("descripcionCargo"));
                position.setLeadership(rs.getInt("jefatura"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return position;
    }

    public void save(Position position) {
        String sql = "INSERT INTO cargos (cargo, descripcionCargo, jefatura) VALUES (?, ?, ?);";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Aquí necesitas adaptar los parámetros para que se correspondan con los IDs
            stmt.setString(1, position.getPosition());
            stmt.setString(2, position.getPosition_description());
            stmt.setInt(3, position.getLeadership());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Position position) {
        String sql = "UPDATE cargos SET cargo = ?, descripcionCargo = ?, jefatura = ? " +
                "WHERE idCargo = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, position.getPosition());
            stmt.setString(2, position.getPosition_description());
            stmt.setInt(3, position.getLeadership());
            stmt.setInt(4, position.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM cargos WHERE idCargo = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
