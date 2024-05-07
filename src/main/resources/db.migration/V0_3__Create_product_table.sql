CREATE TABLE "product"(
    id SERIAL PRIMARY KEY,
    fuel_station_id VARCHAR REFERENCES "fuel_station"(id),
    product_template_id VARCHAR REFERENCES "product_template"(id)
)