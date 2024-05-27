-- V2__create_crypto_holdings_table.sql

CREATE TABLE crypto_holdings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    crypto_name VARCHAR(100) NOT NULL,
    symbol VARCHAR(100) NOT NULL,
    purchase_price DECIMAL(19, 4) NOT NULL,
    amount_bought DECIMAL(19, 4) NOT NULL,
    purchase_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);