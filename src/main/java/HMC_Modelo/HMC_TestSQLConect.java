/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HMC_Modelo;
import java.sql.Connection;

/**
 *
 * @author Benjamin
 */
public class HMC_TestSQLConect {
    
    public static void main(String[] args) {
        HMC_Conexion conexion = new HMC_Conexion();
        Connection con = conexion.conectar();
        
        if (con != null) {
            System.out.println("Conexion exitosa con Oracle HR");
        } else {
            System.out.println("No se pudo conectar");
        }
    } 
}