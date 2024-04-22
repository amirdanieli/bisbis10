CREATE TABLE IF NOT EXISTS dishes (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  price FLOAT NOT NULL,
  restaurant_id BIGINT NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);