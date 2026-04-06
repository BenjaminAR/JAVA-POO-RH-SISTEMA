/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HMC_Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Benjamin
 */
public class HMC_LoginDAO {

    private HMC_Conexion conexion = new HMC_Conexion();

    public boolean validar(String usuario, String password) {
        String sql = """
                SELECT COUNT(*)
                FROM employees
                WHERE UPPER(first_name) = UPPER(?)
                AND employee_id = ?
                AND department_id = 900
                """;

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setInt(2, Integer.parseInt(password));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    

}
