CREATE DATABASE kaly_db;

CREATE TABLE restaurant (
                            re_id SERIAL PRIMARY KEY,
                            re_location VARCHAR NOT NULL
);
CREATE TABLE menu(
                     me_id SERIAL PRIMARY KEY,
                     me_name VARCHAR NOT NULL,
);
CREATE TABLE sell(
                     sl_id SERIAL PRIMARY KEY,
                     re_id INT NOT NULL REFERENCES restaurant(re_id),
                     me_id INT NOT NULL REFERENCES menu(me_id),
                     sl_qty DOUBLE PRECISION NOT NULL,
                     sl_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE menu_apply(
                           me_ap_id SERIAL PRIMARY KEY,
                           me_id INT NOT NULL REFERENCES menu(me_id),
                           pr_id INT NOT NULL REFERENCES price(pr_id)
);
CREATE TABLE menu_price(
                           me_pr_id SERIAL PRIMARY KEY,
                           me_pr_amount DOUBLE PRECISION NOT NULL,
                           me_pr_app_datetime TIMESTAMP NOT NULL
);
CREATE TABLE ingredient(
                           in_id SERIAL PRIMARY KEY,
                           in_qty DOUBLE PRECISION NOT NULL,
                           in_unity VARCHAR NOT NULL,
                           in_up DOUBLE PRECISION NOT NULL
);
CREATE TABLE ingr_price(
                           in_pr_id SERIAL PRIMARY KEY,
                           in_pr_amount DOUBLE PRECISION NOT NULL,
                           in_pr_app_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           in_id INT NOT NULL REFERENCES ingredient(in_id)
);
CREATE TABLE ingr_apply(
                           in_ap_id SERIAL PRIMARY KEY,
                           in_id INT NOT NULL REFERENCES ingredient(in_id),
                           in_pr_id INT NOT NULL REFERENCES ingr_price(in_pr_id)
);
CREATE TABLE stock(
                      st_id SERIAL PRIMARY KEY,
                      in_id INT NOT NULL REFERENCES ingredient(in_id),
                      st_remaining_qty DOUBLE INT NOT NULL,
                      st_update_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE compose(
                        cm_id SERIAL PRIMARY KEY,
                        in_id INT NOT NULL REFERENCES ingredient(in_id),
                        me_id INT NOT NULL REFERENCES menu(me_id),
                        cm_nec_qty DOUBLE PRECISION NOT NULL,
                        cm_unity VARCHAR NOT NULL
);