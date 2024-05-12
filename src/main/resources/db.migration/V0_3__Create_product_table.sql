CREATE TABLE "product"(
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    evaporation_rate float ,
    fuel_station_id VARCHAR REFERENCES "fuel_station"(id),
    product_template_id VARCHAR REFERENCES "product_template"(id)
);