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

/**
 * The `RecruitmentDAO` class in Java provides methods to interact with a database for recruitment data
 * including retrieving all recruitments, saving new recruitments, and deleting recruitments by ID.
 */
public class RecruitmentDAO {

   /**
    * The function retrieves all recruitment data from the database and maps it to a list of
    * Recruitment objects.
    *
    * @return This method returns a list of `Recruitment` objects, which contain information about
    * different recruitments. The method retrieves data from a database query and populates each
    * `Recruitment` object with details such as recruitment ID, department name, employee name,
    * position name, type of recruitment, recruitment date, salary, and status. Finally, it returns a
    * list containing all the populated `Recruitment` objects
    */
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


   /**
    * The `save` method inserts recruitment data into a database table by converting names to
    * corresponding IDs and handling exceptions.
    *
    * @param recruitment The `save` method you provided is responsible for saving recruitment data into
    * a database. It takes a `Recruitment` object as a parameter. The `Recruitment` class seems to have
    * properties such as `deptName`, `employeeName`, `positionName`, `typeRecruitment`, `date
    */
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

   /**
    * The `delete` function deletes a record from the database table `Contrataciones` based on the
    * provided `id`.
    *
    * @param id The `id` parameter in the `delete` method represents the unique identifier of the
    * record that you want to delete from the `Contrataciones` table in the database. When calling this
    * method, you need to pass the specific `id` value of the record that you want to remove from
    */
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

    /**
     * Methods to get the ID of the employee by name, position by name, and type of recruitment by name
     */

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
