DROP TABLE IF EXISTS eshop_user CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS product_category CASCADE;
DROP TABLE IF EXISTS supplier CASCADE;

CREATE TABLE eshop_user
(
    id        SERIAL PRIMARY KEY NOT NULL,
    full_name VARCHAR(200)       NOT NULL,
    password  VARCHAR(200)       NOT NULL,
    email     VARCHAR(200)       NOT NULL
);

CREATE TABLE product
(
    id           SERIAL PRIMARY KEY NOT NULL,
    product_name VARCHAR(200)       NOT NULL,
    price        DECIMAL            NOT NULL,
    currency     VARCHAR(200)       NOT NULL,
    description  VARCHAR(200),
    image_path        VARCHAR(200),
    supplier_id  INTEGER            NOT NULL,
    category_id  INTEGER            NOT NULL
);

CREATE TABLE product_category
(
    id            SERIAL PRIMARY KEY NOT NULL,
    category_name VARCHAR(200)       NOT NULL,
    department    VARCHAR(200)       NOT NULL
);

CREATE TABLE supplier(
    id       SERIAL PRIMARY KEY     NOT NULL,
    supplier_name   VARCHAR(200)    NOT NULL,
    description     VARCHAR(200)    NOT NULL
    );

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES supplier(id);

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES product_category(id);

INSERT INTO product_category VALUES (1, 'Knife', 'Cuisinart') ;
INSERT INTO product_category VALUES (2, 'Gun', 'Guns') ;
INSERT INTO product_category VALUES (3, 'NFT', 'Digital art') ;

INSERT INTO supplier VALUES (1, 'Valve', 'Valve is a video game developer and publisher company') ;
INSERT INTO supplier VALUES (2, 'OpenSea', 'A peer-to-peer marketplace for NFTs') ;
INSERT INTO supplier VALUES (3, 'Staub', 'A quality knife producer.');

INSERT INTO product VALUES (1,'M4A4 | Howl',2000,'USD','CS:GO digital skin. Get your hand on a rare peace of art now!','/static/img/product_1.jpg',1,2);
INSERT INTO product VALUES (2,'M4A4 | Poseidon',1556,'USD','CS:GO digital skin. A mostly rare skin with rare condition. Feel the power of the sea!','/static/img/product_2.jpg',1,2);
INSERT INTO product VALUES (3,'AWP | Dragon lore',2000,'USD','CS:GO digital skin. A nice skin with nordic motives and a nice dragon art.','/static/img/product_3.jpg',1,2);
INSERT INTO product VALUES (4,'M4A1 | Imminent Danger',239,'USD','CS:GO digital skin. Warn others as you take the lead on the battlefield.','/static/img/product_4.jpg',1,2);
INSERT INTO product VALUES (5,'M9 Bayonet | Crimson Web',258,'USD','CS:GO digital skin. A knife with red color and a black web design on the middle.','/static/img/product_5.jpg',1,1);
INSERT INTO product VALUES (6,'Classic Knife',214,'USD','CS:GO digital skin. Feel the old times with this peace of art!','/static/img/product_6.jpg',1,1);
INSERT INTO product VALUES (7,'Kitchen Knife',64,'USD','A quality knife. Perfect for cutting meat and vegetables!','/static/img/product_7.jpg',3,1);
INSERT INTO product VALUES (8,'Golden monkey art',2999,'USD','A digital art from a golden monkey','/static/img/product_8.jpg',2,3);
INSERT INTO product VALUES (9,'Intelligent gorilla art',699,'USD','A digital art from a very intelligent gorilla','/static/img/product_9.jpg',2,3);
INSERT INTO product VALUES (10,'Frankenstein`s apple art',755,'USD','A digital art from an apple cosplaying Frankenstein`s monster','/static/img/product_10.jpg',2,3);
INSERT INTO product VALUES (11,'Surprised strawberry art',465,'USD','A digital art from a surprised strawberry','/static/img/product_11.jpg',2,3);
INSERT INTO product VALUES (12,'Cool bomb art',643,'USD','A digital art from a bomb','/static/img/product_12.jpg',2,3);
INSERT INTO product VALUES (13,'Pirate monkey art',265,'USD','A digital art from a monkey with an eye patch','/static/img/product_13.jpg',2,3);
INSERT INTO product VALUES (14,'Surprised bomb art',245,'USD','A digital art from a surprised bomb','/static/img/product_14.jpg',2,3);
INSERT INTO product VALUES (15,'Toxic doge art',135,'USD','A digital art from a toxic dog','/static/img/product_15.jpg',2,3);

INSERT INTO eshop_user VALUES(1,'Józsi','kek', 'csipkej98@gmail.com');