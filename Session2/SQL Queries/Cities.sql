CREATE TABLE City(
  id    serial PRIMARY KEY,
  name  VARCHAR(50) NOT NULL,
  countryCode CHAR(2) NOT NULL,
  latitude DECIMAL(10,8) NOT NULL,
  longitude DECIMAL (11,8) NOT NULL
);

INSERT INTO City (name, countryCode, latitude, longitude)
VALUES ('Paris','FR', 48.8534, 2.3488 );

INSERT INTO City(name, countryCode, latitude, longitude)
VALUES('Paris', 'US', 33.668144, -95.543979);

SELECT * FROM City WHERE name = 'Paris';

INSERT INTO City(name, countryCode, latitude, longitude)
VALUES('Test', 'FR', 10, 10);

UPDATE City
SET name = 'Paris'
WHERE id=3;

DELETE FROM City WHERE id=3;

CREATE INDEX idx_city_name_country ON city (name, countryCode);
CREATE INDEX idx_city_lat_long ON city(latitude, longitude);
