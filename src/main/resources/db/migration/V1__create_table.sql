CREATE TABLE restaurant (
                            id SERIAL PRIMARY KEY,
                            location VARCHAR NOT NULL
);
CREATE TABLE menu(
                     id SERIAL PRIMARY KEY,
                     name VARCHAR NOT NULL
);
CREATE TABLE sell(
                     id SERIAL PRIMARY KEY,
                     qty DOUBLE PRECISION NOT NULL,
                     datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                     resto_id INT NOT NULL REFERENCES restaurant(re_id),
                     menu_id INT NOT NULL REFERENCES menu(me_id)
);
CREATE TABLE menu_price(
                           id SERIAL PRIMARY KEY,
                           amount DOUBLE PRECISION NOT NULL,
                           price_datetime TIMESTAMP NOT NULL,
                            menu INT NOT NULL REFERENCES menu(id)
);
CREATE TABLE menu_apply(
                           id SERIAL PRIMARY KEY,
                           id INT NOT NULL REFERENCES menu(me_pr_id),
                           menu_price_id INT NOT NULL REFERENCES menu_price(id)
);
CREATE TABLE ingredient(
                           id SERIAL PRIMARY KEY,
                           qty DOUBLE PRECISION NOT NULL,
                           unity VARCHAR NOT NULL,
                           unit_price DOUBLE PRECISION NOT NULL
);
CREATE TABLE ingredient_price(
                           id SERIAL PRIMARY KEY,
                           amount DOUBLE PRECISION NOT NULL,
                           price_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           ingredient_id INT NOT NULL REFERENCES ingredient(id)
);
CREATE TABLE ingredient_apply(
                           id SERIAL PRIMARY KEY,
                           ingredient_id INT NOT NULL REFERENCES ingredient(id),
                           ingredient_price_id INT NOT NULL REFERENCES ingredient_price(id)
);
CREATE TYPE MVT_TYPE AS ENUM('IN, OUT');
CREATE TABLE stock_mvt(
    id SERIAL PRIMARY KEY,
    mvt_type MVT_TYPE NOT NULL,
    mvt_qty DOUBLE PRECISION NOT NULL,
    updated_qty DOUBLE PRECISION NOT NULL,
    mvt_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    resto_id INT NOT NULL REFERENCES restaurant(id),
    ingredient_id INT NOT NULL REFERENCES ingredient(d)
);
CREATE TABLE compose(
                        id SERIAL PRIMARY KEY,
                        ingredient_id INT NOT NULL REFERENCES ingredient(id),
                        menu_id INT NOT NULL REFERENCES menu(id),
                        necessary_qty DOUBLE PRECISION NOT NULL
);
