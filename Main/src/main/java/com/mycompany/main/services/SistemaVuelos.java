/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mycompany.main.exceptions.VueloLlenoException;
import com.mycompany.main.models.Aeropuerto;
import com.mycompany.main.models.EstadoVuelo;
import com.mycompany.main.models.Pasajero;
import com.mycompany.main.models.Persona;
import com.mycompany.main.models.Piloto;
import com.mycompany.main.models.Vuelo;
import com.mycompany.main.repositories.Repositorio;

/**
 *
 * @author camper
 */
public class SistemaVuelos {

    //Uso de la clase genérica Repositorio<T> para administrar entidades
    private Repositorio<Aeropuerto> repoAeropuertos = new Repositorio<>();
    private Repositorio<Pasajero> repoPasajeros = new Repositorio<>();

    //Lista de vuelos registrados
    private List<Vuelo> vuelos = new ArrayList<>();

    //Lista de pilotos para demostrar herencia, polimorfismo
    private List<Piloto> pilotos = new ArrayList<>();

    // olección polimórfica: puede contener Pasajeros y Pilotos: POLIMORFISMO
    private List<Persona> personas = new ArrayList<>();

    //Contador de reservas totales realizadas para estadísticas
    private int totalReservas = 0;

    //AEROPUERTOS

    public boolean existeAeropuerto(String codigo) {
        for (Aeropuerto a : repoAeropuertos.obtenerTodos()) {
            if (a.getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
        }
        return false;
    }

    public Aeropuerto buscarAeropuerto(String codigo) {
        for (Aeropuerto a : repoAeropuertos.obtenerTodos()) {
            if (a.getCodigo().equalsIgnoreCase(codigo)) {
                return a;
            }
        }
        return null;
    }

    public void registrarAeropuerto(String codigo, String nombre, String ciudad, String pais) {
        //No permitir dos aeropuertos con el mismo código
        if (existeAeropuerto(codigo)) {
            System.out.println("Ya existe un aeropuerto con ese código.");
            return;
        }
        Aeropuerto nuevo = new Aeropuerto(codigo, nombre, ciudad, pais);
        repoAeropuertos.agregar(nuevo);
        System.out.println("Aeropuerto registrado correctamente.");
    }

    //PASAJEROS
    public boolean existePasajero(String documento) {
        for (Pasajero p : repoPasajeros.obtenerTodos()) {
            if (p.tieneDocumento(documento)) {
                return true;
            }
        }
        return false;
    }

    public Pasajero buscarPasajero(String documento) {
        for (Pasajero p : repoPasajeros.obtenerTodos()) {
            if (p.tieneDocumento(documento)) {
                return p;
            }
        }
        return null;
    }

    public void registrarPasajero(String documento, String nombre, int edad) {
        if (existePasajero(documento)) {
            System.out.println("Ya existe un pasajero con ese documento.");
            return;
        }
        if (edad <= 0) {
            System.out.println("La edad debe ser mayor que 0.");
            return;
        }
        Pasajero nuevo = new Pasajero(documento, nombre, edad);
        repoPasajeros.agregar(nuevo);
        personas.add(nuevo); // también entra a la colección polimórfica
        System.out.println("Pasajero registrado correctamente.");
    }

    public void registrarPiloto(String documento, String nombre, String licencia, int horasVuelo) {
        Piloto piloto = new Piloto(documento, nombre, licencia, horasVuelo);
        pilotos.add(piloto);
        personas.add(piloto);
    }

    //VUELOS
    public Vuelo buscarVuelo(String codigo) {
        for (Vuelo v : vuelos) {
            if (v.getCodigo().equalsIgnoreCase(codigo)) {
                return v;
            }
        }
        return null;
    }

    public void registrarVuelo(String codigo, String codOrigen, String codDestino, int capacidad) {
        Aeropuerto origen = buscarAeropuerto(codOrigen);
        Aeropuerto destino = buscarAeropuerto(codDestino);

        //Validaciones
        if (origen == null) {
            System.out.println("El aeropuerto de origen no existe.");
            return;
        }
        if (destino == null) {
            System.out.println("El aeropuerto de destino no existe.");
            return;
        }
        if (codOrigen.equalsIgnoreCase(codDestino)) {
            System.out.println("El origen y el destino deben ser diferentes.");
            return;
        }
        if (buscarVuelo(codigo) != null) {
            System.out.println("Ya existe un vuelo con ese código.");
            return;
        }
        if (capacidad <= 0) {
            System.out.println("La capacidad debe ser mayor que 0.");
            return;
        }

        Vuelo nuevo = new Vuelo(codigo, origen, destino, capacidad);
        vuelos.add(nuevo);
        System.out.println("Vuelo registrado correctamente.");
    }

    public void listarVuelos() {
        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos registrados.");
            return;
        }
        for (Vuelo v : vuelos) {
            System.out.println("--------------------------------");
            System.out.println("Vuelo: " + v.getCodigo());
            System.out.println("Origen: " + v.getOrigen().getCiudad());
            System.out.println("Destino: " + v.getDestino().getCiudad());
            System.out.println("Capacidad: " + v.getCapacidad());
            System.out.println("Pasajeros: " + v.getPasajeros().size());
            System.out.println("Estado: " + v.getEstado());
        }
        System.out.println("--------------------------------");
    }

