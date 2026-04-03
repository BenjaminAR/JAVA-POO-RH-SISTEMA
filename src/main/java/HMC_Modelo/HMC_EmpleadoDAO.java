/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HMC_Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin
 */

public class HMC_EmpleadoDAO {
    
    public List<HMC_Empleado> listar() {
    List<HMC_Empleado> lista = new ArrayList<>();
    
    String sql = "SELECT employee_id, first_name, last_name, email, salary, department_id, job_id FROM employees";

    HMC_Conexion conexion = new HMC_Conexion();

    try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
             while (rs.next()) {
                 HMC_Empleado emp = new HMC_Empleado();
                 emp.setEmployeeId(rs.getInt("employee_id"));
                 emp.setFirstName(rs.getString("first_name"));
                 emp.setLastName(rs.getString("last_name"));
                 emp.setEmail(rs.getString("email"));
                 emp.setSalary(rs.getDouble("salary"));
                 emp.setDepartmentId(rs.getInt("department_id"));
                 emp.setJobId(rs.getString("job_id"));
                 
                 lista.add(emp);
             
             }    
                 
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    return lista;
    
    } 
}
