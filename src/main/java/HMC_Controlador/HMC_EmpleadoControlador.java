/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HMC_Controlador;

import HMC_Modelo.HMC_Empleado;
import HMC_Modelo.HMC_EmpleadoDAO;
import HMC_Vista.HMC_EmpleadoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.Date;

/**
 *
 * @author Benjamin
 */
public class HMC_EmpleadoControlador implements ActionListener {

    private HMC_EmpleadoView vista;
    private HMC_EmpleadoDAO dao;

    public List<HMC_Empleado> obtenerEmpleados() {
        return dao.listar();
    }

    public HMC_EmpleadoControlador(HMC_EmpleadoView v) {
        //this.vista.cargarTabla();
        this.vista = v;
        this.dao = new HMC_EmpleadoDAO();
        this.vista.btnGuardar.addActionListener(this);
        this.dao.cargarComboDepartamentos(this.vista.cbxDepartamento);
        this.dao.cargarComboPuesto(this.vista.cbxPuesto);
        this.dao.cargarComboGerente(this.vista.cbxGerente);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            validarYGuardar();
        }
    }

    private void validarYGuardar() {
        try {
            // Extraer datos de la interfaz
            String nom = vista.txtNombre.getText();
            String ape = vista.txtApellido.getText();
            double sal = Double.parseDouble(vista.txtSalario.getText());
            double comision = Double.parseDouble(vista.txtComision.getText());
            String gen = vista.cbxGenero.getSelectedItem().toString();
            String email = vista.txtEmail.getText();
            java.sql.Date ingreso = null;
            if (vista.txtIngreso.getDate() != null) {
                // Convertimos de java.util.Date (del calendario) a java.sql.Date (para Oracle)
                ingreso = new java.sql.Date(vista.txtIngreso.getDate().getTime());
                
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha de ingreso.");
                return; // Detiene el proceso si no hay fecha
            }
            java.sql.Date nacimiento = null;
            if (vista.txtNacimiento.getDate() != null) {
                // Convertimos de java.util.Date (del calendario) a java.sql.Date (para Oracle)
                nacimiento = new java.sql.Date(vista.txtNacimiento.getDate().getTime());
                
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha de ingreso.");
                return; // Detiene el proceso si no hay fecha
            }
            
            String seleccionGerente = vista.cbxGerente.getSelectedItem().toString();
            int idGerente = Integer.parseInt(seleccionGerente.split(" - ")[0]);
            
            String seleccionDepartamento = vista.cbxDepartamento.getSelectedItem().toString();
            int idDepto = Integer.parseInt(seleccionDepartamento.split(" - ")[0]);
            
            String seleccionPuesto = vista.cbxPuesto.getSelectedItem().toString();
            String[] joId = seleccionPuesto.split(" - ");
            
            String rfc = vista.txtRfc.getText();
            String curp = vista.txtCurp.getText();
            String nss = vista.txtNss.getText();
            String cuenta = vista.txtCuentaBancaria.getText();
            String direccion = vista.txtDireccion.getText();
            
            // Crear objeto usando Polimorfismo y Herencia este objeto despues es capturado por la base de datos a traves de dao.insertar(emp)
            HMC_Empleado emp = new HMC_Empleado();
            emp.setFirstName(nom);
            emp.setLastName(ape);
            emp.setSalary(sal);
            emp.setGender(gen);
            emp.setEmail(email);
            //emp.setEmployeeId(220); 
            emp.setJobId(joId[0].trim());
            //emp.setDepartmentId(50);
            emp.setDepartmentId(idDepto);
            emp.setHireDate(ingreso);
            emp.setBirthDay(nacimiento);
            emp.setComision(comision);
            emp.setRfc(rfc);
            emp.setCurp(curp);
            emp.setNss(nss);
            emp.setCuentaBancaria(cuenta);
            emp.setDireccion(direccion);
            emp.setGerente(idGerente);

            // Ejecutar inserción
            if (dao.insertar(emp)) {
                JOptionPane.showMessageDialog(vista, "Empleado registrado con éxito en BD Oracle.");
                limpiarCampos();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Error: El salario debe ser un número válido.");
        }
    }

    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.txtApellido.setText("");
        vista.txtSalario.setText("");
        vista.txtComision.setText("");
        vista.txtCuentaBancaria.setText("");
        vista.txtIngreso.setCalendar(null);
        vista.txtDireccion.setText("");
//        vista.txtEstado.setText("");
        vista.txtNacimiento.setCalendar(null);
        vista.txtEmail.setText("");
        vista.txtCurp.setText("");
        vista.txtRfc.setText("");
        vista.txtNumTelefono.setText("");
        vista.txtNss.setText("");
        vista.cbxPuesto.setSelectedIndex(0);
//        vista.cbxCiudad.setSelectedIndex(0);
//        vista.cbxContinente.setSelectedIndex(0);
        vista.cbxDepartamento.setSelectedIndex(0);
        vista.cbxGerente.setSelectedIndex(0);
        vista.cbxGenero.setSelectedIndex(0);
//        vista.cbxPais.setSelectedIndex(0);

        obtenerEmpleados();
    }
}
