-- USE DB_BOOKING;

-- APP LANGUAGES
INSERT INTO APP_LANGUAGE(ID, LANG) VALUES(1, 'ES'); # SPANISH
INSERT INTO APP_LANGUAGE(ID, LANG) VALUES(2, 'EN'); # ENGLISH
INSERT INTO APP_LANGUAGE(ID, LANG) VALUES(3, 'IT'); # ITALIAN

-- APP PRICING PLANS

-- CREATE ALL PLANS FEATURES
INSERT INTO PLAN_FEATURE(ID, DETAIL) VALUES(1, 'Panel de administración');
INSERT INTO PLAN_FEATURE(ID, DETAIL) VALUES(2, 'Contabilidad');
INSERT INTO PLAN_FEATURE(ID, DETAIL) VALUES(3, 'Sistema de reservas');
INSERT INTO PLAN_FEATURE(ID, DETAIL) VALUES(4, 'Soporte técnico');
INSERT INTO PLAN_FEATURE(ID, DETAIL) VALUES(5, 'Soporte técnico premium');
INSERT INTO PLAN_FEATURE(ID, DETAIL) VALUES(6, 'Soporte telefónico');
INSERT INTO PLAN_FEATURE(ID, DETAIL) VALUES(7, 'Creación códigos promocionales');
INSERT INTO PLAN_FEATURE(ID, DETAIL) VALUES(8, 'Gestión de clientes');

-- PLAN NUM.1 - STARTER PLAN - PAY ONCE A YEAR (PRICE PER MONTH)
INSERT INTO APP_PLAN(ID, PLAN_TYPE, PRICE) VALUES(1, 'YEAR', 15.99);

INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(1, 1);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(1, 3);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(1, 4);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(1, 8);

-- PLAN NUM.2 - STARTER PLAN - MONTH (PRICE PER MONTH)
INSERT INTO APP_PLAN(ID, PLAN_TYPE, PRICE) VALUES(2, 'MONTH', 23.98);

INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(2, 1);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(2, 3);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(2, 4);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(2, 8);

-- PLAN NUM.3 - PROFESSIONAL PLAN - PAY ONCE A YEAR (PRICE PER MONTH)
INSERT INTO APP_PLAN(ID, PLAN_TYPE, PRICE) VALUES(3, 'YEAR', 23.98);

INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(3, 1);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(3, 2);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(3, 3);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(3, 5);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(3, 6);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(3, 7);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(3, 8);

-- PLAN NUM.4 - PROFESSIONAL PLAN - MONTH (PRICE PER MONTH)
INSERT INTO APP_PLAN(ID, PLAN_TYPE, PRICE) VALUES(4, 'MONTH', 35.98);

INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(4, 1);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(4, 2);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(4, 3);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(4, 5);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(4, 6);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(4, 7);
INSERT INTO PLAN_FEATURE_APP_PLAN(ID_PLAN, ID_PLAN_FEATURE) VALUES(4, 8);

-- SAMPLE USERS

-- NAME: Samuel
-- SURNAME: Cooper
-- EMAIL: samuel.cooper@gmail.com
-- PHONE: +34 773 893 743 
-- PASS: 1234 --> 03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4

-- >> HOST

-- CONFIGURATIONS

-- SPANISH - EUR
INSERT INTO USER_CONFIGURATION(ID, ID_APP_LANGUAGE, ID_CURRENCY) VALUES(1, 1, 2);

-- ENGLISH - 
INSERT INTO USER_CONFIGURATION(ID, ID_APP_LANGUAGE, ID_CURRENCY) VALUES(2, 2, 100);


