CREATE TYPE fuel AS ENUM('gasoline','diesel','petrol');

CREATE TABLE "product_template"(
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    name fuel NOT NULL ,
    unit_price FLOAT NOT NULL ,
    evaporation_rate FLOAT NOT NULL
);