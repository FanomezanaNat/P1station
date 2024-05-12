CREATE TABLE "storage"(
    id VARCHAR PRIMARY KEY DEFAULT  uuid_generate_v4(),
    station_id VARCHAR REFERENCES fuel_station(id),
    fuel_type fuel,
    value float,
    last_updated timestamp

);