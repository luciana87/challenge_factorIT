-- CREATE DATABASE IF NOT EXISTS ecommerce;
-- use ecommerce;

INSERT INTO products (deleted, description, name, price, category)
VALUES 
(false, 'Descubrí infinitas posibilidades para tus fotos con las 3 cámaras principales de tu equipo.', 'Samsung Galaxy A15', 349999, 'ELECTRONICA'),
(false, 'La freidora de aire Atma redefine la cocina saludable con su capacidad de 6.5 litros', 'Freidora De Aire Digital ATMA', 100948, 'ELECTRODOMESTICOS'),
(false, 'El estuche de carga tiene un diseño limpio y elegante. Cada auricular pesa solo 3.6 g', 'Auriculares Inalámbricos Xiaomi Redmi', 28500, 'ELECTRONICA'),
(false, 'Estas zapatillas adidas actualizan un diseño clásico con materiales modernos.', 'Zapatillas Daily 3.0', 89999, 'CALZADO'),
(false, 'Combina los colores y patrones de la selva brasileña en un diseño único.', 'Pantalón Tiro adidas x FARM', 109999, 'INDUMENTARIA'),
(true, 'Unidad de 210 gramos. Cereal con el auténtico sabor a chocolate NESQUIK.', 'Cereales Nesquik De Chocolate 210 G', 3236.35, 'ALMACEN');

INSERT INTO promotional_dates (started_at, ended_at)
VALUES 
('2025-04-17 00:00:00', '2025-04-20 00:00:00'),
('2025-05-01 00:00:00', '2025-05-04 00:00:00'),
('2025-07-05 00:00:00', '2025-07-09 00:00:00'),
('2025-06-18 00:00:00', '2025-06-19 00:00:00');

INSERT INTO vips (active, modified_at, user_id)
VALUES (true, '2024-09-10 12:45:51', 1),
(true, '2025-01-13 18:05:32', 2),
(false, '2024-12-01 00:00:00', 5);
