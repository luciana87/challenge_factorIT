
# challenge_factorIT
Simulación de una aplicación e-commerce que utiliza un carrito de compras implementando diferentes funcionalidades.

## Requisitos del Sistema

- **Java**: 21
- **Spring Boot**: 3.4.4
- **Spring Framework**: 6.1.5
- **MySQL**: 8.0.33
- **JPA / Hibernate**: Usado para la persistencia de datos

### Frontend
- **Angular**: 16
- **Node.js**: 20.11.0
- **Bootstrap**

## Descripción del Proyecto

Este proyecto utiliza **Spring Boot 3.4.4** con **Spring Framework 6.1.5**, integrando tecnologías como **JPA / Hibernate** 
para la persistencia de datos, y **Angular 16** para el frontend. 

Se exponen **servicios REST** y se provee documentación interactiva utilizando **Swagger**. 
También se incluyen **unit tests** con **JUnit** y **Mockito** para asegurar la calidad del código, así como **scripts SQL** 
para la gestión de bases de datos en **MySQL 8.0.33**.

## Funcionalidades

- Exposición de servicios RESTful.
- Documentación de APIs con **Swagger**.
- Ejecución de pruebas unitarias con **JUnit** y **Mockito**.
- Uso de **JPA / Hibernate** para la persistencia de datos en **MySQL**.
- Scripts SQL para la configuración inicial de la base de datos.

## Dependencias

- **Maven**: Para la gestión de dependencias y construcción del proyecto.

---

### 🔐 Seguridad

Este proyecto utiliza **JWT (JSON Web Tokens)** para manejar la autenticación y autorización de los usuarios.

- Los usuarios deben autenticarse para obtener un token válido.
- El token debe ser enviado en el header `Authorization` con el prefijo `Bearer` en cada solicitud protegida.
- Los roles permiten gestionar permisos como **ADMIN ó VIP y COMMON**.

---

### 🧪 Diagrama de entidades de relación

![DEER proyecto](Diagrama_EER_ecommerce.png)


### 🧪 Scripts de Datos

A continuación se indican las instrucciones para ejecutar los scripts SQL de carga inicial de datos. Estos scripts permiten poblar la base con usuarios, productos y fechas promocionales.

#### 📌 Pasos para ejecutar los scripts

1. **Crear un base de datos MySQL**:
   ```sql
   CREATE DATABASE IF NOT EXISTS ecommerce;
   ```

2. **Seleccionar la base de datos correspondiente**:
   ```sql
   USE ecommerce;
   ```

3. **Ejecutar el endpoint  `http://localhost:8080/user/create`**:

   Se insertaran usuarios a la base de datos.

- **Usuarios ADMIN:**  
  **username:** admin1  
  **password:** admin1  

  Usuario de prueba security:  
  **username:** admin123  
  **password:** admin123  

- **Usuarios VIP:**  
  **username:** uservip  
  **password:** uservip  

  **username:** uservip1  
  **password:** uservip1  

- **Usuarios COMMON:**  
  **username:** user1  
  **password:** user1  

  **username:** user2  
  **password:** user2  

  **username:** user3  
  **password:** user3  


4. **Ejecutar el script llamado `scrpt_challenge.sql`**:

   - `1. Inserta productos`
   - `2. Inserta fechas especiales`
   - `3. Inserta usuarios VIP`

---
