-- Создаем схемы для каждого микросервиса
CREATE SCHEMA IF NOT EXISTS product_schema;
CREATE SCHEMA IF NOT EXISTS order_schema;
CREATE SCHEMA IF NOT EXISTS user_schema;
CREATE SCHEMA IF NOT EXISTS cart_schema;

-- Даем права
GRANT ALL ON SCHEMA product_schema TO postgres;
GRANT ALL ON SCHEMA order_schema TO postgres;
GRANT ALL ON SCHEMA user_schema TO postgres;
GRANT ALL ON SCHEMA cart_schema TO postgres;