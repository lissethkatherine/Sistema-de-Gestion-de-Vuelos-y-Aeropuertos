/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.models;

import com.mycompany.main.models.Persona;

/**
 *
 * @author camper
 */
public class Pasajero extends Persona {

    //Atributo encapsulado
    private int edad;

    public Pasajero(String documento, String nombre, int edad) {
        //Llama al constructor de la clase padre persona
        super(documento, nombre);
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    //polimorfismo
    @Override
    public String obtenerDescripcion() {
        return "Pasajero: " + getNombre();
    }

    //Compara dos pasajeros por su documento,
    public boolean tieneDocumento(String documento) {
        return this.getDocumento().equalsIgnoreCase(documento);
    }
}