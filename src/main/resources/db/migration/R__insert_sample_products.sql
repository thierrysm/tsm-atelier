-- Repetable Migration for Seeding Sample Products, Collections, and Promotions
-- This script runs after all versioned migrations and re-runs if its checksum changes.

-- Limpa os dados existentes em ordem de dependência para evitar erros de chave estrangeira
DELETE FROM product_images;
DELETE FROM product_variants;
DELETE FROM product_materials;
DELETE FROM product_care_instructions;
DELETE FROM products;
DELETE FROM collections;

-- 1. INSERE AS COLEÇÕES PRIMEIRO
INSERT INTO collections (id, name, slug, description, is_active, created_at, updated_at, "version") VALUES
('c01ec1a0-1111-4444-8888-000000000001', 'Coleção Verão 2025', 'colecao-verao-2025', 'Peças leves e fluidas, inspiradas na brisa do mar e no calor do sol.', true, NOW(), NOW(), 0),
('c01ec1a0-2222-4444-8888-000000000002', 'Clássicos Atemporais', 'classicos-atemporais', 'Designs icônicos e versáteis que transcendem as estações.', true, NOW(), NOW(), 0),
('c01ec1a0-3333-4444-8888-000000000003', 'Festa & Gala', 'festa-&-gala', 'Criações sofisticadas para eventos e noites inesquecíveis.', true, NOW(), NOW(), 0);

-- 2. INSERE OS PRODUTOS, AGORA COM collection_id e promotional_price

-- PRODUTO 1 (Em promoção, na Coleção Verão 2025)
INSERT INTO products (id, name, description, price, promotional_price, sku, category, is_active, collection_id, created_at, updated_at, "version") VALUES
('a1a1a1a1-1111-1111-1111-000000000001', 'Vestido de Seda ''Luar''', 'Um vestido etéreo de seda pura, perfeito para noites especiais.', 899.90, 750.00, 'VEST-LUAR', 'VESTIDOS', true, 'c01ec1a0-3333-4444-8888-000000000003', NOW(), NOW(), 0);
INSERT INTO product_variants (id, sku, size, color, quantity_in_stock, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'VEST-LUAR-P-PRETO', 'P', 'Preto Meia-Noite', 3, 'a1a1a1a1-1111-1111-1111-000000000001', NOW(), NOW(), 0),
(gen_random_uuid(), 'VEST-LUAR-M-PRETO', 'M', 'Preto Meia-Noite', 2, 'a1a1a1a1-1111-1111-1111-000000000001', NOW(), NOW(), 0);
INSERT INTO product_images (id, image_url, alt_text, is_primary, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'https://example.com/images/vestido-luar-1.jpg', 'Frente do Vestido Luar', true, 'a1a1a1a1-1111-1111-1111-000000000001', NOW(), NOW(), 0);
INSERT INTO product_materials (product_id, material) VALUES ('a1a1a1a1-1111-1111-1111-000000000001', 'Seda Pura');
INSERT INTO product_care_instructions (product_id, instruction) VALUES ('a1a1a1a1-1111-1111-1111-000000000001', 'Lavar a seco');

-- PRODUTO 2 (Preço normal, na Coleção Verão 2025)
INSERT INTO products (id, name, description, price, promotional_price, sku, category, is_active, collection_id, created_at, updated_at, "version") VALUES
('a1a1a1a1-1111-1111-1111-000000000002', 'Blusa de Algodão ''Brisa''', 'Leve e confortável, ideal para o dia a dia.', 249.90, NULL, 'BLUSA-BRISA', 'BLUSAS', true, 'c01ec1a0-1111-4444-8888-000000000001', NOW(), NOW(), 0);
INSERT INTO product_variants (id, sku, size, color, quantity_in_stock, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'BLUSA-BRISA-M-BRANCO', 'M', 'Branco Neve', 10, 'a1a1a1a1-1111-1111-1111-000000000002', NOW(), NOW(), 0),
(gen_random_uuid(), 'BLUSA-BRISA-G-BRANCO', 'G', 'Branco Neve', 8, 'a1a1a1a1-1111-1111-1111-000000000002', NOW(), NOW(), 0);
INSERT INTO product_images (id, image_url, alt_text, is_primary, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'https://example.com/images/blusa-brisa-1.jpg', 'Blusa de Algodão Brisa', true, 'a1a1a1a1-1111-1111-1111-000000000002', NOW(), NOW(), 0);
INSERT INTO product_materials (product_id, material) VALUES ('a1a1a1a1-1111-1111-1111-000000000002', 'Algodão Pima');
INSERT INTO product_care_instructions (product_id, instruction) VALUES ('a1a1a1a1-1111-1111-1111-000000000002', 'Lavar à máquina com água fria');

