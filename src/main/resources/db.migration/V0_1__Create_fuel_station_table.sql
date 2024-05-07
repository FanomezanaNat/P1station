CREATE EXTENSION IF NOT EXISTS  "uuid-ossp";


CREATE TABLE "fuel_station"(
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR NOT NULL ,
    address VARCHAR NOT NULL ,
    id_location INT REFERENCES "location"(id),
    fuel_capacity int default null

);