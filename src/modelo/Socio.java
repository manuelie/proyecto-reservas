/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
public class Socio {

    private String nombre;
    private String idSocio;

    public Socio(String nombre, String idSocio) {
        this.nombre = nombre;
        this.idSocio = idSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdSocio() {
        return idSocio;
    }
}

