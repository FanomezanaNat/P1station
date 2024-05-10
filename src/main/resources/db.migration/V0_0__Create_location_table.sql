CREATE EXTENSION IF NOT EXISTS  "uuid-ossp";

CREATE TABLE "location"(
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    latitude VARCHAR NOT NULL ,
    longitude VARCHAR
);