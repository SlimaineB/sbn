
DELETE FROM erp_db.product_category;
DELETE FROM erp_db.product;

INSERT INTO erp_db.product_category (ID,  NAME) VALUES (1,'Legume');
INSERT INTO erp_db.product_category (ID,  NAME) VALUES (2,'Fruit');
INSERT INTO erp_db.product (ID, CATEGORY_ID, NAME, PRICE) VALUES (1, 1, 'Patate', 17.2);
INSERT INTO erp_db.product (ID, CATEGORY_ID, NAME, PRICE) VALUES (2, 2, 'Poire', 17.2);