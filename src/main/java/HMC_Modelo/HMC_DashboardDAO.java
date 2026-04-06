/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HMC_Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;



/**
 *
 * @author Benjamin
 */
public class HMC_DashboardDAO {

    private HMC_Conexion conexion = new HMC_Conexion();

    public int obtenerEmpleadosActivos() {
        String sql = "SELECT COUNT(*) FROM employees";

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int obtenerNuevosIngresos() {
        String sql = """
                     SELECT COUNT(*) 
                     FROM employees
                     WHERE hire_date >= ADD_MONTHS(SYSDATE, -1)
                     """;

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int obtenerVacantesAbiertas() {
        // Como HR sample schema no tiene tabla de vacantes,
        // lo simularemos con puestos sin empleados
        String sql = """
                     SELECT COUNT(*)
                     FROM jobs j
                     WHERE NOT EXISTS (
                         SELECT 1
                         FROM employees e
                         WHERE e.job_id = j.job_id
                     )
                     """;

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    
    public Map<String, Integer> obtenerGeneroPorcentaje() {
            Map<String, Integer> datos = new HashMap<>();

            String sql = "SELECT gender, COUNT(*) total FROM employees GROUP BY gender";

            try (Connection con = conexion.conectar();
                 PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    datos.put(rs.getString("gender"), rs.getInt("total"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return datos;
        }

    public Map<String, Integer> obtenerRangosEdad() {
        Map<String, Integer> datos = new LinkedHashMap<>();

        String sql = """
            SELECT
                CASE
                    WHEN FLOOR(MONTHS_BETWEEN(SYSDATE, birth_date)/12) < 30 THEN '<30'
                    WHEN FLOOR(MONTHS_BETWEEN(SYSDATE, birth_date)/12) BETWEEN 30 AND 40 THEN '30-40'
                    WHEN FLOOR(MONTHS_BETWEEN(SYSDATE, birth_date)/12) BETWEEN 41 AND 50 THEN '41-50'
                    ELSE '50+'
                END rango,
                COUNT(*) total
            FROM employees
            GROUP BY
                CASE
                    WHEN FLOOR(MONTHS_BETWEEN(SYSDATE, birth_date)/12) < 30 THEN '<30'
                    WHEN FLOOR(MONTHS_BETWEEN(SYSDATE, birth_date)/12) BETWEEN 30 AND 40 THEN '30-40'
                    WHEN FLOOR(MONTHS_BETWEEN(SYSDATE, birth_date)/12) BETWEEN 41 AND 50 THEN '41-50'
                    ELSE '50+'
                END
            """;

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                datos.put(rs.getString("rango"), rs.getInt("total"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return datos;
    }
    
}