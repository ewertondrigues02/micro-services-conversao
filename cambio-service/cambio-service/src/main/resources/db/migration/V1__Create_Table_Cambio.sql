CREATE TABLE cambio (
  id SERIAL PRIMARY KEY,
  from_currency CHAR(3) NOT NULL,
  to_currency CHAR(3) NOT NULL,
  conversion_factor DECIMAL(10,2) NOT NULL
);