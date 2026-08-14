/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.models;

/**
 *
 * @author camper
 */
public abstract class Persona {

    //Atributos encapsulados
    private String documento;
    private String nombre;

    public Persona(String documento, String nombre) {
        this.documento = documento;
        this.nombre = nombre;
    }

    //Getters (encapsulamiento)
    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    //Método abstracto
    public abstract String obtenerDescripcion();
}
