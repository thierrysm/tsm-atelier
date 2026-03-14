-- Collection Images
INSERT INTO collection_images (id, url, file_name)
VALUES ('7b6a1b2c-3d4e-4f5a-8b9c-0d1e2f3a4b5c', 'https://example.com/images/winter-2025.jpg', 102456);
INSERT INTO collection_images (id, url, file_name)
VALUES ('8c7b2c3d-4e5f-5a6b-9c0d-1e2f3a4b5c6d', 'https://example.com/images/summer-2025.jpg', 204812);

-- Collections
INSERT INTO collections (id, name, description, year, image_id, created_at, updated_at)
VALUES ('1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', 'Inverno Minimalista 2025', 'A melhor coleção do momento', 2025, '7b6a1b2c-3d4e-4f5a-8b9c-0d1e2f3a4b5c', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO collections (id, name, description, year, image_id, created_at, updated_at)
VALUES ('2b3c4d5e-6f7a-8b9c-0d1e-2f3a4b5c6d7e', 'Verão Vibrante 2025', 'Aproveite essa coleção do verão', 2025, '8c7b2c3d-4e5f-5a6b-9c0d-1e2f3a4b5c6d', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Products
INSERT INTO products (id, name, alias, description, price, material, collection_id, active, created_at, updated_at)
VALUES ('3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f', 'Camiseta Algodão Orgânico', 'camiseta-algodao-organico', 'Camiseta básica feita com algodão 100% orgânico.', 89.90, 'Algodão Orgânico', '2b3c4d5e-6f7a-8b9c-0d1e-2f3a4b5c6d7e', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO products (id, name, alias, description, price, material, collection_id, active, created_at, updated_at)
VALUES ('4d5e6f7a-8b9c-0d1e-2f3a-4b5c6d7e8f9a', 'Blazer de Lã', 'blazer-de-la', 'Blazer estruturado em lã para dias frios.', 450.00, 'Lã', '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Product Cares (Product 1)
INSERT INTO product_cares (id, product_id, instruction, display_order)
VALUES (gen_random_uuid(), '3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f', 'Lavar à máquina em ciclo delicado', 1);
INSERT INTO product_cares (id, product_id, instruction, display_order)
VALUES (gen_random_uuid(), '3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f', 'Não usar alvejante', 2);

-- Product Colors (Product 1)
INSERT INTO product_colors (id, product_id, name, hex_code, stock)
VALUES ('5e6f7a8b-9c0d-1e2f-3a4b-5c6d7e8f9a0b', '3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f', 'Branco', '#FFFFFF', 50);
INSERT INTO product_colors (id, product_id, name, hex_code, stock)
VALUES ('6f7a8b9c-0d1e-2f3a-4b5c-6d7e8f9a0b1c', '3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f', 'Preto', '#000000', 30);

-- Product Images (Product 1 - Color Branco)
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (gen_random_uuid(), '5e6f7a8b-9c0d-1e2f-3a4b-5c6d7e8f9a0b', 'https://example.com/images/shirt-white-front.jpg', 'shirt-white-front.jpg', 1, true);

-- Product Variants (Product 1 - Color Branco)
INSERT INTO product_variants (id, product_color_id, product_size, stock, version)
VALUES (gen_random_uuid(), '5e6f7a8b-9c0d-1e2f-3a4b-5c6d7e8f9a0b', 'P', 20, 1);
INSERT INTO product_variants (id, product_color_id, product_size, stock, version)
VALUES (gen_random_uuid(), '5e6f7a8b-9c0d-1e2f-3a4b-5c6d7e8f9a0b', 'M', 30, 1);
