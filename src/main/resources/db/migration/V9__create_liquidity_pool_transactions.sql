CREATE TABLE liquidity_transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(100) NOT NULL,
    pool_reference VARCHAR(255) NOT NULL,
    token_a_symbol VARCHAR(20) NOT NULL,
    token_b_symbol VARCHAR(20) NOT NULL,
    token_a_amount VARCHAR(100) NOT NULL,
    token_b_amount VARCHAR(100) NOT NULL,
    price_lower VARCHAR(100) NOT NULL,
    price_upper VARCHAR(100) NOT NULL,
    is_active VARCHAR(100) NOT NULL,
    position_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);