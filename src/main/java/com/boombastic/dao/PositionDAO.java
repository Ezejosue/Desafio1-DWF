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

/**
 * The `PositionDAO` class provides methods for interacting with a database table storing position
 * information including retrieval, insertion, updating, and deletion operations.
 */
public class PositionDAO {

    /**
     * This Java function retrieves all positions from a database table and returns them as a list of
     * Position objects.
     * 
     * @return This method returns a list of `Position` objects, which represent positions retrieved
     * from a database table named `cargos`. The method fetches data from the database, creates
     * `Position` objects for each row in the result set, populates the objects with data from the
     * database, and adds them to a list. Finally, the method returns this list of `Position` objects.
     */
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

   /**
    * This Java function retrieves a Position object from a database based on the provided ID.
    * 
    * @param id The `id` parameter in the `getPositionById` method is used to specify the unique
    * identifier of the position (idCargo) that you want to retrieve from the database. This method
    * fetches details of a position based on the provided `id` and returns a `Position` object
    * containing information such
    * @return The `getPositionById` method returns a `Position` object with the details fetched from
    * the database based on the provided `id`.
    */
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

   /**
    * The `save` method inserts a new position into a database table called `cargos` with the provided
    * position details.
    * 
    * @param position The `position` parameter seems to be an object of the `Position` class. It likely
    * contains information about a specific job position such as the position name, position
    * description, and leadership level.
    */
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

    /**
     * The `update` function updates a record in the `cargos` table with new values for cargo,
     * description, and leadership based on the provided Position object.
     * 
     * @param position The `update` method is used to update a record in the `cargos` table in a
     * database. The method takes a `Position` object as a parameter. The `Position` class likely has
     * the following attributes:
     */
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

   /**
    * The `delete` method deletes a record from the `cargos` table in a database based on the provided
    * `id`.
    * 
    * @param id The `id` parameter in the `delete` method represents the unique identifier of the cargo
    * that you want to delete from the database. This method executes a SQL query to delete a record
    * from the `cargos` table where the `idCargo` column matches the provided `id` value.
    */
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
