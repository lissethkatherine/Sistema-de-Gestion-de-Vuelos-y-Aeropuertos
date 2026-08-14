/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.models;

import java.util.ArrayList;
import java.util.List;
import com.mycompany.main.exceptions.VueloLlenoException;
/**
 *
 * @author camper
 */
public class Vuelo {

    //Encapsulamiento
    private String codigo;
    private Aeropuerto origen;
    private Aeropuerto destino;
    private int capacidad;
    private EstadoVuelo estado;
    private List<Pasajero> pasajeros;

    public Vuelo(String codigo, Aeropuerto origen, Aeropuerto destino, int capacidad) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.capacidad = capacidad;
        //Todo vuelo nuevo inicia en estado programado
        this.estado = EstadoVuelo.PROGRAMADO;
        this.pasajeros = new ArrayList<>();
    }

    //Getters
    public String getCodigo() {
        return codigo;
    }

    public Aeropuerto getOrigen() {
        return origen;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public EstadoVuelo getEstado() {
        return estado;
    }

    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    //Setter 
    public void setEstado(EstadoVuelo estado) {
        this.estado = estado;
    }

    //Agrega un pasajero al vuelo validando cupo disponible y lanza una excepción personalizada si el vuelo ya está lleno
    public void agregarPasajero(Pasajero pasajero) throws VueloLlenoException {
        if (pasajeros.size() >= capacidad) {
            throw new VueloLlenoException("No existen asientos disponibles.");
        }
        pasajeros.add(pasajero);
    }

    public boolean contienePasajero(String documento) {
        for (Pasajero p : pasajeros) {
            if (p.tieneDocumento(documento)) {
                return true;
            }
        }
        return false;
    }

    //Calcula el porcentaje de ocupación del vuelo
    public double calcularPorcentajeOcupacion() {
        if (capacidad == 0) {
            return 0.0;
        }
        return (pasajeros.size() * 100.0) / capacidad;
    }
}
