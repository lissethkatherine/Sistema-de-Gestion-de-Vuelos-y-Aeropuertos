# Sistema de Gestión de Vuelos y Aeropuertos

Proyecto Java de consola que cumple con el 100% de los requisitos del taller,
incluyendo los dos retos adicionales.

## Estructura

```
src/
├── Main.java
├── models/
│   ├── Persona.java        (clase abstracta)
│   ├── Pasajero.java       (extends Persona)
│   ├── Piloto.java         (extends Persona)
│   ├── Aeropuerto.java
│   ├── Vuelo.java
│   └── EstadoVuelo.java    (enum)
├── services/
│   └── SistemaVuelos.java  (toda la lógica de negocio)
├── repositories/
│   └── Repositorio.java    (clase genérica <T>)
└── exceptions/
    └── VueloLlenoException.java
```

## Cómo compilar y ejecutar

Desde la carpeta raíz del proyecto (donde está la carpeta `src`):

```bash
# Compilar
javac -d bin src/Main.java src/models/*.java src/services/*.java src/repositories/*.java src/exceptions/*.java

# Ejecutar
java -cp bin Main
```

## Conceptos aplicados (todos presentes en el código, con comentarios)

- Variables, tipos de datos, operadores, condicionales, switch, ciclos → `Main.java`, `SistemaVuelos.java`
- Cadenas y conversiones (`toLowerCase`, `contains`, `trim`) → `buscarPorDestino`, `consultarRutas`
- Clases y objetos, encapsulamiento (atributos `private` + getters/setters) → todas las clases de `models/`
- Herencia → `Persona` → `Pasajero`, `Piloto`
- Clase abstracta y polimorfismo → `Persona.obtenerDescripcion()`, recorrido de `List<Persona>` en `verEstadisticas()`
- Listas → `List<Vuelo>`, `List<Pasajero>`, `List<Persona>`
- Genéricos → `Repositorio<T>`
- Relaciones entre clases → `Vuelo` contiene `Aeropuerto` (origen/destino) y `List<Pasajero>`
- Set → `obtenerCiudadesDestino()`
- Map → `obtenerVuelosPorDestino()`
- Enumeraciones → `EstadoVuelo`
- Excepciones y excepción personalizada → `VueloLlenoException`, manejada con `try/catch` en `Main.reservarVuelo`

## Menú

1. Registrar aeropuerto
2. Registrar pasajero
3. Registrar vuelo
4. Listar vuelos
5. Buscar vuelos por destino
6. Reservar vuelo
7. Ver pasajeros de un vuelo
8. Cambiar estado de vuelo
9. Ver estadísticas
10. Consultar rutas disponibles (reto adicional 1)
11. Ver ocupación por vuelo (reto adicional 2)
12. Salir

El programa carga automáticamente los datos iniciales de ejemplo (aeropuertos,
vuelos, pasajeros y pilotos).