-- PRODUTO 3 (Preço normal, na Coleção Clássicos Atemporais)
INSERT INTO products (id, name, description, price, promotional_price, sku, category, is_active, collection_id, created_at, updated_at, "version") VALUES
('a1a1a1a1-1111-1111-1111-000000000003', 'Calça Alfaiataria ''Elara''', 'Corte clássico e tecido nobre para um look sofisticado.', 599.90, NULL, 'CALCA-ELARA', 'CALCAS', true, 'c01ec1a0-2222-4444-8888-000000000002', NOW(), NOW(), 0);
INSERT INTO product_variants (id, sku, size, color, quantity_in_stock, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'CALCA-ELARA-38-BEGE', 'P', 'Bege', 5, 'a1a1a1a1-1111-1111-1111-000000000003', NOW(), NOW(), 0),
(gen_random_uuid(), 'CALCA-ELARA-40-BEGE', 'M', 'Bege', 5, 'a1a1a1a1-1111-1111-1111-000000000003', NOW(), NOW(), 0);
INSERT INTO product_images (id, image_url, alt_text, is_primary, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'https://example.com/images/calca-elara-1.jpg', 'Calça Alfaiataria Elara', true, 'a1a1a1a1-1111-1111-1111-000000000003', NOW(), NOW(), 0);
INSERT INTO product_materials (product_id, material) VALUES ('a1a1a1a1-1111-1111-1111-000000000003', 'Lã Fria');
INSERT INTO product_care_instructions (product_id, instruction) VALUES ('a1a1a1a1-1111-1111-1111-000000000003', 'Lavar a seco');

-- PRODUTO 4 (Sem coleção específica)
INSERT INTO products (id, name, description, price, promotional_price, sku, category, is_active, collection_id, created_at, updated_at, "version") VALUES
('a1a1a1a1-1111-1111-1111-000000000004', 'Saia Midi ''Serena''', 'Saia plissada com movimento e elegância.', 450.00, NULL, 'SAIA-SERENA', 'SAIAS', true, NULL, NOW(), NOW(), 0);
INSERT INTO product_variants (id, sku, size, color, quantity_in_stock, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'SAIA-SERENA-M-VERDE', 'M', 'Verde Esmeralda', 4, 'a1a1a1a1-1111-1111-1111-000000000004', NOW(), NOW(), 0);
INSERT INTO product_images (id, image_url, alt_text, is_primary, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'https://example.com/images/saia-serena-1.jpg', 'Saia Midi Serena', true, 'a1a1a1a1-1111-1111-1111-000000000004', NOW(), NOW(), 0);
INSERT INTO product_materials (product_id, material) VALUES ('a1a1a1a1-1111-1111-1111-000000000004', 'Crepe');
INSERT INTO product_care_instructions (product_id, instruction) VALUES ('a1a1a1a1-1111-1111-1111-000000000004', 'Lavar à mão');

-- PRODUTO 5 (Em promoção, Clássicos Atemporais)
INSERT INTO products (id, name, description, price, promotional_price, sku, category, is_active, collection_id, created_at, updated_at, "version") VALUES
('a1a1a1a1-1111-1111-1111-000000000011', 'Trench Coat ''Imperial''', 'Um trench coat clássico, feito com gabardine resistente à água. Uma peça para a vida toda.', 1890.00, 1500.00, 'TCOAT-IMP', 'ACESSORIOS', true, 'c01ec1a0-2222-4444-8888-000000000002', NOW(), NOW(), 0);
INSERT INTO product_variants (id, sku, size, color, quantity_in_stock, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'TCOAT-IMP-M-BEGE', 'M', 'Bege Clássico', 5, 'a1a1a1a1-1111-1111-1111-000000000011', NOW(), NOW(), 0);
INSERT INTO product_images (id, image_url, alt_text, is_primary, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'https://example.com/images/trench-coat-imperial-1.jpg', 'Vista do Trench Coat Imperial', true, 'a1a1a1a1-1111-1111-1111-000000000011', NOW(), NOW(), 0);
INSERT INTO product_materials (product_id, material) VALUES ('a1a1a1a1-1111-1111-1111-000000000011', 'Gabardine');
INSERT INTO product_care_instructions (product_id, instruction) VALUES ('a1a1a1a1-1111-1111-1111-000000000011', 'Lavar a seco');

-- PRODUTO 6 (Festa & Gala)
INSERT INTO products (id, name, description, price, promotional_price, sku, category, is_active, collection_id, created_at, updated_at, "version") VALUES
('a1a1a1a1-1111-1111-1111-000000000012', 'Vestido de Gala ''Estrela''', 'Vestido longo bordado com cristais, com uma cauda sutil.', 2500.00, NULL, 'VEST-ESTRELA', 'VESTIDOS', true, 'c01ec1a0-3333-4444-8888-000000000003', NOW(), NOW(), 0);
INSERT INTO product_variants (id, sku, size, color, quantity_in_stock, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'VEST-ESTRELA-38-PRATA', 'P', 'Prata', 2, 'a1a1a1a1-1111-1111-1111-000000000012', NOW(), NOW(), 0);
INSERT INTO product_images (id, image_url, alt_text, is_primary, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'https://example.com/images/vestido-estrela-1.jpg', 'Vestido de Gala Estrela', true, 'a1a1a1a1-1111-1111-1111-000000000012', NOW(), NOW(), 0);
INSERT INTO product_materials (product_id, material) VALUES ('a1a1a1a1-1111-1111-1111-000000000012', 'Tule Bordado'), ('a1a1a1a1-1111-1111-1111-000000000012', 'Cristais');
INSERT INTO product_care_instructions (product_id, instruction) VALUES ('a1a1a1a1-1111-1111-1111-000000000012', 'Limpeza especializada');