-- BIO: ¡Me encanta viajar! Por ello, ofrezco no sólo un hogar en Granada a la gente que viene de fuera, sino que 
-- me encantará aconsejarles sobre los sitios más bonitos para pasear, mis bares o restaurantes favoritos, las 
-- vistas más bonitas de la ciudad,...
-- DIRECTION: C/ Canónigo Valiño, 28
-- LANGUAGES: SPANISH AND ENGLISH
-- VERIFIED: NO
-- PLAN: NUM.1
-- PASS: password1 --> $2a$04$GJ85Ihcglhbqac2zc3z3A.C3v55FMmN8.qGQ8FlNBCuyLtQ5/TyMO
INSERT INTO APP_USER(ID, UNAME, SURNAME, EMAIL, PHONE, PASS, ID_APP_CONFIGURATION) VALUES(1, 'Samuel', 'Cooper', 'samuel.cooper@gmail.com', '773893743 ', '$2a$04$GJ85Ihcglhbqac2zc3z3A.C3v55FMmN8.qGQ8FlNBCuyLtQ5/TyMO', 1);
INSERT INTO USER_HOST(ID, DNI, BIO, DIRECTION) VALUES(1, '15412808W', '¡Me encanta viajar! Por ello, ofrezco no sólo un hogar en Granada a la gente que viene de fuera, sino que me encantará aconsejarles sobre los sitios más bonitos para pasear, mis bares o restaurantes favoritos, las vistas más bonitas de la ciudad,...', 'C/ Canónigo Valiño, 28');
INSERT INTO HOST_LANGUAGE(ID_HOST, ID_LANG, IS_NATIVE) VALUES(1, 1, TRUE); # SPANISH
INSERT INTO HOST_LANGUAGE(ID_HOST, ID_LANG) VALUES(1, 2); # ENGLISH

INSERT INTO PLAN_SUBSCRIPTION(ID_PLAN, ID_USER) VALUES(1, 1);

-- NAME: Dana
-- SURNAME: Moreno
-- EMAIL: dana.moreno@gmail.com
-- PHONE: +34 680 745 322
-- PASS: hello --> $2a$12$QUek6cYr28DOEyiCElA95e0z6DzygeYL7ouf70.AA541Wh4LJPyfu
INSERT INTO APP_USER(ID, UNAME, SURNAME, EMAIL, PHONE, PASS, ID_APP_CONFIGURATION) VALUES(2, 'Dana', 'Moreno', 'dana.moreno@gmail.com', '680745322', '$2a$12$QUek6cYr28DOEyiCElA95e0z6DzygeYL7ouf70.AA541Wh4LJPyfu', 1);

-- NAME: Ardalion
-- SURNAME: Meza Ocampo
-- EMAIL: ardalion.mezao@gmail.com
-- PHONE: +34 720 671 778
-- PASS: ardalion --> $2a$12$jIDE.Hvw7itNBQwv6zxCWebjpEqoalGvN3jTfvTnXplpCjh4Xw/v2

-- >> HOST

-- BIO: Vivo en León con mi familia en una zona muy tranquila y muy cerca del centro de la ciudad (10min).
-- LANGUAGES: SPANISH

-- PLAN: NUM.4
INSERT INTO APP_USER(ID, UNAME, SURNAME, EMAIL, PHONE, PASS, ID_APP_CONFIGURATION) VALUES(3, 'Ardalion', 'Meza Ocampo', 'ardalion.mezao@gmail.com', '720671778', '$2a$12$jIDE.Hvw7itNBQwv6zxCWebjpEqoalGvN3jTfvTnXplpCjh4Xw/v2', 2);
INSERT INTO USER_HOST(ID, DNI, BIO, DIRECTION) VALUES(3, '14055845Q', 'Vivo en León con mi familia en una zona muy tranquila y muy cerca del centro de la ciudad (10min).', 'Avda/ Mariano Andrés, 120');
INSERT INTO HOST_LANGUAGE(ID_HOST, ID_LANG, IS_NATIVE) VALUES(3, 1, TRUE); # SPANISH

INSERT INTO PLAN_SUBSCRIPTION(ID_PLAN, ID_USER) VALUES(4, 3);

-- NAME: Marta
-- SURNAME: Soto Pérez
-- EMAIL: marta.sotop@gmail.com
-- PHONE: +34 664 889 909
-- PASS: tY1uU#%79hMv --> $2a$12$hkP/e2Y2VqkTwq3Dg0sQDOCo950lxE/zMap5Joq5YdBxDNCxX4zYu

-- >> HOST

-- BIO:
-- LANGUAGES: SPANISH AND ITALIAN
-- VERIFIED: YES

-- PLAN: NUM.3
INSERT INTO APP_USER(ID, UNAME, SURNAME, EMAIL, PHONE, PASS, ID_APP_CONFIGURATION) VALUES(4, 'Marta', 'Soto Pérez', 'marta.sotop@gmail.com', '664889909', '$2a$12$hkP/e2Y2VqkTwq3Dg0sQDOCo950lxE/zMap5Joq5YdBxDNCxX4zYu', 1);
INSERT INTO USER_HOST(ID, DNI, DIRECTION) VALUES(4, '10081139D', 'Avda/ de Europa 2');
INSERT INTO HOST_LANGUAGE(ID_HOST, ID_LANG, IS_NATIVE) VALUES(4, 1, TRUE); # SPANISH
INSERT INTO HOST_LANGUAGE(ID_HOST, ID_LANG) VALUES(4, 3); # ITALIAN

