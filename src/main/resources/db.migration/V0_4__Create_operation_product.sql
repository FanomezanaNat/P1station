CREATE TYPE transaction_type as ENUM('sale','supply');


CREATE TABLE "operation_product"(
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    date TIMESTAMP default now(),
    type transaction_type NOT NULL,
    product_id VARCHAR REFERENCES product(id),
    quantity FLOAT
);