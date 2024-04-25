-- Insert sample restaurants
INSERT INTO restaurants (name, is_kosher) VALUES ('Taizu', false);
INSERT INTO restaurants (name, is_kosher) VALUES ('Sushi Bar', true);

-- Insert cuisines for each restaurant
INSERT INTO restaurant_cuisines (restaurant_id, cuisine) VALUES
(1, 'Asian'), (1, 'Mexican'), (1, 'Indian'),
(2, 'Japanese'), (2, 'Sushi');

-- Insert sample dishes for each restaurant
INSERT INTO dishes (name, description, price, restaurant_id) VALUES
('Noodles', 'Amazing one', 59, 1),
('Shakshuka', 'Great one', 34, 1),
('Sushi Roll', 'Delicious one', 20, 2);
