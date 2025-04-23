-- CREATE DATABASE IF NOT EXISTS ecommerce;
-- use ecommerce;

INSERT INTO products (deleted, description, name, price, category)
VALUES 
(false, 'Descubrí infinitas posibilidades para tus fotos', 'Samsung Galaxy A15', 349999, 'ELECTRONICA'),
(false, 'La freidora de aire Atma redefine la cocina saludable', 'Freidora De Aire Digital ATMA', 100948, 'ELECTRODOMESTICOS'),
(false, 'El estuche de carga tiene un diseño limpio y elegante', 'Auriculares Inalámbricos', 28500, 'ELECTRONICA'),
(false, 'Estas zapatillas adidas actualizan un diseño clásico', 'Zapatillas Daily 3.0', 89999, 'CALZADO'),
(false, 'Combina los colores y patrones de la selva brasileña', 'Pantalón Tiro adidas x FARM', 109999, 'INDUMENTARIA'),
(true, 'Cereal con el auténtico sabor a chocolate NESQUIK.', 'Cereales Nesquik', 3236.35, 'ALMACEN'),
(false, 'Yerba mate Mañanita 4flex 1kg.', 'Yerba mate bajo polvo.', 2842.5, 'ALMACEN');

INSERT INTO promotional_dates (started_at, ended_at)
VALUES 
('2025-04-17 00:00:00', '2025-04-20 00:00:00'),
('2025-05-01 00:00:00', '2025-05-04 00:00:00'),
('2025-07-05 00:00:00', '2025-07-09 00:00:00'),
('2025-06-18 00:00:00', '2025-06-19 00:00:00');
