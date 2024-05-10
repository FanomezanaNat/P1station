CREATE TABLE "fuel_station"(
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    address VARCHAR NOT NULL ,
    id_location VARCHAR REFERENCES "location"(id)
);