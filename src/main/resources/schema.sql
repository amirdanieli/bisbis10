CREATE TABLE IF NOT EXISTS restaurants (
  restaurant_id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  is_kosher BOOLEAN NOT NULL,
  number_of_ratings INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS restaurant_cuisines (
  restaurant_id BIGINT NOT NULL,
  cuisine VARCHAR(255) NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id)
);

CREATE TABLE IF NOT EXISTS dishes (
  dish_id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  price DECIMAL(5, 2) NOT NULL,
  restaurant_id BIGINT NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id)
);

CREATE TABLE IF NOT EXISTS ratings (
  rating_id BIGSERIAL PRIMARY KEY,
  rating FLOAT NOT NULL,
  restaurant_id BIGINT UNIQUE NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id)
);

CREATE TABLE IF NOT EXISTS orders (
  order_id SERIAL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS order_items (
  order_item_id SERIAL PRIMARY KEY,
  dish_id INT NOT NULL,
  amount INT NOT NULL,
  order_id BIGINT,
  FOREIGN KEY (order_id) REFERENCES orders(order_id)
);