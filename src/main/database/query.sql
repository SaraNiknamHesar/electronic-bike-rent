SELECT * FROM ScooterRacks WHERE available_parking_spots > 0;

SELECT * FROM Customer WHERE paymentType = 'Credit Card';

SELECT * FROM Scooter WHERE remaining_battery_life < 50;

INSERT INTO ScooterRacks (ID, total_parking_spots, available_parking_spots, postalCode, address)
VALUES ('SR012', 10, 5, '12345', '123 Main St');

UPDATE ScooterRacks SET total_parking_spots = 15 WHERE ID = 'SR001';

DELETE FROM Customer WHERE cf = '1234567890123456';

SELECT AVG(battery_life) FROM Model;

SELECT COUNT(*) FROM Rental WHERE customer = '1234567890123456';

SELECT customer, COUNT(*) AS rentals FROM Rental GROUP BY customer ORDER BY rentals DESC;

SELECT name FROM Model WHERE price_per_min = (SELECT MAX(price_per_min) FROM Model);

SELECT rental.id, scooter.id, scooter.model, scooter.id_scooter_rack
FROM rental
INNER JOIN scooter ON rental.id_scooter=scooter.id

SELECT customer.name, customer.surname, customer.email, rental.id_scooter, scooter.model
FROM customer
JOIN rental ON customer.cf = rental.customer
JOIN scooter ON rental.id_scooter = scooter.id

SELECT customer.name, customer.surname, customer.email, rental.id_scooter, scooter.model, model.brand
FROM customer
JOIN rental ON customer.cf = rental.customer
JOIN scooter ON rental.id_scooter = scooter.id
JOIN model ON scooter.model = model.name