INSERT INTO PLAN_SUBSCRIPTION(ID_PLAN, ID_USER) VALUES(3, 4);

-- NAME: Antonio
-- SURNAME: Rodríguez Fernández
-- EMAIL: antonio.rodfer@gmail.com
-- PHONE: +34 757 791 565
-- PASS: Antonio1234* --> $2a$12$v3JFeMwVSLtQg3WsbNVIHe67ecIPqpdra.YK5dQiAVThXxIH8M55C
INSERT INTO APP_USER(ID, UNAME, SURNAME, EMAIL, PHONE, PASS, ID_APP_CONFIGURATION) VALUES(5, 'Antonio', 'Rodríguez Fernández', 'antonio.rodfer@gmail.com', '757791565', '$2a$12$v3JFeMwVSLtQg3WsbNVIHe67ecIPqpdra.YK5dQiAVThXxIH8M55C', 1);

-- NAME: Marina 
-- SURNAME: Riojas Sarabia
-- EMAIL: marina22.rodsar@gmail.com
-- PHONE: +34 648 773 354
-- PASS: RioMi22230*$ --> $2a$12$SrqfKU60SsBhPRIucQiKAOmNzTwWJeYXOrbuM2HMB0aOfd15pSC32
INSERT INTO APP_USER(ID, UNAME, SURNAME, EMAIL, PHONE, PASS, ID_APP_CONFIGURATION) VALUES(6, 'Marina', 'Riojas Sarabia', 'marina22.rodsar@gmail.com', '648773354', '$2a$12$SrqfKU60SsBhPRIucQiKAOmNzTwWJeYXOrbuM2HMB0aOfd15pSC32', 2);

-- USUARIO ADMIN
-- PASS: Cqc3MHB#aJbo!lr# --> $2a$12$sOsCaNg4.OEwsF515ESzeeXoYASgruL8rMznrZi7hVx6OkjWegR2u
INSERT INTO APP_USER(ID, UNAME, SURNAME, EMAIL, PHONE, PASS) VALUES(7, 'administrator', 'administrator', 'admin@leoncamp.es', '000000000', '$2a$12$sOsCaNg4.OEwsF515ESzeeXoYASgruL8rMznrZi7hVx6OkjWegR2u');

-- -------------
-- ACCOMODATIONS SERVICES
-- -------------
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(1, 'Wifi');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(2, 'Calefacción');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(3, 'TV');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(4, 'Ropa de cama');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(5, 'Toallas');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(6, 'Detector de humo');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(7, 'Botiquín');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(8, 'Desayuno');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(9, 'Aparcamiento gratuito');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(10, 'Aire acondicionado');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(11, 'Agua caliente');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(12, 'Secador de pelo');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(13, 'Lavadora');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(14, 'Plancha');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(15, 'Vajilla');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(16, 'Admite mascotas');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(17, 'Apto para fumadores');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(18, 'Cuna');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(19, 'Productos de limpieza');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(20, 'Microondas');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(21, 'Cafetera');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(22, 'Accesible a minusválidos');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(23, 'Congelador');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(24, 'Horno');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(25, 'Lavavajillas');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(26, 'Terraza / patio');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(27, 'Ascensor');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(28, 'Bañera de hidromasaje');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(29, 'Piscina privada');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(30, 'Piscina compartida');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(31, 'Jardín');
INSERT INTO ACCOMODATION_SERVICE(ID, DENOMINATION) VALUES(32, 'Zona para trabajar / Oficina');


-- -------------
-- ACCOMODATION RULES
-- -------------
INSERT INTO ACCOMODATION_RULE(ID, RULE) VALUES(1, 'Prohibido fumar');
INSERT INTO ACCOMODATION_RULE(ID, RULE) VALUES(2, 'No se admiten mascotas');
INSERT INTO ACCOMODATION_RULE(ID, RULE) VALUES(3, 'Están totalmente prohibidas las fiestas y eventos');


