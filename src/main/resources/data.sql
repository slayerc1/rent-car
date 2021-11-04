
INSERT INTO special_offer (day_start, day_end, offer_pct) VALUES
  (0, 7, 1),
  (7, 30, 0.8),
  (30, null, 0.5),
  (7, null, 0.6);

INSERT INTO category (name, price, extra_day_price, extra_day_pct, loyalty_points) VALUES
  ('PREMIUM', 300, 300, 0.2, 5),
  ('SUV', 150, 50, 0.6, 3),
  ('SMALL', 50, 50, 0.3, 1);

INSERT INTO category_offers (category, special_offer) VALUES
  ('SUV', 1),
  ('SUV', 2),
  ('SUV', 3),
  ('SMALL', 1),
  ('SMALL', 4);

INSERT INTO car (brand, model, category) VALUES
  ('BMW', 'M7', 'PREMIUM'),
  ('Kia', 'Sorento', 'SUV'),
  ('Nissan', 'Juke', 'SUV'),
  ('Seat', 'Ibiza', 'SMALL');

INSERT INTO user (user_id, name) VALUES
  ('user1', 'Raul'),
  ('user2', 'Ruben'),
  ('user3', 'Mario');
