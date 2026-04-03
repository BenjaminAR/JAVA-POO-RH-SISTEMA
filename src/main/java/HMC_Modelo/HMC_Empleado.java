/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HMC_Modelo;

/**
 *
 * @author Benjamin
 */
public class HMC_Empleado {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private double salary;
    private int departmentId;
    private String JobId;

    public HMC_Empleado() {
    }

    public HMC_Empleado(int employeeId, String firstName, String lastName, String email, double salary, int departmentId, String JobId) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.salary = salary;
        this.departmentId = departmentId;
        this.JobId = JobId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return JobId;
    }

    public void setJobId(String JobId) {
        this.JobId = JobId;
    }
    

}
