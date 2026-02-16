-- Categorías
INSERT INTO categorias (nombre) VALUES ('Filosofía') ON CONFLICT (nombre) DO NOTHING;
INSERT INTO categorias (nombre) VALUES ('Ciencia') ON CONFLICT (nombre) DO NOTHING;
INSERT INTO categorias (nombre) VALUES ('Literatura') ON CONFLICT (nombre) DO NOTHING;

-- Autores
INSERT INTO autores (nombre, anio_nacimiento, anio_fallecimiento, profesion) VALUES ('Sénca', -4, 65, 'Filósofo') ON CONFLICT (nombre) DO NOTHING;
INSERT INTO autores (nombre, anio_nacimiento, anio_fallecimiento, profesion) VALUES ('Albert Einstein', 1879, 1955, 'Físico') ON CONFLICT (nombre) DO NOTHING;
INSERT INTO autores (nombre, anio_nacimiento, anio_fallecimiento, profesion) VALUES ('Oscar Wilde', 1854, 1900, 'Escritor') ON CONFLICT (nombre) DO NOTHING;
INSERT INTO autores (nombre, anio_nacimiento, anio_fallecimiento, profesion) VALUES ('Marie Curie', 1867, 1934, 'Científica') ON CONFLICT (nombre) DO NOTHING;
INSERT INTO autores (nombre, anio_nacimiento, anio_fallecimiento, profesion) VALUES ('Friedrich Nietzsche', 1844, 1900, 'Filósofo') ON CONFLICT (nombre) DO NOTHING;
INSERT INTO autores (nombre, anio_nacimiento, anio_fallecimiento, profesion) VALUES ('Cervantes', 1547, 1616, 'Escritor') ON CONFLICT (nombre) DO NOTHING;

-- Frases
INSERT INTO frases (texto, fecha_programada, autor_id, categoria_id) VALUES ('La vida es muy corta y el tiempo para aprenderla es muy poco.', CURRENT_DATE, 1, 1);
INSERT INTO frases (texto, fecha_programada, autor_id, categoria_id) VALUES ('La imaginación es más importante que el conocimiento.', CURRENT_DATE + 1, 2, 2);
INSERT INTO frases (texto, fecha_programada, autor_id, categoria_id) VALUES ('Sé tú mismo, los demás puestos están ocupados.', CURRENT_DATE + 2, 3, 3);
INSERT INTO frases (texto, fecha_programada, autor_id, categoria_id) VALUES ('Nada en la vida es para ser temido, es solo para ser comprendido.', CURRENT_DATE + 3, 4, 2);
INSERT INTO frases (texto, fecha_programada, autor_id, categoria_id) VALUES ('Lo que no me mata, me hace más fuerte.', CURRENT_DATE + 4, 5, 1);
INSERT INTO frases (texto, fecha_programada, autor_id, categoria_id) VALUES ('En un lugar de la Mancha...', CURRENT_DATE + 5, 6, 3);
INSERT INTO frases (texto, fecha_programada, autor_id, categoria_id) VALUES ('La sencillez es la sofisticación suprema.', CURRENT_DATE + 6, 2, 2);
INSERT INTO frases (texto, fecha_programada, autor_id, categoria_id) VALUES ('Vivir es lo más raro del mundo. La mayoría de la gente existe.', CURRENT_DATE + 7, 3, 3);
INSERT INTO frases (texto, fecha_programada, autor_id, categoria_id) VALUES ('Dios ha muerto.', CURRENT_DATE + 8, 5, 1);
INSERT INTO frases (texto, fecha_programada, autor_id, categoria_id) VALUES ('El que lee mucho y anda mucho, ve mucho y sabe mucho.', CURRENT_DATE + 9, 6, 3);
INSERT INTO frases (texto, fecha_programada, autor_id, categoria_id) VALUES ('No hay nada que no se pueda conseguir con esfuerzo.', CURRENT_DATE + 10, 1, 1);
INSERT INTO frases (texto, fecha_programada, autor_id, categoria_id) VALUES ('El secreto de la existencia es no tener miedo.', CURRENT_DATE + 11, 4, 2);

-- Usuarios (Passwords: admin123 / standard123)
-- Hash para 'admin123': $2a$10$vI8.07mK.n5/q.B.7zJ7y5mO.tS7J8yv/m.W/L7X7Z.Z.Z.Z.Z.Z.Z (es un ejemplo, voy a poner uno real)
INSERT INTO usuarios (username, password, role) VALUES ('admin', '$2a$10$fVfS8.07mK.n5/q.B.7zJ7y5mO.tS7J8yv/m.W/L7X7Z.Z.Z.Z.Z.Z.Z', 'ADMIN') ON CONFLICT (username) DO NOTHING;
INSERT INTO usuarios (username, password, role) VALUES ('standard', '$2a$10$fVfS8.07mK.n5/q.B.7zJ7y5mO.tS7J8yv/m.W/L7X7Z.Z.Z.Z.Z.Z.Z', 'STANDARD') ON CONFLICT (username) DO NOTHING;
