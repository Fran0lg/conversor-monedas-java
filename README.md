<h1>Conversor de Monedas Java CLI</h1>
  Este es un conversor de monedas simple basado en la línea de comandos (CLI) desarrollado en Java. Utiliza la API de [ExchangeRate-API](https://www.exchangerate-api.com/) para obtener tasas de cambio actualizadas en tiempo real, permitiendo a los usuarios realizar conversiones predefinidas y guardar un historial de transacciones.

## Características

* **Tipos de Cambio en Tiempo Real:** Las tasas son consultadas a través de una API externa.

* **Conversiones Predefinidas:** Opciones rápidas para conversiones comunes (USD a PYG, ARS a USD, etc.).

* **Historial de Transacciones:** Almacena y muestra las conversiones realizadas durante la sesión.

* **Manejo de Errores:** Incluye gestión de errores para problemas de conexión o respuestas inválidas del API.

## Requisitos

Para compilar y ejecutar este proyecto, necesitas tener instalado:

* **Java Development Kit (JDK) 17 o superior.**

* **Maven o Gradle** (Si usas un IDE como IntelliJ o Eclipse, generalmente manejan la compilación y dependencias automáticamente).

### Dependencias

El proyecto utiliza la librería `Gson` para la deserialización de objetos JSON:

* **Google Gson:** Para convertir la respuesta JSON del API a un objeto Java (`Moneda` record).

## Estructura del Proyecto

El conversor se compone de tres clases principales:

| **Archivo** | **Descripción** | 
| :--- | :--- |
| `Principal.java` | Contiene el método `main()`, la lógica del menú, la interacción con el usuario y el historial. | 
| `ConsultarCambio.java` | Maneja la conexión con el API (`ExchangeRate-API`) y la solicitud de conversión. | 
| `Moneda.java` | Es un Java Record que modela la estructura de la respuesta JSON del API (`base_code`, `conversion_rate`, etc.). | 

## Cómo Ejecutar

### 1. Clonar el Repositorio

```
git clone [REEMPLAZA ESTO CON LA URL DE TU REPOSITORIO]
cd [nombre-del-repositorio]

```

### 2. Compilar y Ejecutar (Usando IDE o Línea de Comandos)

Si estás usando un entorno de desarrollo integrado (IDE), simplemente abre el proyecto y ejecuta la clase `Principal.java`.

Si utilizas la línea de comandos, necesitarás incluir la dependencia `Gson` en el classpath al compilar y ejecutar:

*(Nota: Los comandos exactos pueden variar según tu sistema de compilación. Este es un ejemplo básico.)*

```
# Ejemplo de compilación (si no usas Maven/Gradle y tienes los JARs localmente)
javac -cp "ruta/a/gson-2.10.1.jar" Moneda.java ConsultarCambio.java Principal.java

# Ejemplo de ejecución
java -cp ".:ruta/a/gson-2.10.1.jar" Principal

```

## Uso del Conversor

Al iniciar la aplicación, se mostrará un menú:

```
--- Conversor de Monedas ---
===============================================
1. USD a PYG
2. USD a ARS
...
7. Salir
8. Historial de conversiones
===============================================
Ingrese una opción: 


```

1. Selecciona una opción del **1 al 6**.

2. Ingresa el **monto** a convertir.

3. El sistema mostrará el resultado y la conversión se guardará en el historial.

4. Selecciona la opción **8** para revisar todas las transacciones realizadas.
