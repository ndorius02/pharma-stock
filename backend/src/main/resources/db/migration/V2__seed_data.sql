-- ============================================
-- V2__seed_data.sql
-- Seed data for pharmastock schema
-- ============================================

-- =========================
-- 1) APP_USER
-- =========================
INSERT INTO app_user (id, last_name, first_name, email, password_hash, role, is_active)
VALUES
    (1, 'Dupont',  'Sarah',  'sarah.dupont@pharmastock.be',  '$2a$10$dummyhashsarah.............................', 'ADMIN',   TRUE),
    (2, 'Lambert', 'Thomas', 'thomas.lambert@pharmastock.be','$2a$10$dummyhashthomas............................', 'MANAGER', TRUE),
    (3, 'Martin',  'Inès',   'ines.martin@pharmastock.be',   '$2a$10$dummyhashines..............................',  'STAFF',   TRUE),
    (4, 'Nguyen',  'Minh',   'minh.nguyen@pharmastock.be',  '$2a$10$dummyhashminh..............................',  'STAFF',   TRUE),
    (5, 'Benoit',  'Julie',  'julie.benoit@pharmastock.be', '$2a$10$dummyhashjulie.............................', 'STAFF',   TRUE);

-- =========================
-- 2) CATEGORY
-- =========================
INSERT INTO category (id, name, description, is_active)
VALUES
    (1, 'Analgésiques', 'Douleurs et fièvre (paracétamol, ibuprofène, etc.)', TRUE),
    (2, 'Antibiotiques', 'Traitements antibactériens sur prescription', TRUE),
    (3, 'Antiallergiques', 'Rhinites, allergies saisonnières', TRUE),
    (4, 'Vitamines', 'Compléments alimentaires (vitamines, minéraux)', TRUE),
    (5, 'Digestif', 'Reflux, nausées, constipation, diarrhée', TRUE),
    (6, 'Dermatologie', 'Soins de la peau : crèmes, antiseptiques, etc.', TRUE);

-- =========================
-- 3) LOCATION
-- =========================
INSERT INTO location (id, name, description, is_active)
VALUES
    (1, 'Magasin A - Étagère 1', 'Zone grand public, niveau yeux', TRUE),
    (2, 'Magasin A - Étagère 2', 'Zone grand public, niveau bas', TRUE),
    (3, 'Réserve - Armoire sécurisée', 'Produits sensibles / prescription', TRUE),
    (4, 'Réserve - Frigo 2-8°C', 'Chaîne du froid', TRUE),
    (5, 'Back-office - Tiroir 3', 'Petits formats / échantillons', TRUE),
    (6, 'Quarantaine', 'Produits en attente / retour / contrôle', TRUE);

-- =========================
-- 4) SUPPLIER
-- =========================
INSERT INTO supplier (id, name, vat_number, phone, email, street, postal_code, locality, is_active)
VALUES
    (1, 'BENU Distribution',        'BE0123456789', '+32 2 555 11 22', 'contact@benu-distribution.be', 'Rue de l''Industrie 10', '1000', 'Bruxelles', TRUE),
    (2, 'PharmaLogistics Belgium',  'BE0234567891', '+32 81 222 333',  'sales@pharmalogistics.be',     'Avenue des Ardennes 44', '5000', 'Namur',     TRUE),
    (3, 'MediSupply Europe',        'BE0345678912', '+32 71 444 555',  'info@medisupply.eu',           'Chaussée de Charleroi 8','6000', 'Charleroi', TRUE),
    (4, 'HealthSource',             'BE0456789123', '+32 10 777 888',  'orders@healthsource.be',       'Rue de Louvain 120',     '1300', 'Wavre',     TRUE),
    (5, 'BioCare Wholesale',         'BE0567891234', '+32 10 111 222',  'support@biocare.be',           'Boulevard Central 5',     '1348', 'Louvain-la-Neuve', TRUE);

-- =========================
-- 5) CUSTOMER
-- =========================
INSERT INTO customer (id, name, phone, email, street, postal_code, locality, is_active)
VALUES
    (1, 'Clinique Vétérinaire de Namur', '+32 81 123 456', 'contact@vetnamur.be', 'Rue des Vétos 7', '5000', 'Namur', TRUE),
    (2, 'Maison de Repos Les Tilleuls',  '+32 2 888 777',  'pharma@tilleuls.be', 'Avenue du Parc 21', '1050', 'Ixelles', TRUE),
    (3, 'Cabinet Dr. Moreau',            '+32 71 222 111', 'secretariat@drmoreau.be', 'Rue de la Santé 12', '6000', 'Charleroi', TRUE),
    (4, 'ASBL Solidaris - Antenne',      '+32 10 333 999', 'antenne@solidaris.be', 'Place Centrale 1', '1300', 'Wavre', TRUE),
    (5, 'Centre Médical Saint-Jean',     '+32 2 555 900',  'admin@cmsj.be', 'Boulevard du Nord 15', '1000', 'Bruxelles', TRUE),
    (6, 'Particulier - Client Comptoir', NULL, 'client.comptoir@exemple.be', NULL, NULL, 'Namur', TRUE);