    public void buscarPorDestino(String ciudad) {
        //Búsqueda usando toLowerCase(), contains() y trim()
        String busqueda = ciudad.trim().toLowerCase();
        boolean encontrado = false;

        System.out.println("Resultados encontrados:");
        for (Vuelo v : vuelos) {
            String destinoCiudad = v.getDestino().getCiudad().trim().toLowerCase();
            if (destinoCiudad.contains(busqueda)) {
                encontrado = true;
                System.out.println(v.getCodigo());
                System.out.println(v.getOrigen().getCiudad() + " -> " + v.getDestino().getCiudad());
                System.out.println("Estado: " + v.getEstado());
                System.out.println("--------------------------------");
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron vuelos hacia esa ciudad.");
        }
    }

    //RESERVAS
    public void reservarVuelo(String documento, String codigoVuelo) throws VueloLlenoException {
        Pasajero pasajero = buscarPasajero(documento);
        if (pasajero == null) {
            System.out.println("El pasajero no existe.");
            return;
        }

        Vuelo vuelo = buscarVuelo(codigoVuelo);
        if (vuelo == null) {
            System.out.println("El vuelo no existe.");
            return;
        }

        if (vuelo.getEstado() != EstadoVuelo.PROGRAMADO) {
            System.out.println("El vuelo no está en estado PROGRAMADO, no se puede reservar.");
            return;
        }

        if (vuelo.contienePasajero(documento)) {
            System.out.println("El pasajero ya está registrado en este vuelo.");
            return;
        }

        //Si no hay cupo, este método lanza VueloLlenoException
        vuelo.agregarPasajero(pasajero);
        totalReservas++;
        System.out.println("Reserva realizada correctamente.");
    }

    public void verPasajerosDeVuelo(String codigoVuelo) {
        Vuelo vuelo = buscarVuelo(codigoVuelo);
        if (vuelo == null) {
            System.out.println("El vuelo no existe.");
            return;
        }
        List<Pasajero> lista = vuelo.getPasajeros();
        if (lista.isEmpty()) {
            System.out.println("Este vuelo aún no tiene pasajeros.");
            return;
        }
        System.out.println("Pasajeros del vuelo " + vuelo.getCodigo() + ":");
        for (Pasajero p : lista) {
            System.out.println("- " + p.getDocumento() + " | " + p.getNombre() + " | " + p.getEdad() + " años");
        }
    }

    //CAMBIO DE ESTADO
    public void cambiarEstadoVuelo(String codigoVuelo, EstadoVuelo nuevoEstado) {
        Vuelo vuelo = buscarVuelo(codigoVuelo);
        if (vuelo == null) {
            System.out.println("El vuelo no existe.");
            return;
        }
        vuelo.setEstado(nuevoEstado);
        System.out.println("Estado actualizado correctamente a " + nuevoEstado + ".");
    }

    //SET Y MAP
    
    //Devuelve el conjunto de ciudades destino sin repetir uso de Set
    public Set<String> obtenerCiudadesDestino() {
        Set<String> ciudadesDestino = new HashSet<>();
        for (Vuelo v : vuelos) {
            ciudadesDestino.add(v.getDestino().getCiudad());
        }
        return ciudadesDestino;
    }

    //Devuelve cuántos vuelos existen por cada ciudad destino con uso de Map
    public Map<String, Integer> obtenerVuelosPorDestino() {
        Map<String, Integer> vuelosPorDestino = new HashMap<>();
        for (Vuelo v : vuelos) {
            String ciudad = v.getDestino().getCiudad();
            //Si la ciudad ya existe en el mapa, se incrementa si no se inicializa en 1
            if (vuelosPorDestino.containsKey(ciudad)) {
                vuelosPorDestino.put(ciudad, vuelosPorDestino.get(ciudad) + 1);
            } else {
                vuelosPorDestino.put(ciudad, 1);
            }
        }
        return vuelosPorDestino;
    }

    public void verEstadisticas() {
        int programados = 0, abordando = 0, enVuelo = 0, finalizados = 0, cancelados = 0;

        for (Vuelo v : vuelos) {
            switch (v.getEstado()) {
                case PROGRAMADO:
                    programados++;
                    break;
                case ABORDANDO:
                    abordando++;
                    break;
                case EN_VUELO:
                    enVuelo++;
                    break;
                case FINALIZADO:
                    finalizados++;
                    break;
                case CANCELADO:
                    cancelados++;
                    break;
            }
        }

        //Busca el vuelo con mayor número de pasajeros
        Vuelo vueloMasOcupado = null;
        for (Vuelo v : vuelos) {
            if (vueloMasOcupado == null || v.getPasajeros().size() > vueloMasOcupado.getPasajeros().size()) {
                vueloMasOcupado = v;
            }
        }

        System.out.println("======= ESTADÍSTICAS ======");
        System.out.println("Aeropuertos registrados: " + repoAeropuertos.cantidad());
        System.out.println("Vuelos registrados: " + vuelos.size());
        System.out.println("Pasajeros registrados: " + repoPasajeros.cantidad());
        System.out.println("Vuelos programados: " + programados);
        System.out.println("Vuelos abordando: " + abordando);
        System.out.println("Vuelos en vuelo: " + enVuelo);
        System.out.println("Vuelos finalizados: " + finalizados);
        System.out.println("Vuelos cancelados: " + cancelados);
        System.out.println("Total reservas realizadas: " + totalReservas);

        if (vueloMasOcupado != null) {
            System.out.println("Vuelo con mayor ocupación:");
            System.out.println(vueloMasOcupado.getCodigo() + " - " + vueloMasOcupado.getOrigen().getCiudad()
                    + " -> " + vueloMasOcupado.getDestino().getCiudad());
            System.out.println(vueloMasOcupado.getPasajeros().size() + " / " + vueloMasOcupado.getCapacidad() + " pasajeros");
        }

        System.out.println("Destinos disponibles:");
        for (String ciudad : obtenerCiudadesDestino()) {
            System.out.println(ciudad);
        }

        //Polimorfismo para recorrer la lista de Persona
        System.out.println("Tripulación y pasajeros registrados:");
        for (Persona persona : personas) {
            System.out.println(persona.obtenerDescripcion());
        }
        System.out.println("=================================");
    }

    //consulta rutas disponibles entre dos ciudades
    public void consultarRutas(String ciudadOrigen, String ciudadDestino) {
        String origenBuscado = ciudadOrigen.trim().toLowerCase();
        String destinoBuscado = ciudadDestino.trim().toLowerCase();
        boolean encontrado = false;

        System.out.println("Vuelos " + ciudadOrigen + " -> " + ciudadDestino);
        for (Vuelo v : vuelos) {
            String ciudadOrigenVuelo = v.getOrigen().getCiudad().trim().toLowerCase();
            String ciudadDestinoVuelo = v.getDestino().getCiudad().trim().toLowerCase();
            if (ciudadOrigenVuelo.contains(origenBuscado) && ciudadDestinoVuelo.contains(destinoBuscado)) {
                encontrado = true;
                int disponibles = v.getCapacidad() - v.getPasajeros().size();
                System.out.println(v.getCodigo());
                System.out.println("Estado: " + v.getEstado());
                System.out.println("Disponibles: " + disponibles + " asientos");
                System.out.println("--------------");
            }
        }
        if (!encontrado) {
            System.out.println("No hay vuelos disponibles para esa ruta.");
        }
    }

    public void verOcupacionPorVuelo() {
        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos registrados.");
            return;
        }
        for (Vuelo v : vuelos) {
            double porcentaje = v.calcularPorcentajeOcupacion();
            System.out.printf("%s | %s -> %s%n", v.getCodigo(), v.getOrigen().getCiudad(), v.getDestino().getCiudad());
            System.out.println("Ocupación: " + v.getPasajeros().size() + " / " + v.getCapacidad());
            System.out.printf("Porcentaje: %.1f%%%n", porcentaje);
            System.out.println("------------");
        }
    }

