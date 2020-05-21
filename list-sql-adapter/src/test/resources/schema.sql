CREATE TABLE IF NOT EXISTS pinelists
(
    id VARCHAR(32) PRIMARY KEY NOT NULL,
    name VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS items
(
    item_id VARCHAR(32) PRIMARY KEY NOT NULL,
    name VARCHAR(256) NOT NULL,
    pinelist_id VARCHAR(32) REFERENCES pinelists (id)
);