-- =========================
-- 6) MEDICATION
-- =========================
INSERT INTO medication
(id, name, sku, barcode, description, unit, min_stock, current_stock, is_active, category_id, location_id)
VALUES
    (1,  'Paracétamol 500mg (20 cps)',  'SKU-PARA-500-20', '5410000000011', 'Antalgique et antipyrétique', 'box',  30, 120, TRUE, 1, 1),
    (2,  'Ibuprofène 400mg (30 cps)',   'SKU-IBU-400-30',  '5410000000028', 'Anti-inflammatoire non stéroïdien', 'box',  20,  55, TRUE, 1, 2),
    (3,  'Amoxicilline 500mg (12 gél)', 'SKU-AMOX-500-12', '5410000000035', 'Antibiotique (prescription)', 'box',  10,  18, TRUE, 2, 3),
    (4,  'Cétirizine 10mg (30 cps)',    'SKU-CETI-10-30',  '5410000000042', 'Antihistaminique', 'box',  15,  40, TRUE, 3, 1),
    (5,  'Vitamine C 1000mg (20)',      'SKU-VITC-1000-20','5410000000059', 'Comprimés effervescents', 'box',  25,  70, TRUE, 4, 2),
    (6,  'Magnésium + B6 (60)',         'SKU-MAGB6-60',    '5410000000066', 'Complément magnésium', 'box',  20,  22, TRUE, 4, 5),
    (7,  'Oméprazole 20mg (14 gél)',    'SKU-OME-20-14',   '5410000000073', 'Reflux gastro-oesophagien', 'box',  10,  12, TRUE, 5, 1),
    (8,  'Lopéramide 2mg (10 gél)',     'SKU-LOPE-2-10',   '5410000000080', 'Anti-diarrhéique', 'box',  10,  35, TRUE, 5, 2),
    (9,  'Crème hydrocortisone 1%',     'SKU-HYDRO-1-30G', '5410000000097', 'Démangeaisons / irritations', 'tube',  8,  16, TRUE, 6, 2),
    (10, 'Solution antiseptique 250ml', 'SKU-ANTIS-250',   '5410000000103', 'Antiseptique cutané', 'bottle', 12,  28, TRUE, 6, 5),
    (11, 'Insuline (flacon)',           'SKU-INSU-FRIGO',  '5410000000110', 'Produit chaîne du froid', 'bottle',  5,   6, TRUE, 2, 4),
    (12, 'Paracétamol 1g (8 sachets)',  'SKU-PARA-1G-8',   '5410000000127', 'Douleur/fièvre, format sachet', 'box',  20,  10, TRUE, 1, 6);

-- =========================
-- 7) MEDICATION_SUPPLIER
-- (uq_supplier_sku_per_supplier UNIQUE(supplier_id, supplier_sku))
-- =========================
INSERT INTO medication_supplier
(medication_id, supplier_id, purchase_price, supplier_sku, is_primary)
VALUES
    (1, 1, 1.20, 'BENU-PARA-500-20', TRUE),
    (1, 2, 1.10, 'PLB-PARA-500-20',  FALSE),

    (2, 2, 2.10, 'PLB-IBU-400-30',   TRUE),
    (2, 3, 2.25, 'MSE-IBU-400-30',   FALSE),

    (3, 1, 6.80, 'BENU-AMOX-500-12', TRUE),
    (3, 4, 6.95, 'HS-AMOX-500-12',   FALSE),

    (4, 3, 1.95, 'MSE-CETI-10-30',   TRUE),

    (5, 5, 2.40, 'BIO-VITC-1000-20', TRUE),
    (6, 5, 3.10, 'BIO-MAGB6-60',     TRUE),

    (7, 2, 3.80, 'PLB-OME-20-14',    TRUE),
    (8, 3, 1.30, 'MSE-LOPE-2-10',    TRUE),

    (9, 4, 2.75, 'HS-HYDRO-1-30G',   TRUE),
    (10,4, 1.90, 'HS-ANTIS-250',     TRUE),

    (11,1, 18.00,'BENU-INSU-FRIGO',  TRUE),

    (12,2, 0.95, 'PLB-PARA-1G-8',    TRUE);

-- =========================
-- 8) STOCK_MOVEMENT
-- Règles :
-- - quantity > 0
-- - movement_type IN/OUT/ADJUST
-- - ADJUST => supplier_id & customer_id MUST be NULL
-- =========================
INSERT INTO stock_movement
(id, medication_id, user_id, movement_type, quantity, note, supplier_id, customer_id)
VALUES
    (1,  1, 3, 'IN',     50, 'Réassort BENU',         1, NULL),
    (2,  2, 3, 'IN',     30, 'Réassort PharmaLogistics', 2, NULL),
    (3,  3, 2, 'IN',     10, 'Réception antibiotiques', 1, NULL),

    (4,  1, 4, 'OUT',    15, 'Vente comptoir',        NULL, 6),
    (5,  4, 4, 'OUT',     5, 'Commande clinique',      NULL, 1),
    (6,  7, 5, 'OUT',     2, 'Commande cabinet',       NULL, 3),

    (7,  6, 2, 'ADJUST',  3, 'Casse / perte inventaire', NULL, NULL),
    (8, 12, 2, 'ADJUST',  5, 'Correction stock (audit)', NULL, NULL);

-- =========================
-- Recalage des séquences (si IDs explicites)
-- =========================
SELECT setval(pg_get_serial_sequence('app_user','id'),        (SELECT MAX(id) FROM app_user));
SELECT setval(pg_get_serial_sequence('category','id'),        (SELECT MAX(id) FROM category));
SELECT setval(pg_get_serial_sequence('location','id'),        (SELECT MAX(id) FROM location));
SELECT setval(pg_get_serial_sequence('supplier','id'),        (SELECT MAX(id) FROM supplier));
SELECT setval(pg_get_serial_sequence('customer','id'),        (SELECT MAX(id) FROM customer));
SELECT setval(pg_get_serial_sequence('medication','id'),      (SELECT MAX(id) FROM medication));
SELECT setval(pg_get_serial_sequence('stock_movement','id'),  (SELECT MAX(id) FROM stock_movement));
