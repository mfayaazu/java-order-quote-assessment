CREATE TABLE quotes (
    id VARCHAR(36) PRIMARY KEY,
    customer_type VARCHAR(20) NOT NULL,
    coupon_code VARCHAR(50),
    subtotal NUMERIC(12, 2) NOT NULL,
    discount NUMERIC(12, 2) NOT NULL,
    delivery_charge NUMERIC(12, 2) NOT NULL,
    final_total NUMERIC(12, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE quote_items (
    id VARCHAR(36) PRIMARY KEY,
    quote_id VARCHAR(36) NOT NULL,
    product_id VARCHAR(100) NOT NULL,
    unit_price NUMERIC(12, 2) NOT NULL,
    quantity INT NOT NULL,
    CONSTRAINT fk_quote_items_quote FOREIGN KEY (quote_id) REFERENCES quotes(id) ON DELETE CASCADE
);

CREATE INDEX idx_quote_items_product_id ON quote_items(product_id);