    //Datos de ejemplos
    public void cargarDatosIniciales() {
        // Aeropuertos
        registrarAeropuerto("BOG", "El Dorado", "Bogotá", "Colombia");
        registrarAeropuerto("MDE", "José María Córdova", "Medellín", "Colombia");
        registrarAeropuerto("CLO", "Alfonso Bonilla Aragón", "Cali", "Colombia");
        registrarAeropuerto("CTG", "Rafael Núñez", "Cartagena", "Colombia");
        registrarAeropuerto("PSO", "Antonio Nariño", "Pasto", "Colombia");
        // Vuelos
        registrarVuelo("AV101", "BOG", "MDE", 180);
        registrarVuelo("AV202", "BOG", "CTG", 150);
        registrarVuelo("AV303", "MDE", "CLO", 120);
        registrarVuelo("AV404", "CLO", "BOG", 160);
        registrarVuelo("AV505", "BOG", "PSO", 90);
        // Pasajeros
        registrarPasajero("1001", "Laura Gómez", 25);
        registrarPasajero("1002", "Andrés Martínez", 32);
        registrarPasajero("1003", "Carolina Díaz", 28);
        registrarPasajero("1004", "Daniel Torres", 41);

        // Pilotos (para demostrar herencia y polimorfismo, sección 6)
        registrarPiloto("P001", "Carlos Rodríguez", "LIC-5521", 2500);
        registrarPiloto("P002", "Marta Suárez", "LIC-7788", 4100);
        // Algunas reservas de ejemplo (silenciosas, ignorando mensajes)
        try {
            reservarVuelo("1001", "AV101");
            reservarVuelo("1002", "AV101");
            reservarVuelo("1003", "AV202");
        } catch (VueloLlenoException e) {
            System.out.println(e.getMessage());
        }
    }
}
