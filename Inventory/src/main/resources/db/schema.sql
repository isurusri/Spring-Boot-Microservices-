CREATE TABLE IF NOT EXISTS Product
(
    id       INTEGER AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    quantity INTEGER      NOT NULL,
    price    DOUBLE       NOT NULL
);

