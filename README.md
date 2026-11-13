# ✈️ Sistema de Gestión de Vuelos y Aeropuertos

> Aplicación desarrollada en Java para la administración integral de aeropuertos, vuelos, pasajeros, pilotos y la lógica de control asociada.

---

## 📂 Estructura del Proyecto

El proyecto está organizado bajo una arquitectura modular limpia orientada a objetos, separando las entidades, la lógica de negocio, el almacenamiento y las excepciones personalizadas:

```text
Main/
├── pom.xml
└── src
    └── main
        └── java
            └── com
                └── mycompany
                    └── main
                        ├── Main.java
                        ├── exceptions
                        │   └── VueloLlenoException.java
                        ├── models
                        │   ├── Aeropuerto.java
                        │   ├── EstadoVuelo.java
                        │   ├── Pasajero.java
                        │   ├── Persona.java
                        │   ├── Piloto.java
                        │   └── Vuelo.java
                        ├── repositories
                        │   └── Repositorio.java
                        └── services
                            └── SistemaVuelos.java
```

---

## 🧱 Componentes Principales

### 1. Modelos (`models/`)
* **`Persona`**: Clase base que define los atributos generales (identificación, nombre, etc.) para las personas involucradas en el sistema.
* **`Pasajero`**: Extiende de `Persona` y gestiona los datos específicos de los viajeros y sus reservas de vuelos.
* **`Piloto`**: Extiende de `Persona` e incluye la información y credenciales de los comandantes de vuelo.
* **`Aeropuerto`**: Modela las instalaciones aeroportuarias, sus ubicaciones y la gestión de operaciones de salida/llegada.
* **`Vuelo`**: Administra la información del trayecto, origen, destino, capacidad máxima, asignación de pilotos y pasajeros a bordo.
* **`EstadoVuelo`**: `Enum` que representa los posibles estados operativos de un vuelo (ej. *Programado*, *En Vuelo*, *Cancelado*, *Aterrizado*).

### 2. Lógica y Excepciones (`exceptions/` y `services/`)
* **`VueloLlenoException`**: Excepción personalizada lanzada cuando se intenta registrar un pasajero en un vuelo que ha alcanzado su capacidad máxima de asientos.
* **`SistemaVuelos`**: Capa de servicios que concentra las reglas de negocio principales (creación de vuelos, asignación de pilotos, reservas de pasajeros y control de estados).

### 3. Repositorios (`repositories/`)
* **`Repositorio`**: Gestiona el almacenamiento temporal o en memoria de las entidades del sistema (vuelos, pasajeros, aeropuertos y pilotos).

---

## ⚙️ Requisitos del Sistema

* **Java JDK**: Versión 11 o superior recomendada.
* **Apache Maven**: Para la gestión de dependencias y la compilación del proyecto.

---

## 🚀 Instalación y Ejecución

1. **Clonar o descargar** el repositorio en tu máquina local.
2. **Abrir una terminal** en la carpeta raíz del proyecto (`Main/`).
3. **Compilar el proyecto** usando Maven:
   ```bash
   mvn clean compile
   ```
4. **Ejecutar la aplicación**:
   Puedes ejecutar la clase principal `Main.java` desde tu entorno de desarrollo favorito (IntelliJ IDEA, Eclipse, NetBeans) o mediante la terminal utilizando Maven (si está configurado con un plugin de ejecución) o ejecutando el archivo `.class` compilado.
