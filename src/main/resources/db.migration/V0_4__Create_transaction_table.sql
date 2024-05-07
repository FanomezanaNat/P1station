CREATE TYPE transaction_type as ENUM('income','outcome');

CREATE TABLE "transaction"(
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    date TIMESTAMP default now(),
    product_template_id VARCHAR REFERENCES "product_template"(id),
    fuel_station_id VARCHAR REFERENCES "fuel_station"(id),
    type transaction_type NOT NULL

);