-- PRODUTO 7 (Coleção Verão 2025)
INSERT INTO products (id, name, description, price, promotional_price, sku, category, is_active, collection_id, created_at, updated_at, "version") VALUES
('a1a1a1a1-1111-1111-1111-000000000013', 'Short de Linho ''Maré''', 'Short de cintura alta com caimento perfeito para dias quentes.', 280.00, NULL, 'SHORT-MARE', 'CALCAS', true, 'c01ec1a0-1111-4444-8888-000000000001', NOW(), NOW(), 0);
INSERT INTO product_variants (id, sku, size, color, quantity_in_stock, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'SHORT-MARE-P-AREIA', 'P', 'Areia', 8, 'a1a1a1a1-1111-1111-1111-000000000013', NOW(), NOW(), 0),
(gen_random_uuid(), 'SHORT-MARE-M-AREIA', 'M', 'Areia', 10, 'a1a1a1a1-1111-1111-1111-000000000013', NOW(), NOW(), 0);
INSERT INTO product_images (id, image_url, alt_text, is_primary, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'https://example.com/images/short-mare-1.jpg', 'Short de Linho Maré', true, 'a1a1a1a1-1111-1111-1111-000000000013', NOW(), NOW(), 0);
INSERT INTO product_materials (product_id, material) VALUES ('a1a1a1a1-1111-1111-1111-000000000013', 'Linho');
INSERT INTO product_care_instructions (product_id, instruction) VALUES ('a1a1a1a1-1111-1111-1111-000000000013', 'Lavar à mão');

-- PRODUTO 8 (Clássicos Atemporais)
INSERT INTO products (id, name, description, price, promotional_price, sku, category, is_active, collection_id, created_at, updated_at, "version") VALUES
('a1a1a1a1-1111-1111-1111-000000000014', 'Suéter de Cashmere ''Inverno''', 'Suéter de cashmere puro, toque extremamente macio.', 1200.00, NULL, 'SUETER-INV', 'BLUSAS', true, 'c01ec1a0-2222-4444-8888-000000000002', NOW(), NOW(), 0);
INSERT INTO product_variants (id, sku, size, color, quantity_in_stock, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'SUETER-INV-M-MARFIM', 'M', 'Marfim', 4, 'a1a1a1a1-1111-1111-1111-000000000014', NOW(), NOW(), 0);
INSERT INTO product_images (id, image_url, alt_text, is_primary, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'https://example.com/images/sueter-inverno-1.jpg', 'Suéter de Cashmere Inverno', true, 'a1a1a1a1-1111-1111-1111-000000000014', NOW(), NOW(), 0);
INSERT INTO product_materials (product_id, material) VALUES ('a1a1a1a1-1111-1111-1111-000000000014', 'Cashmere');
INSERT INTO product_care_instructions (product_id, instruction) VALUES ('a1a1a1a1-1111-1111-1111-000000000014', 'Lavar a seco');

-- PRODUTO 9 (Sem coleção, em promoção)
INSERT INTO products (id, name, description, price, promotional_price, sku, category, is_active, collection_id, created_at, updated_at, "version") VALUES
('a1a1a1a1-1111-1111-1111-000000000015', 'Cinto de Couro ''Fivela''', 'Cinto de couro legítimo com fivela artesanal dourada.', 350.00, 299.90, 'CINTO-FIV', 'ACESSORIOS', true, NULL, NOW(), NOW(), 0);
INSERT INTO product_variants (id, sku, size, color, quantity_in_stock, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'CINTO-FIV-U-CAFE', 'G', 'Café', 20, 'a1a1a1a1-1111-1111-1111-000000000015', NOW(), NOW(), 0);
INSERT INTO product_images (id, image_url, alt_text, is_primary, product_id, created_at, updated_at, "version") VALUES
(gen_random_uuid(), 'https://example.com/images/cinto-fivela-1.jpg', 'Cinto de Couro Fivela', true, 'a1a1a1a1-1111-1111-1111-000000000015', NOW(), NOW(), 0);
INSERT INTO product_materials (product_id, material) VALUES ('a1a1a1a1-1111-1111-1111-000000000015', 'Couro Legítimo');
INSERT INTO product_care_instructions (product_id, instruction) VALUES ('a1a1a1a1-1111-1111-1111-000000000015', 'Limpar com pano úmido');