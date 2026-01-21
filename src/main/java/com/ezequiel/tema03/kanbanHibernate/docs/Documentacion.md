# 📋 Guía de Diseño: Proyecto Kanban Hibernate

## 1. Resumen Técnico

- **Tipo:** Aplicación Java de consola.
- **ORM:** Hibernate (JPA).
- **BD:** PostgreSQL.
- **Objetivo:** Gestión de tareas mediante tableros Kanban.

---

## 2. Seguridad (Hashing de Contraseñas)

El sistema no guarda contraseñas reales, sino un "hash" para proteger al usuario.

- **Algoritmo:** PBKDF2WithHmacSHA256.
- **Iteraciones:** 100,000.
- **Sal (Salt):** 16 bytes aleatorios generados con `SecureRandom`.
- **Formato en BD:** `algoritmo:iteraciones:saltBase64:hashBase64`.

---

## 3. Arquitectura de Datos (Entidades)

- **Usuario:** Posee un email único y su contraseña cifrada.
- **Tablero:** Pertenece a un usuario. Al crearse, genera automáticamente las columnas **TODO, DOING y DONE**.
- **Columna:** Pertenece a un tablero. Contiene las tarjetas.
- **Tarjeta:** Tiene título y descripción. Se puede mover entre columnas del mismo tablero.
- **Etiqueta:** Son globales. Una tarjeta puede tener varias etiquetas.

---

## 4. Reglas Críticas (Para no perder puntos)

1. **Validación:** No permitir registros con el mismo email.
2. **Restricción de Movimiento:** No puedes mover una tarjeta a una columna de otro tablero distinto.
3. **Borrado en Cascada:** Si borras un tablero, Hibernate debe borrar sus columnas y tarjetas automáticamente.
4. **Privacidad:** Al filtrar por etiqueta, el usuario solo debe ver sus propias tarjetas.

---

## 5. Estructura de Menús

- **Acceso:** Login / Registro / Salir.
- **Principal:** Gestión de Tableros, Columnas, Tarjetas y Etiquetas.
