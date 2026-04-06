/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HMC_Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Benjamin
 */
public class HMC_EmpleadoDAO {
    
    //Gestion de coneccion con la base de datos.
    HMC_Conexion conectar = new HMC_Conexion();
    Connection con;
    PreparedStatement ps;

    //Metodo para obtener los empleados segun emp_details_view
    public List<HMC_Empleado> listar() {
        List<HMC_Empleado> lista = new ArrayList<>();

        String sql = "select EMPLOYEE_ID as \"Empleo ID\", "
                + "FIRST_NAME as \"Nombres\", "
                + "LAST_NAME as \"Apellido\", "
                + "SALARY as \"Salario\", "
                + "COMMISSION_PCT as \"Porcentaje de comision\", " // ok
                + "DEPARTMENT_NAME as \"Departamento\", " //ok
                + "JOB_TITLE as \"Puesto\", " //ok
                + "CITY as \"Cuidad\", " // ok
                + "STATE_PROVINCE as \"Estado o provincia\", " // ok
                + "COUNTRY_NAME as \"Pais\", "
                + "REGION_NAME as \"Continente\" "
                + "from emp_details_view";

        HMC_Conexion conexion = new HMC_Conexion();

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HMC_Empleado emp = new HMC_Empleado();

                emp.setEmployeeId(rs.getInt("Empleo ID"));
                emp.setFirstName(rs.getString("Nombres"));
                emp.setLastName(rs.getString("Apellido"));
                emp.setSalary(rs.getDouble("Salario"));
                emp.setComision(rs.getDouble("Porcentaje de comision"));
                emp.setNombreDepartamento(rs.getString("Departamento"));
                emp.setPuesto(rs.getString("Puesto"));
                emp.setCiudad(rs.getString("Cuidad"));
                emp.setEstado(rs.getString("Estado o provincia"));
                emp.setPais(rs.getString("Pais"));
                emp.setContinente(rs.getString("Continente"));

                lista.add(emp);
            }

        } catch (Exception e) {
            System.err.println("Error al listar empleados: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    //Metodo para insertar un objeto de tipo empleado a la base de datos en la tabla employees
    public boolean insertar(HMC_Empleado emp) {
        
        String sql = "INSERT INTO employees ("
                + "employee_id, first_name, last_name, email, phone_number, "
                + "hire_date, job_id, salary, commission_pct, manager_id, "
                + "department_id, gender, birth_date, direccion_personal, "
                + "rfc, curp, nss, cuenta_bancaria) "
                + "VALUES (EMPLOYEES_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        HMC_Conexion conexion = new HMC_Conexion();

        
        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            
            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getPhone_number());

            // Manejo de Fechas (Convertir java.util.Date a java.sql.Date)
            ps.setDate(5, new java.sql.Date(emp.getHireDate().getTime()));

            ps.setString(6, emp.getJobId());
            ps.setDouble(7, emp.getSalary());

            // Manejo de Nulos para Commission (Si es 0 o nulo)
            if (emp.getComision() > 0) {
                ps.setDouble(8, emp.getComision());
            } else {
                ps.setNull(8, java.sql.Types.DOUBLE);
            }

            ps.setInt(9, emp.getGerente());
            ps.setInt(10, emp.getDepartmentId());
            ps.setString(11, emp.getGender());

            // Fecha de Nacimiento
            ps.setDate(12, new java.sql.Date(emp.getBirthDay().getTime()));

            ps.setString(13, emp.getDireccion());
            ps.setString(14, emp.getRfc());
            ps.setString(15, emp.getCurp());
            ps.setString(16, emp.getNss());
            ps.setString(17, emp.getCuentaBancaria());

            // 3. Ejecución
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            // Log específico para SQL (ayuda a depurar errores de constraints o tipos)
            System.err.println("!!!!!!!-- Error al insertar empleado: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("!!!!!!!-- Error general: " + e.getMessage());
            return false;
        }
    }

    
    //Clase para obtener los header de la tabla segun en procedimiento almacenado emp_details_view
    public DefaultTableModel obtenerModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "select EMPLOYEE_ID as \"Empleo ID\", "
                + "FIRST_NAME as \"Nombres\", "
                + "LAST_NAME as \"Apellido\", "
                + "SALARY as \"Salario\", "
                + "COMMISSION_PCT as \"Porcentaje de comision\", "
                + "DEPARTMENT_NAME as \"Departamento\", "
                + "JOB_TITLE as \"Puesto\", "
                + "CITY as \"Cuidad\", "
                + "STATE_PROVINCE as \"Estado o provincia\", "
                + "COUNTRY_NAME as \"Pais\", "
                + "REGION_NAME as \"Continente\" "
                + "from emp_details_view WHERE ROWNUM <= 1";
        HMC_Conexion conexion = new HMC_Conexion();

        try (Connection con = conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int numeroColumnas = metaData.getColumnCount();

            // 1. Agregar las cabeceras automáticamente
            for (int i = 1; i <= numeroColumnas; i++) {
                modelo.addColumn(metaData.getColumnName(i));
            }

            // Aquí podrías agregar un bucle para llenar los datos si lo deseas
        } catch (SQLException e) {
            System.out.println("Error al obtener metadatos: " + e.getMessage());
        }
        return modelo;
    }
    
    //Metodo para cargar los Departamentos en el ComboBox cbxDepartamentos
    public void cargarComboDepartamentos(JComboBox combo) {
        String sql = "SELECT department_id, department_name FROM departments ORDER BY department_name";
        try (Connection con = conectar.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            combo.removeAllItems();
            combo.addItem("0 - Seleccione Departamento");
            while (rs.next()) {
                combo.addItem(rs.getInt("department_id") + " - " + rs.getString("department_name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar departamentos: " + e.getMessage());
        }
    }
    
    //Metodo para cargar los Puestos en el ComboBox cbxPuestos
    public void cargarComboPuesto(JComboBox combo) {
        String sql = "SELECT job_id, job_title FROM JOBS ORDER BY job_title ";
        try (Connection con = conectar.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            combo.removeAllItems();
            combo.addItem("0 - Seleccione Puesto");
            while (rs.next()) {
                combo.addItem(rs.getString("job_id") + " - " + rs.getString("job_title"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar empleos: " + e.getMessage());
        }
    }
    
        //Metodo para cargar los Puestos en el ComboBox cbxPuestos
    public void cargarComboGerente(JComboBox combo) {
        String sql = "SELECT employee_id, first_name, last_name FROM employees ORDER BY employee_id";
        try (Connection con = conectar.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            System.out.println(rs);
            combo.removeAllItems();
            combo.addItem("0 - Seleccione gerente");
            while (rs.next()) {
                combo.addItem(rs.getString("employee_id") + " - " + rs.getString("first_name") + " " + rs.getString("last_name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar gerentes: " + e.getMessage());
        }
    }

//        public void cargarComboPais(JComboBox combo) {
//        String sql = "SELECT location_id, CITY FROM LOCATIONS ORDER BY  CITY DESC";
//        try (Connection con = conectar.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
//
//            combo.removeAllItems();
//            combo.addItem("0 - Seleccione Pais");
//            while (rs.next()) {
//                combo.addItem(rs.getInt("location_id") + " - " + rs.getString("CITY"));
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error al cargar paises: " + e.getMessage());
//        }
//    }
//        
//            public void cargarComboContinente(JComboBox combo) {
//        String sql = "SELECT location_id, CITY FROM LOCATIONS ORDER BY  CITY DESC";
//        try (Connection con = conectar.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
//
//            combo.removeAllItems();
//            combo.addItem("0 - Seleccione Pais");
//            while (rs.next()) {
//                combo.addItem(rs.getInt("location_id") + " - " + rs.getString("CITY"));
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error al cargar paises: " + e.getMessage());
//        }
//    }    
}