-- -------------
-- ACCOMODATION CATEGORIES
-- -------------
INSERT INTO ACCOMODATION_CATEGORY(ID, ACC_CAT) VALUES(1, 'Apartamento');
INSERT INTO ACCOMODATION_CATEGORY(ID, ACC_CAT) VALUES(2, 'Loft');
INSERT INTO ACCOMODATION_CATEGORY(ID, ACC_CAT) VALUES(3, 'Chalet');
INSERT INTO ACCOMODATION_CATEGORY(ID, ACC_CAT) VALUES(4, 'Ático');
INSERT INTO ACCOMODATION_CATEGORY(ID, ACC_CAT) VALUES(5, 'Dúplex');
INSERT INTO ACCOMODATION_CATEGORY(ID, ACC_CAT) VALUES(6, 'Adosado');
INSERT INTO ACCOMODATION_CATEGORY(ID, ACC_CAT) VALUES(7, 'Estudio');

-- -------------
-- ACCOMODATIONS
-- -------------

-- ACCOMODATION NUM.1
-- NÚMERO DE REGISTRO: 0001234A
-- Nº CAMAS: 3
-- Nº BAÑOS: 1
-- Nº INVITADOS: 4
-- Nº HABITACIONES: 2
-- DIRECCIÓN. AVENIDA DE ROMA 3, LEÓN
-- CATEGORIA: APARTAMENTO
-- SUPERFICIE: 75 METROS CUADRADOS
-- PRECIO: 35€ / NOCHE
INSERT INTO ACCOMODATION_LOCATION(ID, LAT, LNG, DIRECTION, CITY, ZIP) VALUES(1, 42.59855643669309, -5.576348640183169, 'Av. de Roma, 3', 'León', 24001);
INSERT INTO ACCOMODATION(REG_NUM, ACC_DESCRIPTION, BEDS, NUM_BATHROOMS, NUM_BEDROOMS, PRICE_PER_NIGHT, GUESTS, AREA, ID_ACC_CATEGORY, ID_ACC_LOCATION, ID_USER_OWNER) VALUES('0001234A', 'El moderno apartamento cuenta con una habitación independiente con cama de matrimonio y tv. El salón tiene un sofá cama doble, tv y un agradable calefactor eléctrico led. Cocina totalmente equipada y utensilios básicos para cocinar. Baño amplio con ducha. Calefacción y wifi incluidos.
cuenta con un dormitorio con cama doble y televisor. La sala de estar cuenta con un sofá cama para 2 personas, televisor y un cómodo calentador eléctrico led. Cocina totalmente equipada. El baño tiene una ducha. Sistema de calefacción y WiFi incluido.'
, 3, 1, 2, 35, 4, 75, 1, 1, 1);

-- RULES
INSERT INTO ACCOMODATION_ACC_RULE(ID_ACC, ID_ACC_RULE) VALUES('0001234A', 1);
INSERT INTO ACCOMODATION_ACC_RULE(ID_ACC, ID_ACC_RULE) VALUES('0001234A', 2);
INSERT INTO ACCOMODATION_ACC_RULE(ID_ACC, ID_ACC_RULE) VALUES('0001234A', 3);

-- SERVICES
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0001234A', 1);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0001234A', 2);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0001234A', 3);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0001234A', 4);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0001234A', 5);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0001234A', 11);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0001234A', 12);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0001234A', 13);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0001234A', 19);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0001234A', 20);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0001234A', 21);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0001234A', 23);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0001234A', 25);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0001234A', 32);

-- ACCOMODATION IMAGES
INSERT INTO ACCOMODATION_IMAGE(ID, IMG_URL) VALUES(1, 'https://images.unsplash.com/photo-1553444836-bc6c8d340ba7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1169&q=80');
INSERT INTO ACCOMODATION_IMAGE(ID, IMG_URL) VALUES(2, 'https://images.unsplash.com/photo-1522771739844-6a9f6d5f14af?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1171&q=80');
INSERT INTO ACCOMODATION_IMAGE(ID, IMG_URL) VALUES(3, 'https://images.unsplash.com/photo-1523755231516-e43fd2e8dca5?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80');
INSERT INTO ACCOMODATION_IMAGE(ID, IMG_URL) VALUES(4, 'https://images.unsplash.com/photo-1493663284031-b7e3aefcae8e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80');
INSERT INTO ACCOMODATION_IMAGE(ID, IMG_URL) VALUES(5, 'https://images.unsplash.com/photo-1553444859-788c4b385b13?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1169&q=80');

INSERT INTO ACCOMODATION_ACC_IMAGE(ID_ACC_IMAGE, ID_ACC) VALUES(1, '0001234A');
INSERT INTO ACCOMODATION_ACC_IMAGE(ID_ACC_IMAGE, ID_ACC) VALUES(2, '0001234A');
INSERT INTO ACCOMODATION_ACC_IMAGE(ID_ACC_IMAGE, ID_ACC) VALUES(3, '0001234A');
INSERT INTO ACCOMODATION_ACC_IMAGE(ID_ACC_IMAGE, ID_ACC) VALUES(4, '0001234A');
INSERT INTO ACCOMODATION_ACC_IMAGE(ID_ACC_IMAGE, ID_ACC) VALUES(5, '0001234A');

