-- SCRIPT DE DATOS DE PRUEBA
-- Ejecutar despues de que Hibernate haya creado las tablas (update/create)

-- 1. Insertar Usuario 'admin' (Pass: 'admin' hasheada con BCrypt)
-- Hash generado de 'admin': $2a$10$R/h.j.ExampleHash... (Esto es un ejemplo, usar el registro de la app es lo ideal)
-- Para este script usaremos un insert directo asumiendo que el ID es serial.

INSERT INTO usuarios (email, passwd) 
VALUES ('admin@admin.com', '$2a$10$e0MYzXyjpJS7Pd0RVvH3.O0.K.x.T.EXAMPLE.HASH.CHANGE.ME');
-- NOTA: Como BCrypt usa salt aleatorio, insertar un hash "a mano" puede no coincidir con el generated por tu app si la version difiere.
-- LO RECOMENDABLE: Registrar el usuario desde la app Java. Este script sirve de estructura de ejemplo.

-- 2. Insertar Tablero
INSERT INTO tableros (nombre, usuario_id) 
VALUES ('Proyecto Final', 1);

-- 3. Insertar Columnas (TODO, DOING, DONE)
INSERT INTO columnas (titulo, tablero_id) VALUES ('TODO', 1);
INSERT INTO columnas (titulo, tablero_id) VALUES ('DOING', 1);
INSERT INTO columnas (titulo, tablero_id) VALUES ('DONE', 1);

-- 4. Insertar Tarjetas
-- Asumiendo que TODO es ID 1, DOING ID 2... (depende del orden de insercion)
INSERT INTO tarjetas (titulo, descripcion, columna_id) 
VALUES ('Hacer el Login', 'Implementar JBCrypt', 1);

INSERT INTO tarjetas (titulo, descripcion, columna_id) 
VALUES ('Diseñar BD', 'Modelo Entidad Relacion', 2);

-- 5. Insertar Etiquetas
INSERT INTO etiquetas (titulo) VALUES ('Urgente');
INSERT INTO etiquetas (titulo) VALUES ('Backend');

-- 6. Relacionar tarjeta 1 con etiqueta 1
INSERT INTO tarjetas_etiquetas (tarjeta_id, etiqueta_id) VALUES (1, 1);
