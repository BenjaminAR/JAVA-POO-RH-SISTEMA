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
public class HMC_Persona {

    private int personaId;
    public String firstName;
    public String lastName;
    private String direccion;
    private String ciudad;
    public String estado;
    public String pais;
    public String continente;
    private String email;
    public String gender;
    private Date birthDay;
    private String Phone_number;
    private String rfc;
    private String curp;
    
        

    public HMC_Persona() {

    }

    public HMC_Persona(int personaId, String firstName, String lastName, String direccion, String ciudad, String estado, String pais, String continente, String email, String gender, Date birthDay, String Phone_number, String rfc, String curp) {
        this.personaId = personaId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.estado = estado;
        this.pais = pais;
        this.continente = continente;
        this.email = email;
        this.gender = gender;
        this.birthDay = birthDay;
        this.Phone_number = Phone_number;
        this.rfc = rfc;
        this.curp = curp;
    }



    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String Phone_number) {
        this.Phone_number = Phone_number;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
    
    
    
}
