CREATE TABLE IF NOT EXISTS restaurant
(
    id UUID  NOT NULL,
    name VARCHAR(20) NOT NULL,
    address VARCHAR(50) NOT NULL,
    type VARCHAR(20) NOT NULL,
    CONSTRAINT restaurant_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS meal
(
    id UUID NOT NULL,
    restaurant_id UUID NOT NULL,
    name VARCHAR(50) NOT NULL,
    price float NOT NULL,
    CONSTRAINT restaurant_meal_pkey FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);