-- ACCOMODATION NUM.2
-- NÚMERO DE REGISTRO: 0005678B
-- Nº CAMAS: 2
-- Nº BAÑOS: 1
-- Nº INVITADOS: 2
-- Nº HABITACIONES: 1
-- DIRECCIÓN. CALLE ORDOÑO 2, LEÓN
-- CATEGORIA: ESTUDIO
-- SUPERFICIE: 40 METROS CUADRADOS
-- PRECIO: 89.99€ / NOCHE
INSERT INTO ACCOMODATION_LOCATION(ID, LAT, LNG, DIRECTION, CITY, ZIP) VALUES(2, 42.59719114135686, -5.574461714926381, 'Av. de Ordoño, 19', 'León', 24001);
INSERT INTO ACCOMODATION(REG_NUM, ACC_DESCRIPTION, BEDS, NUM_BATHROOMS, NUM_BEDROOMS, 
PRICE_PER_NIGHT, GUESTS, AREA, ID_ACC_CATEGORY, ID_ACC_LOCATION, ID_USER_OWNER) VALUES('0005678B', 'Estudio en el centro de León, a 200m de la Catedral y los monumentos más emblemáticos de la ciudad. El alojamiento ofrece todos las facilidades para que disfrutes de unos días inolvidables en León.', 
2, 1, 1, 35, 2, 89.99, 2, 2, 3);

-- RULES
INSERT INTO ACCOMODATION_ACC_RULE(ID_ACC, ID_ACC_RULE) VALUES('0005678B', 1);
INSERT INTO ACCOMODATION_ACC_RULE(ID_ACC, ID_ACC_RULE) VALUES('0005678B', 2);
INSERT INTO ACCOMODATION_ACC_RULE(ID_ACC, ID_ACC_RULE) VALUES('0005678B', 3);

-- SERVICES
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 1);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 2);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 3);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 4);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 5);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 6);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 8);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 9);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 11);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 13);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 14);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 15);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 19);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 20);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 21);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 23);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 25);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 27);
INSERT INTO ACCOMODATION_ACC_SERVICE(ID_ACC, ID_ACC_SERVICE) VALUES('0005678B', 32);


-- -----------
-- PROMO CODES
-- -----------

-- FIRST PROMO CODE
-- USER: Samuel Cooper
-- ACCOMODATION: 1
-- DISCCOUNT: 10%
INSERT INTO PROMO_CODE(SERIAL_NUM, AMOUNT_PERCENTAGE, DATE_START, DATE_END, ID_USER) 
  VALUES('XCmQOJpyjO', 10, DATE('2022-03-20'), DATE('2022-03-27'), 1);
  
INSERT INTO ACCOMODATION_PROMO_CODE(ID_ACC, ID_PROMO_CODE) VALUES('0001234A', 'XCmQOJpyjO');



-- -----------
-- USER REVIEWS
-- -----------

-- REVIEW DEL USUARIO ID[1]: SAMUEL COOPER AL USUARIO ID[2]: DANA MORENO

INSERT INTO USER_REVIEW(CONTENT, STARS, ID_USER_A, ID_USER_B) VALUES("Usuario de 10! Excelente trato", 5, 1, 2);


-- -----------
-- ACCOMODATION LOCATIONS
-- -----------
INSERT INTO ACCOMODATION_LOCATION(ID, LAT, LNG, DIRECTION, CITY, ZIP) VALUES(3, 42.59637527040337, -5.569699249869098, 'C. la Rúa, 39', 'León', 24003);
INSERT INTO ACCOMODATION_LOCATION(ID, LAT, LNG, DIRECTION, CITY, ZIP) VALUES(4, 42.59328280060388, -5.56556858424137, 'C. Santa Ana, 30', 'León', 24001);
INSERT INTO ACCOMODATION_LOCATION(ID, LAT, LNG, DIRECTION, CITY, ZIP) VALUES(5, 42.58535790970073, -5.558791164868592, 'Av. del Alcalde Miguel Castaño', 'León', 24005);
INSERT INTO ACCOMODATION_LOCATION(ID, LAT, LNG, DIRECTION, CITY, ZIP) VALUES(6, 42.584583860109134, -5.569473392375086, 'Plaza El Parque', 'León', 24005);


