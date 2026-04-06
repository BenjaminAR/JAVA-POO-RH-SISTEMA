/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HMC_Modelo;
import java.util.Date;

/**
 *
 * @author Benjamin
 */
public class HMC_Empleado extends HMC_Persona {

    private int employeeId;
    private double salary;
    private int departmentId; // Departamento
    private String jobId; // Puesto
    private String cuentaBancaria;
    private Date hireDate;
    private double comision;
    private String nombreDepartamento;
    private String puesto;
    private String nss;
    private int gerente;

    public HMC_Empleado() {
    }

    public HMC_Empleado(int personaId, String firstName, String lastName, String direccion, String ciudad, String estado, String pais, String continente, String email, String gender, Date birthDay, String Phone_number, String rfc, String curp, int employeeId, double salary, int departmentId, String jobId, String cuentaBancaria, Date hireDate, double comision, String nombreDepartamento, String puesto, String nss, int gerente) {

        super(personaId, firstName, lastName, direccion, ciudad, estado, pais, continente, email, gender, birthDay, Phone_number, rfc, curp);

        this.employeeId = employeeId;
        this.salary = salary;
        this.departmentId = departmentId;
        this.jobId = jobId;
        this.cuentaBancaria = cuentaBancaria;
        this.hireDate = hireDate;
        this.comision = comision;
        this.nombreDepartamento = nombreDepartamento;
        this.puesto = puesto;
        this.nss = nss;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public int getGerente() {
        return gerente;
    }

    public void setGerente(int gerente) {
        this.gerente = gerente;
    }

    
}
