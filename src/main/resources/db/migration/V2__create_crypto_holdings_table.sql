-- V2__create_crypto_holdings_table.sql

CREATE TABLE crypto_holdings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    symbol VARCHAR(100) NOT NULL,
    price VARCHAR(100) NOT NULL,
    amount VARCHAR(100) NOT NULL,
    transaction_type VARCHAR(100) NOT NULL,
    purchase_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);