-- -----------
-- PAYMENTS
-- -----------

-- PAGOS REALIZADOS AL EFECTUAR UNA RESERVA

INSERT INTO PAYMENT(ID) VALUES(1);
INSERT INTO PAYMENT(ID) VALUES(2);

INSERT INTO PAYMENT_CREDIT_CARD(ID_PAYMENT, CARD_NUMBER) VALUES(1, '342765014567197');
INSERT INTO PAYMENT_PAYPAL(ID_PAYMENT, ACCOUNT_EMAIL) VALUES(2, 'pepe.rodr@gmail.com');


-- -----------
-- BOOKINGS
-- -----------

-- BOOKING 1 - ALOJAMIENTO 1 - PAGADO CON TARJETA DE CRÉDITO

-- BOOKING BILL

INSERT INTO BOOKING(ID, CHECK_IN, CHECK_OUT, GUESTS, AMOUNT, ID_USER, ID_ACCOMODATION, PAYMENT_ID) VALUES(1, DATE('2022-04-01'), DATE('2022-04-03'), 4, 200, 1, '0001234A', 1);


-- -----------
-- SAVED ACCOMODATIONS
-- -----------

INSERT INTO SAVED_ACCOMODATION(ID, ID_USER, ID_ACC) VALUES(1, 1, '0001234A');



-- -----------
-- ACCOMODATION REVIEWS
-- -----------

-- EXCEPTION: HOST USER CANNOT RATE HIS/HER ACCOMODATIONS

INSERT INTO ACCOMODATION_REVIEW(ID, CONTENT, STARS, ID_USER, ID_ACC) VALUES(1, 'El alojamiento está como en las fotos, hemos pasado unos días increíbles', 4, 2, '0001234A');


-- -----------
-- ACCOMODATION REVIEW REPLY
-- -----------

-- RESPUESTA DEL USUARIO HOST DEL ALOJAMIENTO A UNA DE LAS REVIEWS [REVIEW {1}]
INSERT INTO ACCOMODATION_REVIEW_REPLY(ID, CONTENT, ID_ACCOMODATION_REVIEW) VALUES(1, "Gracias por tu comentario!", 1);


-- -----------
-- APPLICATION SEARCHES
-- -----------

INSERT INTO APP_SEARCH(WORD) VALUES("León");
INSERT INTO APP_SEARCH(WORD) VALUES("Madrid");
INSERT INTO APP_SEARCH(WORD) VALUES("Valladolid");
INSERT INTO APP_SEARCH(WORD) VALUES("Avenida Ordoño, León");
INSERT INTO APP_SEARCH(WORD) VALUES("Zaragoza");
INSERT INTO APP_SEARCH(WORD) VALUES("Madrid");
INSERT INTO APP_SEARCH(WORD) VALUES("Avenida San Marcos 3, León");
INSERT INTO APP_SEARCH(WORD) VALUES("León");
INSERT INTO APP_SEARCH(WORD) VALUES("Madrid");
INSERT INTO APP_SEARCH(WORD) VALUES("Paso de la Castellana, Madrid");
INSERT INTO APP_SEARCH(WORD) VALUES("Galicia");

-- -----------
-- SEARCH HISTORY
-- -----------

-- USER ID [1]: SAMUEL COOPER
INSERT INTO USER_SEARCH_HISTORY(ID_USER, ID_SEARCH) VALUES(1, 1);
INSERT INTO USER_SEARCH_HISTORY(ID_USER, ID_SEARCH) VALUES(1, 2);
INSERT INTO USER_SEARCH_HISTORY(ID_USER, ID_SEARCH) VALUES(1, 3);
INSERT INTO USER_SEARCH_HISTORY(ID_USER, ID_SEARCH) VALUES(1, 1);
INSERT INTO USER_SEARCH_HISTORY(ID_USER, ID_SEARCH) VALUES(1, 11);


-- USER ID [6]: MARINA RIOJAS
INSERT INTO USER_SEARCH_HISTORY(ID_USER, ID_SEARCH) VALUES(6, 1);
INSERT INTO USER_SEARCH_HISTORY(ID_USER, ID_SEARCH) VALUES(6, 4);
INSERT INTO USER_SEARCH_HISTORY(ID_USER, ID_SEARCH) VALUES(6, 1);
INSERT INTO USER_SEARCH_HISTORY(ID_USER, ID_SEARCH) VALUES(6, 5);


