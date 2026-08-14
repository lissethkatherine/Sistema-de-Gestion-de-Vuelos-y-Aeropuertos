/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.models;

/**
 *
 * @author camper
 */
public class Piloto extends Persona {

    //Atributos encapsulados
    private String licencia;
    private int horasVuelo;

    public Piloto(String documento, String nombre, String licencia, int horasVuelo) {
        super(documento, nombre);
        this.licencia = licencia;
        this.horasVuelo = horasVuelo;
    }

    public String getLicencia() {
        return licencia;
    }

    public int getHorasVuelo() {
        return horasVuelo;
    }

    //Polimorfismo
    @Override
    public String obtenerDescripcion() {
        return "Piloto: " + getNombre() + " - " + horasVuelo + " horas de vuelo";
    }
}
