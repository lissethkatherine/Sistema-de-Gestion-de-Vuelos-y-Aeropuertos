/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

import com.mycompany.main.exceptions.VueloLlenoException;
import com.mycompany.main.services.SistemaVuelos;
import com.mycompany.main.models.EstadoVuelo;
import java.util.Scanner;

/**
 *
 * @author camper
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaVuelos sistema = new SistemaVuelos();

        //Carga de datos de ejemplo al iniciar el programa
        sistema.cargarDatosIniciales();

        int opcion = 0;

        do {
            System.out.println();
            System.out.println("==================================");
            System.out.println("SISTEMA AEROPUERTO JAVA");
            System.out.println("==================================");
            System.out.println("1. Registrar aeropuerto");
            System.out.println("2. Registrar pasajero");
            System.out.println("3. Registrar vuelo");
            System.out.println("4. Listar vuelos");
            System.out.println("5. Buscar vuelos por destino");
            System.out.println("6. Reservar vuelo");
            System.out.println("7. Ver pasajeros de un vuelo");
            System.out.println("8. Cambiar estado de vuelo");
            System.out.println("9. Ver estadísticas");
            System.out.println("10. Consultar rutas disponibles (reto adicional)");
            System.out.println("11. Ver ocupación por vuelo (reto adicional)");
            System.out.println("12. Salir");
            System.out.print("Seleccione una opción: ");

            //Validación básica de que se ingresó un número
            if (!scanner.hasNextInt()) {
                System.out.println("Debe ingresar un número de opción válido.");
                scanner.next(); // descarta la entrada inválida
                continue;
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); //limpia el salto de línea pendiente

            switch (opcion) {
                case 1:
                    registrarAeropuerto(scanner, sistema);
                    break;
                case 2:
                    registrarPasajero(scanner, sistema);
                    break;
                case 3:
                    registrarVuelo(scanner, sistema);
                    break;
                case 4:
                    sistema.listarVuelos();
                    break;
                case 5:
                    buscarPorDestino(scanner, sistema);
                    break;
                case 6:
                    reservarVuelo(scanner, sistema);
                    break;
                case 7:
                    verPasajerosDeVuelo(scanner, sistema);
                    break;
                case 8:
                    cambiarEstadoVuelo(scanner, sistema);
                    break;
                case 9:
                    sistema.verEstadisticas();
                    break;
                case 10:
                    consultarRutas(scanner, sistema);
                    break;
                case 11:
                    sistema.verOcupacionPorVuelo();
                    break;
                case 12:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
                    break;
            }

        } while (opcion != 12);

        scanner.close();
    }

    private static void registrarAeropuerto(Scanner scanner, SistemaVuelos sistema) {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("País: ");
        String pais = scanner.nextLine();

        sistema.registrarAeropuerto(codigo, nombre, ciudad, pais);
    }

    private static void registrarPasajero(Scanner scanner, SistemaVuelos sistema) {
        System.out.print("Documento: ");
        String documento = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");

        //Valida de que la edad ingresada sea un número entero
        if (!scanner.hasNextInt()) {
            System.out.println("Edad inválida.");
            scanner.nextLine();
            return;
        }
        int edad = scanner.nextInt();
        scanner.nextLine();

        sistema.registrarPasajero(documento, nombre, edad);
    }

    private static void registrarVuelo(Scanner scanner, SistemaVuelos sistema) {
        System.out.print("Código del vuelo: ");
        String codigo = scanner.nextLine();
        System.out.print("Código aeropuerto origen: ");
        String origen = scanner.nextLine();
        System.out.print("Código aeropuerto destino: ");
        String destino = scanner.nextLine();
        System.out.print("Capacidad máxima: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Capacidad inválida.");
            scanner.nextLine();
            return;
        }
        int capacidad = scanner.nextInt();
        scanner.nextLine();

        sistema.registrarVuelo(codigo, origen, destino, capacidad);
    }

    private static void buscarPorDestino(Scanner scanner, SistemaVuelos sistema) {
        System.out.print("Ingrese destino: ");
        String ciudad = scanner.nextLine();
        sistema.buscarPorDestino(ciudad);
    }

    private static void reservarVuelo(Scanner scanner, SistemaVuelos sistema) {
        System.out.print("Documento del pasajero: ");
        String documento = scanner.nextLine();
        System.out.print("Código del vuelo: ");
        String codigoVuelo = scanner.nextLine();

        //Excepción personalizada VueloLlenoException
        try {
            sistema.reservarVuelo(documento, codigoVuelo);
        } catch (VueloLlenoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void verPasajerosDeVuelo(Scanner scanner, SistemaVuelos sistema) {
        System.out.print("Código del vuelo: ");
        String codigoVuelo = scanner.nextLine();
        sistema.verPasajerosDeVuelo(codigoVuelo);
    }

    private static void cambiarEstadoVuelo(Scanner scanner, SistemaVuelos sistema) {
        System.out.print("Código del vuelo: ");
        String codigoVuelo = scanner.nextLine();

        System.out.println("Nuevo estado:");
        System.out.println("1. PROGRAMADO");
        System.out.println("2. ABORDANDO");
        System.out.println("3. EN_VUELO");
        System.out.println("4. FINALIZADO");
        System.out.println("5. CANCELADO");
        System.out.print("Opción: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Opción inválida.");
            scanner.nextLine();
            return;
        }
        int opcionEstado = scanner.nextInt();
        scanner.nextLine();

        EstadoVuelo nuevoEstado;

        //if,else y suich ;(
        if (opcionEstado == 1) {
            nuevoEstado = EstadoVuelo.PROGRAMADO;
        } else if (opcionEstado == 2) {
            nuevoEstado = EstadoVuelo.ABORDANDO;
        } else if (opcionEstado == 3) {
            nuevoEstado = EstadoVuelo.EN_VUELO;
        } else if (opcionEstado == 4) {
            nuevoEstado = EstadoVuelo.FINALIZADO;
        } else if (opcionEstado == 5) {
            nuevoEstado = EstadoVuelo.CANCELADO;
        } else {
            System.out.println("Opción de estado no válida.");
            return;
        }

        sistema.cambiarEstadoVuelo(codigoVuelo, nuevoEstado);
    }

    private static void consultarRutas(Scanner scanner, SistemaVuelos sistema) {
        System.out.print("Ciudad origen: ");
        String origen = scanner.nextLine();
        System.out.print("Ciudad destino: ");
        String destino = scanner.nextLine();
        sistema.consultarRutas(origen, destino);
    }
}