-- =============================================
-- V100: Dados mockados para desenvolvimento
-- =============================================

-- Usuário ADMIN
-- Email: admin@tsmatelier.com | Senha: admin123
INSERT INTO users (id, email, password, role, status, email_verified, created_at, updated_at)
VALUES (
    'a1b2c3d4-e5f6-7890-abcd-ef1234567890',
    'admin@tsmatelier.com',
    '$2a$10$jnTZ5W05BUbgaykRKhU5A.YadGou5WnhdEj0xZ7AEdSMRonSEgc7C',
    'ADMIN',
    'ACTIVE',
    true,
    NOW(),
    NOW()
);

-- Usuário CLIENT
-- Email: cliente@email.com | Senha: client123
INSERT INTO users (id, email, password, role, status, email_verified, created_at, updated_at)
VALUES (
    'b2c3d4e5-f6a7-8901-bcde-f12345678901',
    'cliente@email.com',
    '$2a$10$jIplGCxduLGjD/XDx8F4TudGVSyAWfb5aoletRzEev4I6tOFkxYwy',
    'CLIENT',
    'ACTIVE',
    true,
    NOW(),
    NOW()
);

-- Perfil do cliente
INSERT INTO client_profiles (first_name, last_name, cpf, phone, user_id, created_at, updated_at)
VALUES (
    'Maria',
    'Silva',
    '12345678901',
    '11999999999',
    'b2c3d4e5-f6a7-8901-bcde-f12345678901',
    NOW(),
    NOW()
);
-- ─── PRODUTOS ─────────────────────────────────────────────────

-- ── FEMININO / VESTIDOS ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (1, 'Vestido Ampla Estampado', 'vestido-ampla-estampado', 'Vestido Ampla Estampado - peça exclusiva da coleção TSM Atelier.', 599.00, 'DRESS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (2, 'Vestido Curto Com Babado', 'vestido-curto-com-babado', 'Vestido Curto Com Babado - peça exclusiva da coleção TSM Atelier.', 599.00, 'DRESS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (3, 'Vestido Drapeado Gola Alta', 'vestido-drapeado-gola-alta', 'Vestido Drapeado Gola Alta - peça exclusiva da coleção TSM Atelier.', 599.00, 'DRESS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (4, 'Vestido Longo Acetinado Estampado', 'vestido-longo-acetinado-estampado', 'Vestido Longo Acetinado Estampado - peça exclusiva da coleção TSM Atelier.', 599.00, 'DRESS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (5, 'Vestido Longo Halter', 'vestido-longo-halter', 'Vestido Longo Halter - peça exclusiva da coleção TSM Atelier.', 599.00, 'DRESS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (6, 'Vestido Midi Com Cinto', 'vestido-midi-com-cinto', 'Vestido Midi Com Cinto - peça exclusiva da coleção TSM Atelier.', 599.00, 'DRESS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / CALCAS ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (7, 'Calça Acetinada Estampada', 'calca-acetinada-estampada', 'Calça Acetinada Estampada - peça exclusiva da coleção TSM Atelier.', 599.00, 'PANTS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (8, 'Calça Com Pregas', 'calca-com-pregas', 'Calça Com Pregas - peça exclusiva da coleção TSM Atelier.', 599.00, 'PANTS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (9, 'Calça Reta Zw Collection', 'calca-reta-zw-collection', 'Calça Reta Zw Collection - peça exclusiva da coleção TSM Atelier.', 599.00, 'PANTS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (10, 'Calça Xadrez Com Tiras', 'calca-xadrez-com-tiras', 'Calça Xadrez Com Tiras - peça exclusiva da coleção TSM Atelier.', 599.00, 'PANTS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / CAMISAS_BLUSAS ──────────────────

INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (11, 'Camisa Acetinada Com Botões Dourados', 'camisa-acetinada-com-botoes-dourados', 'Camisa Acetinada Com Botões Dourados - peça exclusiva da coleção TSM Atelier.', 599.00, 'SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (12, 'Camisa Gasa Oversize Com Bolso', 'camisa-gasa-oversize-com-bolso', 'Camisa Gasa Oversize Com Bolso - peça exclusiva da coleção TSM Atelier.', 599.00, 'SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (13, 'Camisa Listrada Com Laço', 'camisa-listrada-com-laco', 'Camisa Listrada Com Laço - peça exclusiva da coleção TSM Atelier.', 599.00, 'SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (14, 'Camisa Listrada Cruzada', 'camisa-listrada-cruzada', 'Camisa Listrada Cruzada - peça exclusiva da coleção TSM Atelier.', 599.00, 'SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (15, 'Casaco De Moletom Com Zíper', 'casaco-de-moletom-com-ziper', 'Casaco De Moletom Com Zíper - peça exclusiva da coleção TSM Atelier.', 599.00, 'SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (16, 'Moletom Com Efeito De Lavagem Don Clemente', 'moletom-com-efeito-de-lavagem-don-clemente', 'Moletom Com Efeito De Lavagem Don Clemente - peça exclusiva da coleção TSM Atelier.', 599.00, 'SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (17, 'Moletom Com Mensagem E Capuz', 'moletom-com-mensagem-e-capuz', 'Moletom Com Mensagem E Capuz - peça exclusiva da coleção TSM Atelier.', 599.00, 'SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (18, 'Moletom Com Mensagem Em Contraste', 'moletom-com-mensagem-em-contraste', 'Moletom Com Mensagem Em Contraste - peça exclusiva da coleção TSM Atelier.', 599.00, 'SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / CAMISETAS ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (19, 'Camiseta Algodão 100% Botões', 'camiseta-algodao-100-botoes', 'Camiseta Algodão 100% Botões - peça exclusiva da coleção TSM Atelier.', 599.00, 'T_SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (20, 'Camiseta Algodão 100% Botões Hover', 'camiseta-algodao-100-botoes-hover', 'Camiseta Algodão 100% Botões Hover - peça exclusiva da coleção TSM Atelier.', 599.00, 'T_SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (21, 'Camiseta Com Texto Em Contraste', 'camiseta-com-texto-em-contraste', 'Camiseta Com Texto Em Contraste - peça exclusiva da coleção TSM Atelier.', 599.00, 'T_SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (22, 'Camiseta Interlock De Manga Curta', 'camiseta-interlock-de-manga-curta', 'Camiseta Interlock De Manga Curta - peça exclusiva da coleção TSM Atelier.', 599.00, 'T_SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (23, 'Camiseta Listrada Com Texto', 'camiseta-listrada-com-texto', 'Camiseta Listrada Com Texto - peça exclusiva da coleção TSM Atelier.', 599.00, 'T_SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / CASACOS ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (24, 'Casaco Com Lã', 'casaco-com-la', 'Casaco Com Lã - peça exclusiva da coleção TSM Atelier.', 599.00, 'COAT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (25, 'Casaco Curto De Lã', 'casaco-curto-de-la', 'Casaco Curto De Lã - peça exclusiva da coleção TSM Atelier.', 599.00, 'COAT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (26, 'Casaco Curto De Pelo Sintético', 'casaco-curto-de-pelo-sintetico', 'Casaco Curto De Pelo Sintético - peça exclusiva da coleção TSM Atelier.', 599.00, 'COAT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (27, 'Casaco De Mistura De Lã', 'casaco-de-mistura-de-la', 'Casaco De Mistura De Lã - peça exclusiva da coleção TSM Atelier.', 599.00, 'COAT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (28, 'Casaco Lã Zw', 'casaco-la-zw', 'Casaco Lã Zw - peça exclusiva da coleção TSM Atelier.', 599.00, 'COAT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (29, 'Casaco Ombreiras Soft', 'casaco-ombreiras-soft', 'Casaco Ombreiras Soft - peça exclusiva da coleção TSM Atelier.', 599.00, 'COAT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / JAQUETAS ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (30, 'Casaco De Plumas Curto', 'casaco-de-plumas-curto', 'Casaco De Plumas Curto - peça exclusiva da coleção TSM Atelier.', 599.00, 'JACKET', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (31, 'Jaqueta Com Efeito Suede De Gola Alta', 'jaqueta-com-efeito-suede-de-gola-alta', 'Jaqueta Com Efeito Suede De Gola Alta - peça exclusiva da coleção TSM Atelier.', 599.00, 'JACKET', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (32, 'Jaqueta De Poliuretano', 'jaqueta-de-poliuretano', 'Jaqueta De Poliuretano - peça exclusiva da coleção TSM Atelier.', 599.00, 'JACKET', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (33, 'Jaqueta De Veludo Cotelê Com Contraste', 'jaqueta-de-veludo-cotele-com-contraste', 'Jaqueta De Veludo Cotelê Com Contraste - peça exclusiva da coleção TSM Atelier.', 599.00, 'JACKET', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (34, 'Jaqueta Reversível Dupla Face', 'jaqueta-reversivel-dupla-face', 'Jaqueta Reversível Dupla Face - peça exclusiva da coleção TSM Atelier.', 599.00, 'JACKET', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / JEANS ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (35, 'Jeans Trf Skinny Cintura Alta', 'jeans-trf-skinny-cintura-alta', 'Jeans Trf Skinny Cintura Alta - peça exclusiva da coleção TSM Atelier.', 599.00, 'JEANS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (36, 'Jeans Z.01 Mom Fit Cintura Alta Com Vinco Passado', 'jeans-z01-mom-fit-cintura-alta-com-vinco-passado', 'Jeans Z.01 Mom Fit Cintura Alta Com Vinco Passado - peça exclusiva da coleção TSM Atelier.', 599.00, 'JEANS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (37, 'Jeans Z1975 Barrel Cintura Média', 'jeans-z1975-barrel-cintura-media', 'Jeans Z1975 Barrel Cintura Média - peça exclusiva da coleção TSM Atelier.', 599.00, 'JEANS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (38, 'Jeans Z1975 Cropped De Cintura Média', 'jeans-z1975-cropped-de-cintura-media', 'Jeans Z1975 Cropped De Cintura Média - peça exclusiva da coleção TSM Atelier.', 599.00, 'JEANS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (39, 'Jeans Zw Collection Wide Leg Cintura Alta', 'jeans-zw-collection-wide-leg-cintura-alta', 'Jeans Zw Collection Wide Leg Cintura Alta - peça exclusiva da coleção TSM Atelier.', 599.00, 'JEANS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (40, 'Jeans Zw Collection Wide Leg Cintura Alta Foto2', 'jeans-zw-collection-wide-leg-cintura-alta-foto2', 'Jeans Zw Collection Wide Leg Cintura Alta Foto2 - peça exclusiva da coleção TSM Atelier.', 599.00, 'JEANS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / SHORTS_BERMUDAS ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (41, 'Short De Poliuretano', 'short-de-poliuretano', 'Short De Poliuretano - peça exclusiva da coleção TSM Atelier.', 599.00, 'SHORTS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (42, 'Short Globo Acetinado Com Renda', 'short-globo-acetinado-com-renda', 'Short Globo Acetinado Com Renda - peça exclusiva da coleção TSM Atelier.', 599.00, 'SHORTS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (43, 'Short-Saia De Veludo Cotelê Com Cinto', 'short-saia-de-veludo-cotele-com-cinto', 'Short-Saia De Veludo Cotelê Com Cinto - peça exclusiva da coleção TSM Atelier.', 599.00, 'SHORTS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (44, 'Shorts Com Babado', 'shorts-com-babado', 'Shorts Com Babado - peça exclusiva da coleção TSM Atelier.', 599.00, 'SHORTS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / BLAZERS_COLETES ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (45, 'Blazer Acinturado Com Ombreiras', 'blazer-acinturado-com-ombreiras', 'Blazer Acinturado Com Ombreiras - peça exclusiva da coleção TSM Atelier.', 599.00, 'BLAZER', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (46, 'Blazer Cruzado', 'blazer-cruzado', 'Blazer Cruzado - peça exclusiva da coleção TSM Atelier.', 599.00, 'BLAZER', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (47, 'Blazer Curto Xadrez', 'blazer-curto-xadrez', 'Blazer Curto Xadrez - peça exclusiva da coleção TSM Atelier.', 599.00, 'BLAZER', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (48, 'Blazer De Veludo Debrum Em Contraste', 'blazer-de-veludo-debrum-em-contraste', 'Blazer De Veludo Debrum Em Contraste - peça exclusiva da coleção TSM Atelier.', 599.00, 'BLAZER', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ─── CORES ────────────────────────────────────────────────────

-- ── FEMININO / VESTIDOS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (1, 1, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (2, 2, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (3, 3, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (4, 3, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (5, 4, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (6, 5, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (7, 5, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (8, 6, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (9, 6, 'Branco', '#FFFFFF', 0);

-- ── FEMININO / CALCAS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (10, 7, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (11, 8, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (12, 8, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (13, 9, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (14, 10, 'Preto', '#000000', 0);

-- ── FEMININO / CAMISAS_BLUSAS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (15, 11, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (16, 11, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (17, 12, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (18, 13, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (19, 13, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (20, 14, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (21, 15, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (22, 15, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (23, 15, 'Bege', '#F5F0E8', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (24, 16, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (25, 17, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (26, 18, 'Preto', '#000000', 0);

-- ── FEMININO / CAMISETAS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (27, 19, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (28, 20, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (29, 21, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (30, 22, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (31, 23, 'Preto', '#000000', 0);

-- ── FEMININO / CASACOS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (32, 24, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (33, 25, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (34, 26, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (35, 27, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (36, 28, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (37, 28, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (38, 29, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (39, 29, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (40, 29, 'Bege', '#F5F0E8', 0);

-- ── FEMININO / JAQUETAS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (41, 30, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (42, 31, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (43, 31, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (44, 32, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (45, 33, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (46, 33, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (47, 34, 'Preto', '#000000', 0);

-- ── FEMININO / JEANS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (48, 35, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (49, 35, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (50, 35, 'Bege', '#F5F0E8', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (51, 36, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (52, 37, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (53, 38, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (54, 38, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (55, 38, 'Bege', '#F5F0E8', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (56, 39, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (57, 39, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (58, 39, 'Bege', '#F5F0E8', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (59, 40, 'Preto', '#000000', 0);

-- ── FEMININO / SHORTS_BERMUDAS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (60, 41, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (61, 41, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (62, 42, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (63, 43, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (64, 44, 'Preto', '#000000', 0);

-- ── FEMININO / BLAZERS_COLETES ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (65, 45, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (66, 46, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (67, 46, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (68, 46, 'Bege', '#F5F0E8', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (69, 47, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (70, 48, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (71, 48, 'Branco', '#FFFFFF', 0);

-- ─── IMAGENS ──────────────────────────────────────────────────

-- ── FEMININO / VESTIDOS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (1, 1, 'http://localhost:9000/atelier/products/vestido-ampla-estampado/VESTIDO AMPLA ESTAMPADO 3.jpg', 'VESTIDO AMPLA ESTAMPADO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (2, 1, 'http://localhost:9000/atelier/products/vestido-ampla-estampado/VESTIDO AMPLA ESTAMPADO CAPA.jpg', 'VESTIDO AMPLA ESTAMPADO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (3, 1, 'http://localhost:9000/atelier/products/vestido-ampla-estampado/VESTIDO AMPLA ESTAMPADO HOVER.jpg', 'VESTIDO AMPLA ESTAMPADO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (4, 2, 'http://localhost:9000/atelier/products/vestido-curto-com-babado/VESTIDO CURTO COM BABADO 3.jpg', 'VESTIDO CURTO COM BABADO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (5, 2, 'http://localhost:9000/atelier/products/vestido-curto-com-babado/VESTIDO CURTO COM BABADO CAPA.jpg', 'VESTIDO CURTO COM BABADO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (6, 2, 'http://localhost:9000/atelier/products/vestido-curto-com-babado/VESTIDO CURTO COM BABADO HOVER.jpg', 'VESTIDO CURTO COM BABADO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (7, 3, 'http://localhost:9000/atelier/products/vestido-drapeado-gola-alta/VESTIDO DRAPEADO GOLA ALTA 3.jpg', 'VESTIDO DRAPEADO GOLA ALTA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (8, 3, 'http://localhost:9000/atelier/products/vestido-drapeado-gola-alta/VESTIDO DRAPEADO GOLA ALTA 4.jpg', 'VESTIDO DRAPEADO GOLA ALTA 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (9, 3, 'http://localhost:9000/atelier/products/vestido-drapeado-gola-alta/VESTIDO DRAPEADO GOLA ALTA CAPA.jpg', 'VESTIDO DRAPEADO GOLA ALTA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (10, 3, 'http://localhost:9000/atelier/products/vestido-drapeado-gola-alta/VESTIDO DRAPEADO GOLA ALTA HOVER.jpg', 'VESTIDO DRAPEADO GOLA ALTA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (11, 4, 'http://localhost:9000/atelier/products/vestido-drapeado-gola-alta/VESTIDO DRAPEADO GOLA ALTA COLOR2 FOTO1.jpg', 'VESTIDO DRAPEADO GOLA ALTA COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (12, 4, 'http://localhost:9000/atelier/products/vestido-drapeado-gola-alta/VESTIDO DRAPEADO GOLA ALTA COLOR2 FOTO2.jpg', 'VESTIDO DRAPEADO GOLA ALTA COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (13, 5, 'http://localhost:9000/atelier/products/vestido-longo-acetinado-estampado/VESTIDO LONGO ACETINADO ESTAMPADO 3.jpg', 'VESTIDO LONGO ACETINADO ESTAMPADO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (14, 5, 'http://localhost:9000/atelier/products/vestido-longo-acetinado-estampado/VESTIDO LONGO ACETINADO ESTAMPADO CAPA.jpg', 'VESTIDO LONGO ACETINADO ESTAMPADO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (15, 5, 'http://localhost:9000/atelier/products/vestido-longo-acetinado-estampado/VESTIDO LONGO ACETINADO ESTAMPADO HOVER.jpg', 'VESTIDO LONGO ACETINADO ESTAMPADO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (16, 6, 'http://localhost:9000/atelier/products/vestido-longo-halter/VESTIDO LONGO HALTER 3.jpg', 'VESTIDO LONGO HALTER 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (17, 6, 'http://localhost:9000/atelier/products/vestido-longo-halter/VESTIDO LONGO HALTER 4.jpg', 'VESTIDO LONGO HALTER 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (18, 6, 'http://localhost:9000/atelier/products/vestido-longo-halter/VESTIDO LONGO HALTER CAPA.jpg', 'VESTIDO LONGO HALTER CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (19, 6, 'http://localhost:9000/atelier/products/vestido-longo-halter/VESTIDO LONGO HALTER HOVER.jpg', 'VESTIDO LONGO HALTER HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (20, 7, 'http://localhost:9000/atelier/products/vestido-longo-halter/VESTIDO LONGO HALTER COLOR2 FOTO1.jpg', 'VESTIDO LONGO HALTER COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (21, 7, 'http://localhost:9000/atelier/products/vestido-longo-halter/VESTIDO LONGO HALTER COLOR2 FOTO2.jpg', 'VESTIDO LONGO HALTER COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (22, 8, 'http://localhost:9000/atelier/products/vestido-midi-com-cinto/VESTIDO MIDI COM CINTO 3.jpg', 'VESTIDO MIDI COM CINTO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (23, 8, 'http://localhost:9000/atelier/products/vestido-midi-com-cinto/VESTIDO MIDI COM CINTO CAPA.jpg', 'VESTIDO MIDI COM CINTO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (24, 8, 'http://localhost:9000/atelier/products/vestido-midi-com-cinto/VESTIDO MIDI COM CINTO HOVER.jpg', 'VESTIDO MIDI COM CINTO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (25, 9, 'http://localhost:9000/atelier/products/vestido-midi-com-cinto/VESTIDO MIDI COM CINTO COLOR2 FOTO1.jpg', 'VESTIDO MIDI COM CINTO COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (26, 9, 'http://localhost:9000/atelier/products/vestido-midi-com-cinto/VESTIDO MIDI COM CINTO COLOR2 FOTO2.jpg', 'VESTIDO MIDI COM CINTO COLOR2 FOTO2.jpg', 2, false);

-- ── FEMININO / CALCAS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (27, 10, 'http://localhost:9000/atelier/products/calca-acetinada-estampada/CALÇA ACETINADA ESTAMPADA 3.jpg', 'CALÇA ACETINADA ESTAMPADA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (28, 10, 'http://localhost:9000/atelier/products/calca-acetinada-estampada/CALÇA ACETINADA ESTAMPADA 4.jpg', 'CALÇA ACETINADA ESTAMPADA 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (29, 10, 'http://localhost:9000/atelier/products/calca-acetinada-estampada/CALÇA ACETINADA ESTAMPADA CAPA.jpg', 'CALÇA ACETINADA ESTAMPADA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (30, 10, 'http://localhost:9000/atelier/products/calca-acetinada-estampada/CALÇA ACETINADA ESTAMPADA HOVER.jpg', 'CALÇA ACETINADA ESTAMPADA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (31, 11, 'http://localhost:9000/atelier/products/calca-com-pregas/CALÇA COM PREGAS 3.jpg', 'CALÇA COM PREGAS 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (32, 11, 'http://localhost:9000/atelier/products/calca-com-pregas/CALÇA COM PREGAS CAPA.jpg', 'CALÇA COM PREGAS CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (33, 11, 'http://localhost:9000/atelier/products/calca-com-pregas/CALÇA COM PREGAS HOVER.jpg', 'CALÇA COM PREGAS HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (34, 12, 'http://localhost:9000/atelier/products/calca-com-pregas/CALÇA COM PREGAS COLOR2 FOTO1.jpg', 'CALÇA COM PREGAS COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (35, 12, 'http://localhost:9000/atelier/products/calca-com-pregas/CALÇA COM PREGAS COLOR2 FOTO2.jpg', 'CALÇA COM PREGAS COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (36, 13, 'http://localhost:9000/atelier/products/calca-reta-zw-collection/CALÇA RETA ZW COLLECTION 3.jpg', 'CALÇA RETA ZW COLLECTION 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (37, 13, 'http://localhost:9000/atelier/products/calca-reta-zw-collection/CALÇA RETA ZW COLLECTION CAPA.jpg', 'CALÇA RETA ZW COLLECTION CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (38, 13, 'http://localhost:9000/atelier/products/calca-reta-zw-collection/CALÇA RETA ZW COLLECTION HOVER.jpg', 'CALÇA RETA ZW COLLECTION HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (39, 14, 'http://localhost:9000/atelier/products/calca-xadrez-com-tiras/CALÇA XADREZ COM TIRAS 3.jpg', 'CALÇA XADREZ COM TIRAS 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (40, 14, 'http://localhost:9000/atelier/products/calca-xadrez-com-tiras/CALÇA XADREZ COM TIRAS CAPA.jpg', 'CALÇA XADREZ COM TIRAS CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (41, 14, 'http://localhost:9000/atelier/products/calca-xadrez-com-tiras/CALÇA XADREZ COM TIRAS HOVER.jpg', 'CALÇA XADREZ COM TIRAS HOVER.jpg', 2, false);

-- ── FEMININO / CAMISAS_BLUSAS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (42, 15, 'http://localhost:9000/atelier/products/calca-de-moletom-basica-reta-com-bainha-ajustavel/CALÇA DE MOLETOM BÁSICA RETA COM BAINHA AJUSTÁVEL 3.jpg', 'CALÇA DE MOLETOM BÁSICA RETA COM BAINHA AJUSTÁVEL 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (43, 15, 'http://localhost:9000/atelier/products/calca-de-moletom-basica-reta-com-bainha-ajustavel/CALÇA DE MOLETOM BÁSICA RETA COM BAINHA AJUSTÁVEL CAPA.jpg', 'CALÇA DE MOLETOM BÁSICA RETA COM BAINHA AJUSTÁVEL CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (44, 15, 'http://localhost:9000/atelier/products/calca-de-moletom-basica-reta-com-bainha-ajustavel/CALÇA DE MOLETOM BÁSICA RETA COM BAINHA AJUSTÁVEL HOVER.jpg', 'CALÇA DE MOLETOM BÁSICA RETA COM BAINHA AJUSTÁVEL HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (45, 15, 'http://localhost:9000/atelier/products/camisa-acetinada-com-botoes-dourados/CAMISA ACETINADA COM BOTÕES DOURADOS 3.jpg', 'CAMISA ACETINADA COM BOTÕES DOURADOS 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (46, 15, 'http://localhost:9000/atelier/products/camisa-acetinada-com-botoes-dourados/CAMISA ACETINADA COM BOTÕES DOURADOS CAPA.jpg', 'CAMISA ACETINADA COM BOTÕES DOURADOS CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (47, 15, 'http://localhost:9000/atelier/products/camisa-acetinada-com-botoes-dourados/CAMISA ACETINADA COM BOTÕES DOURADOS HOVER.jpg', 'CAMISA ACETINADA COM BOTÕES DOURADOS HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (48, 16, 'http://localhost:9000/atelier/products/camisa-acetinada-com-botoes-dourados/CAMISA ACETINADA COM BOTÕES DOURADOS COLOR2 FOTO1.jpg', 'CAMISA ACETINADA COM BOTÕES DOURADOS COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (49, 16, 'http://localhost:9000/atelier/products/camisa-acetinada-com-botoes-dourados/CAMISA ACETINADA COM BOTÕES DOURADOS COLOR2 FOTO2.jpg', 'CAMISA ACETINADA COM BOTÕES DOURADOS COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (50, 16, 'http://localhost:9000/atelier/products/camisa-acetinada-com-botoes-dourados/CAMISA ACETINADA COM BOTÕES DOURADOS COLOR2 FOTO3.jpg', 'CAMISA ACETINADA COM BOTÕES DOURADOS COLOR2 FOTO3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (51, 17, 'http://localhost:9000/atelier/products/camisa-gasa-oversize-com-bolso/CAMISA GASA OVERSIZE COM BOLSO CAPA.jpg', 'CAMISA GASA OVERSIZE COM BOLSO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (52, 17, 'http://localhost:9000/atelier/products/camisa-gasa-oversize-com-bolso/CAMISA GASA OVERSIZE COM BOLSO HOVER.jpg', 'CAMISA GASA OVERSIZE COM BOLSO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (53, 18, 'http://localhost:9000/atelier/products/camisa-listrada-com-laco/CAMISA LISTRADA COM LAÇO 3.jpg', 'CAMISA LISTRADA COM LAÇO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (54, 18, 'http://localhost:9000/atelier/products/camisa-listrada-com-laco/CAMISA LISTRADA COM LAÇO CAPA.jpg', 'CAMISA LISTRADA COM LAÇO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (55, 18, 'http://localhost:9000/atelier/products/camisa-listrada-com-laco/CAMISA LISTRADA COM LAÇO HOVER.jpg', 'CAMISA LISTRADA COM LAÇO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (56, 19, 'http://localhost:9000/atelier/products/camisa-listrada-com-laco/CAMISA LISTRADA COM LAÇO COLOR2 FOTO1.jpg', 'CAMISA LISTRADA COM LAÇO COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (57, 19, 'http://localhost:9000/atelier/products/camisa-listrada-com-laco/CAMISA LISTRADA COM LAÇO COLOR2 FOTO2.jpg', 'CAMISA LISTRADA COM LAÇO COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (58, 20, 'http://localhost:9000/atelier/products/camisa-listrada-cruzada/CAMISA LISTRADA CRUZADA 3.jpg', 'CAMISA LISTRADA CRUZADA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (59, 20, 'http://localhost:9000/atelier/products/camisa-listrada-cruzada/CAMISA LISTRADA CRUZADA CAPA.jpg', 'CAMISA LISTRADA CRUZADA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (60, 20, 'http://localhost:9000/atelier/products/camisa-listrada-cruzada/CAMISA LISTRADA CRUZADA HOVER.jpg', 'CAMISA LISTRADA CRUZADA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (61, 21, 'http://localhost:9000/atelier/products/casaco-de-moletom-com-ziper/CASACO DE MOLETOM COM ZÍPER 3.jpg', 'CASACO DE MOLETOM COM ZÍPER 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (62, 21, 'http://localhost:9000/atelier/products/casaco-de-moletom-com-ziper/CASACO DE MOLETOM COM ZÍPER CAPA.jpg', 'CASACO DE MOLETOM COM ZÍPER CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (63, 21, 'http://localhost:9000/atelier/products/casaco-de-moletom-com-ziper/CASACO DE MOLETOM COM ZÍPER HOVER.jpg', 'CASACO DE MOLETOM COM ZÍPER HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (64, 22, 'http://localhost:9000/atelier/products/casaco-de-moletom-com-ziper/CASACO DE MOLETOM COM ZÍPER COLOR2 FOTO1.jpg', 'CASACO DE MOLETOM COM ZÍPER COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (65, 22, 'http://localhost:9000/atelier/products/casaco-de-moletom-com-ziper/CASACO DE MOLETOM COM ZÍPER COLOR2 FOTO2.jpg', 'CASACO DE MOLETOM COM ZÍPER COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (66, 23, 'http://localhost:9000/atelier/products/casaco-de-moletom-com-ziper/CASACO DE MOLETOM COM ZÍPER COLOR3 FOTO1.jpg', 'CASACO DE MOLETOM COM ZÍPER COLOR3 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (67, 23, 'http://localhost:9000/atelier/products/casaco-de-moletom-com-ziper/CASACO DE MOLETOM COM ZÍPER COLOR3 FOTO2.jpg', 'CASACO DE MOLETOM COM ZÍPER COLOR3 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (68, 24, 'http://localhost:9000/atelier/products/moletom-com-efeito-de-lavagem-don-clemente/MOLETOM COM EFEITO DE LAVAGEM DON CLEMENTE 3.jpg', 'MOLETOM COM EFEITO DE LAVAGEM DON CLEMENTE 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (69, 24, 'http://localhost:9000/atelier/products/moletom-com-efeito-de-lavagem-don-clemente/MOLETOM COM EFEITO DE LAVAGEM DON CLEMENTE CAPA.jpg', 'MOLETOM COM EFEITO DE LAVAGEM DON CLEMENTE CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (70, 24, 'http://localhost:9000/atelier/products/moletom-com-efeito-de-lavagem-don-clemente/MOLETOM COM EFEITO DE LAVAGEM DON CLEMENTE HOVER.jpg', 'MOLETOM COM EFEITO DE LAVAGEM DON CLEMENTE HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (71, 25, 'http://localhost:9000/atelier/products/moletom-com-mensagem-e-capuz/MOLETOM COM MENSAGEM E CAPUZ 3.jpg', 'MOLETOM COM MENSAGEM E CAPUZ 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (72, 25, 'http://localhost:9000/atelier/products/moletom-com-mensagem-e-capuz/MOLETOM COM MENSAGEM E CAPUZ CAPA.jpg', 'MOLETOM COM MENSAGEM E CAPUZ CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (73, 25, 'http://localhost:9000/atelier/products/moletom-com-mensagem-e-capuz/MOLETOM COM MENSAGEM E CAPUZ HOVER.jpg', 'MOLETOM COM MENSAGEM E CAPUZ HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (74, 26, 'http://localhost:9000/atelier/products/moletom-com-mensagem-em-contraste/MOLETOM COM MENSAGEM EM CONTRASTE 3.jpg', 'MOLETOM COM MENSAGEM EM CONTRASTE 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (75, 26, 'http://localhost:9000/atelier/products/moletom-com-mensagem-em-contraste/MOLETOM COM MENSAGEM EM CONTRASTE CAPA.jpg', 'MOLETOM COM MENSAGEM EM CONTRASTE CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (76, 26, 'http://localhost:9000/atelier/products/moletom-com-mensagem-em-contraste/MOLETOM COM MENSAGEM EM CONTRASTE HOVER.jpg', 'MOLETOM COM MENSAGEM EM CONTRASTE HOVER.jpg', 2, false);

-- ── FEMININO / CAMISETAS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (77, 27, 'http://localhost:9000/atelier/products/camiseta-algodao-100-botoes/CAMISETA ALGODÃO 100% BOTÕES 3.jpg', 'CAMISETA ALGODÃO 100% BOTÕES 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (78, 27, 'http://localhost:9000/atelier/products/camiseta-algodao-100-botoes/CAMISETA ALGODÃO 100% BOTÕES CAPA.jpg', 'CAMISETA ALGODÃO 100% BOTÕES CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (79, 27, 'http://localhost:9000/atelier/products/camiseta-algodao-100-botoes/CAMISETA ALGODÃO 100% BOTÕES HOVER.jpg', 'CAMISETA ALGODÃO 100% BOTÕES HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (80, 28, 'http://localhost:9000/atelier/products/camiseta-algodao-100-botoes-hover/CAMISETA ALGODÃO 100% BOTÕES HOVER COLOR2 FOTO1.jpg', 'CAMISETA ALGODÃO 100% BOTÕES HOVER COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (81, 28, 'http://localhost:9000/atelier/products/camiseta-algodao-100-botoes-hover/CAMISETA ALGODÃO 100% BOTÕES HOVER COLOR2 FOTO2.jpg', 'CAMISETA ALGODÃO 100% BOTÕES HOVER COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (82, 29, 'http://localhost:9000/atelier/products/camiseta-com-texto-em-contraste/CAMISETA COM TEXTO EM CONTRASTE 3.jpg', 'CAMISETA COM TEXTO EM CONTRASTE 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (83, 29, 'http://localhost:9000/atelier/products/camiseta-com-texto-em-contraste/CAMISETA COM TEXTO EM CONTRASTE CAPA.jpg', 'CAMISETA COM TEXTO EM CONTRASTE CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (84, 29, 'http://localhost:9000/atelier/products/camiseta-com-texto-em-contraste/CAMISETA COM TEXTO EM CONTRASTE HOVER.jpg', 'CAMISETA COM TEXTO EM CONTRASTE HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (85, 30, 'http://localhost:9000/atelier/products/camiseta-interlock-de-manga-curta/CAMISETA INTERLOCK DE MANGA CURTA 3.jpg', 'CAMISETA INTERLOCK DE MANGA CURTA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (86, 30, 'http://localhost:9000/atelier/products/camiseta-interlock-de-manga-curta/CAMISETA INTERLOCK DE MANGA CURTA 4.jpg', 'CAMISETA INTERLOCK DE MANGA CURTA 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (87, 30, 'http://localhost:9000/atelier/products/camiseta-interlock-de-manga-curta/CAMISETA INTERLOCK DE MANGA CURTA CAPA.jpg', 'CAMISETA INTERLOCK DE MANGA CURTA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (88, 30, 'http://localhost:9000/atelier/products/camiseta-interlock-de-manga-curta/CAMISETA INTERLOCK DE MANGA CURTA HOVER.jpg', 'CAMISETA INTERLOCK DE MANGA CURTA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (89, 31, 'http://localhost:9000/atelier/products/camiseta-listrada-com-texto/CAMISETA LISTRADA COM TEXTO 3.jpg', 'CAMISETA LISTRADA COM TEXTO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (90, 31, 'http://localhost:9000/atelier/products/camiseta-listrada-com-texto/CAMISETA LISTRADA COM TEXTO CAPA.jpg', 'CAMISETA LISTRADA COM TEXTO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (91, 31, 'http://localhost:9000/atelier/products/camiseta-listrada-com-texto/CAMISETA LISTRADA COM TEXTO HOVER.jpg', 'CAMISETA LISTRADA COM TEXTO HOVER.jpg', 2, false);

-- ── FEMININO / CASACOS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (92, 32, 'http://localhost:9000/atelier/products/casaco-com-la/CASACO COM LÃ CAPA.jpg', 'CASACO COM LÃ CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (93, 32, 'http://localhost:9000/atelier/products/casaco-com-la/CASACO COM LÃ HOVER.jpg', 'CASACO COM LÃ HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (94, 33, 'http://localhost:9000/atelier/products/casaco-curto-de-la/CASACO CURTO DE LÃ 3.jpg', 'CASACO CURTO DE LÃ 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (95, 33, 'http://localhost:9000/atelier/products/casaco-curto-de-la/CASACO CURTO DE LÃ CAPA.jpg', 'CASACO CURTO DE LÃ CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (96, 33, 'http://localhost:9000/atelier/products/casaco-curto-de-la/CASACO CURTO DE LÃ HOVER.jpg', 'CASACO CURTO DE LÃ HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (97, 34, 'http://localhost:9000/atelier/products/casaco-curto-de-pelo-sintetico/CASACO CURTO DE PELO SINTÉTICO 3.jpg', 'CASACO CURTO DE PELO SINTÉTICO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (98, 34, 'http://localhost:9000/atelier/products/casaco-curto-de-pelo-sintetico/CASACO CURTO DE PELO SINTÉTICO CAPA.jpg', 'CASACO CURTO DE PELO SINTÉTICO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (99, 34, 'http://localhost:9000/atelier/products/casaco-curto-de-pelo-sintetico/CASACO CURTO DE PELO SINTÉTICO HOVER.jpg', 'CASACO CURTO DE PELO SINTÉTICO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (100, 35, 'http://localhost:9000/atelier/products/casaco-de-mistura-de-la/CASACO DE MISTURA DE LÃ 3.jpg', 'CASACO DE MISTURA DE LÃ 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (101, 35, 'http://localhost:9000/atelier/products/casaco-de-mistura-de-la/CASACO DE MISTURA DE LÃ 4.jpg', 'CASACO DE MISTURA DE LÃ 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (102, 35, 'http://localhost:9000/atelier/products/casaco-de-mistura-de-la/CASACO DE MISTURA DE LÃ CAPA.jpg', 'CASACO DE MISTURA DE LÃ CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (103, 35, 'http://localhost:9000/atelier/products/casaco-de-mistura-de-la/CASACO DE MISTURA DE LÃ HOVER.jpg', 'CASACO DE MISTURA DE LÃ HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (104, 36, 'http://localhost:9000/atelier/products/casaco-la-zw/CASACO LÃ ZW 3.jpg', 'CASACO LÃ ZW 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (105, 36, 'http://localhost:9000/atelier/products/casaco-la-zw/CASACO LÃ ZW CAPA.jpg', 'CASACO LÃ ZW CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (106, 36, 'http://localhost:9000/atelier/products/casaco-la-zw/CASACO LÃ ZW HOVER.jpg', 'CASACO LÃ ZW HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (107, 37, 'http://localhost:9000/atelier/products/casaco-la-zw/CASACO LÃ ZW COLOR2 FOTO1.jpg', 'CASACO LÃ ZW COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (108, 37, 'http://localhost:9000/atelier/products/casaco-la-zw/CASACO LÃ ZW COLOR2 FOTO2.jpg', 'CASACO LÃ ZW COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (109, 37, 'http://localhost:9000/atelier/products/casaco-la-zw/CASACO LÃ ZW COLOR2 FOTO3.jpg', 'CASACO LÃ ZW COLOR2 FOTO3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (110, 38, 'http://localhost:9000/atelier/products/casaco-ombreiras-soft/CASACO OMBREIRAS SOFT 3.jpg', 'CASACO OMBREIRAS SOFT 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (111, 38, 'http://localhost:9000/atelier/products/casaco-ombreiras-soft/CASACO OMBREIRAS SOFT CAPA.jpg', 'CASACO OMBREIRAS SOFT CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (112, 38, 'http://localhost:9000/atelier/products/casaco-ombreiras-soft/CASACO OMBREIRAS SOFT HOVER.jpg', 'CASACO OMBREIRAS SOFT HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (113, 39, 'http://localhost:9000/atelier/products/casaco-ombreiras-soft/CASACO OMBREIRAS SOFT COLOR2 FOTO1.jpg', 'CASACO OMBREIRAS SOFT COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (114, 39, 'http://localhost:9000/atelier/products/casaco-ombreiras-soft/CASACO OMBREIRAS SOFT COLOR2 FOTO2.jpg', 'CASACO OMBREIRAS SOFT COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (115, 39, 'http://localhost:9000/atelier/products/casaco-ombreiras-soft/CASACO OMBREIRAS SOFT COLOR2 FOTO3.jpg', 'CASACO OMBREIRAS SOFT COLOR2 FOTO3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (116, 40, 'http://localhost:9000/atelier/products/casaco-ombreiras-soft/CASACO OMBREIRAS SOFT COLOR3 FOTO1.jpg', 'CASACO OMBREIRAS SOFT COLOR3 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (117, 40, 'http://localhost:9000/atelier/products/casaco-ombreiras-soft/CASACO OMBREIRAS SOFT COLOR3 FOTO2.jpg', 'CASACO OMBREIRAS SOFT COLOR3 FOTO2.jpg', 2, false);

-- ── FEMININO / JAQUETAS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (118, 41, 'http://localhost:9000/atelier/products/casaco-de-plumas-curto/CASACO DE PLUMAS CURTO 3.jpg', 'CASACO DE PLUMAS CURTO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (119, 41, 'http://localhost:9000/atelier/products/casaco-de-plumas-curto/CASACO DE PLUMAS CURTO CAPA.jpg', 'CASACO DE PLUMAS CURTO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (120, 41, 'http://localhost:9000/atelier/products/casaco-de-plumas-curto/CASACO DE PLUMAS CURTO HOVER.jpg', 'CASACO DE PLUMAS CURTO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (121, 42, 'http://localhost:9000/atelier/products/jaqueta-com-efeito-suede-de-gola-alta/JAQUETA COM EFEITO SUEDE DE GOLA ALTA 3.jpg', 'JAQUETA COM EFEITO SUEDE DE GOLA ALTA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (122, 42, 'http://localhost:9000/atelier/products/jaqueta-com-efeito-suede-de-gola-alta/JAQUETA COM EFEITO SUEDE DE GOLA ALTA 4.jpg', 'JAQUETA COM EFEITO SUEDE DE GOLA ALTA 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (123, 42, 'http://localhost:9000/atelier/products/jaqueta-com-efeito-suede-de-gola-alta/JAQUETA COM EFEITO SUEDE DE GOLA ALTA CAPA.jpg', 'JAQUETA COM EFEITO SUEDE DE GOLA ALTA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (124, 42, 'http://localhost:9000/atelier/products/jaqueta-com-efeito-suede-de-gola-alta/JAQUETA COM EFEITO SUEDE DE GOLA ALTA HOVER.jpg', 'JAQUETA COM EFEITO SUEDE DE GOLA ALTA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (125, 43, 'http://localhost:9000/atelier/products/jaqueta-com-efeito-suede-de-gola-alta/JAQUETA COM EFEITO SUEDE DE GOLA ALTA COLOR2 FOTO1.jpg', 'JAQUETA COM EFEITO SUEDE DE GOLA ALTA COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (126, 43, 'http://localhost:9000/atelier/products/jaqueta-com-efeito-suede-de-gola-alta/JAQUETA COM EFEITO SUEDE DE GOLA ALTA COLOR2 FOTO2.jpg', 'JAQUETA COM EFEITO SUEDE DE GOLA ALTA COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (127, 44, 'http://localhost:9000/atelier/products/jaqueta-de-poliuretano/JAQUETA DE POLIURETANO 3.jpg', 'JAQUETA DE POLIURETANO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (128, 44, 'http://localhost:9000/atelier/products/jaqueta-de-poliuretano/JAQUETA DE POLIURETANO CAPA.jpg', 'JAQUETA DE POLIURETANO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (129, 44, 'http://localhost:9000/atelier/products/jaqueta-de-poliuretano/JAQUETA DE POLIURETANO HOVER.jpg', 'JAQUETA DE POLIURETANO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (130, 45, 'http://localhost:9000/atelier/products/jaqueta-de-veludo-cotele-com-contraste/JAQUETA DE VELUDO COTELÊ COM CONTRASTE 3.jpg', 'JAQUETA DE VELUDO COTELÊ COM CONTRASTE 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (131, 45, 'http://localhost:9000/atelier/products/jaqueta-de-veludo-cotele-com-contraste/JAQUETA DE VELUDO COTELÊ COM CONTRASTE CAPA.jpg', 'JAQUETA DE VELUDO COTELÊ COM CONTRASTE CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (132, 45, 'http://localhost:9000/atelier/products/jaqueta-de-veludo-cotele-com-contraste/JAQUETA DE VELUDO COTELÊ COM CONTRASTE HOVER.jpg', 'JAQUETA DE VELUDO COTELÊ COM CONTRASTE HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (133, 46, 'http://localhost:9000/atelier/products/jaqueta-de-veludo-cotele-com-contraste/JAQUETA DE VELUDO COTELÊ COM CONTRASTE COLOR2 FOTO1.jpg', 'JAQUETA DE VELUDO COTELÊ COM CONTRASTE COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (134, 46, 'http://localhost:9000/atelier/products/jaqueta-de-veludo-cotele-com-contraste/JAQUETA DE VELUDO COTELÊ COM CONTRASTE COLOR2 FOTO2.jpg', 'JAQUETA DE VELUDO COTELÊ COM CONTRASTE COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (135, 47, 'http://localhost:9000/atelier/products/jaqueta-reversivel-dupla-face/JAQUETA REVERSÍVEL DUPLA FACE 3.jpg', 'JAQUETA REVERSÍVEL DUPLA FACE 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (136, 47, 'http://localhost:9000/atelier/products/jaqueta-reversivel-dupla-face/JAQUETA REVERSÍVEL DUPLA FACE 4.jpg', 'JAQUETA REVERSÍVEL DUPLA FACE 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (137, 47, 'http://localhost:9000/atelier/products/jaqueta-reversivel-dupla-face/JAQUETA REVERSÍVEL DUPLA FACE CAPA.jpg', 'JAQUETA REVERSÍVEL DUPLA FACE CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (138, 47, 'http://localhost:9000/atelier/products/jaqueta-reversivel-dupla-face/JAQUETA REVERSÍVEL DUPLA FACE HOVER.jpg', 'JAQUETA REVERSÍVEL DUPLA FACE HOVER.jpg', 2, false);

-- ── FEMININO / JEANS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (139, 48, 'http://localhost:9000/atelier/products/jeans-trf-skinny-cintura-alta/JEANS TRF SKINNY CINTURA ALTA 3.jpg', 'JEANS TRF SKINNY CINTURA ALTA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (140, 48, 'http://localhost:9000/atelier/products/jeans-trf-skinny-cintura-alta/JEANS TRF SKINNY CINTURA ALTA CAPA.jpg', 'JEANS TRF SKINNY CINTURA ALTA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (141, 48, 'http://localhost:9000/atelier/products/jeans-trf-skinny-cintura-alta/JEANS TRF SKINNY CINTURA ALTA HOVER.jpg', 'JEANS TRF SKINNY CINTURA ALTA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (142, 49, 'http://localhost:9000/atelier/products/jeans-trf-skinny-cintura-alta/JEANS TRF SKINNY CINTURA ALTA COLOR2 FOTO1.jpg', 'JEANS TRF SKINNY CINTURA ALTA COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (143, 49, 'http://localhost:9000/atelier/products/jeans-trf-skinny-cintura-alta/JEANS TRF SKINNY CINTURA ALTA COLOR2 FOTO2.jpg', 'JEANS TRF SKINNY CINTURA ALTA COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (144, 50, 'http://localhost:9000/atelier/products/jeans-trf-skinny-cintura-alta/JEANS TRF SKINNY CINTURA ALTA COLOR3 FOTO1.jpg', 'JEANS TRF SKINNY CINTURA ALTA COLOR3 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (145, 50, 'http://localhost:9000/atelier/products/jeans-trf-skinny-cintura-alta/JEANS TRF SKINNY CINTURA ALTA COLOR3 FOTO2.jpg', 'JEANS TRF SKINNY CINTURA ALTA COLOR3 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (146, 51, 'http://localhost:9000/atelier/products/jeans-z01-mom-fit-cintura-alta-com-vinco-passado/JEANS Z.01 MOM FIT CINTURA ALTA COM VINCO PASSADO 3.jpg', 'JEANS Z.01 MOM FIT CINTURA ALTA COM VINCO PASSADO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (147, 51, 'http://localhost:9000/atelier/products/jeans-z01-mom-fit-cintura-alta-com-vinco-passado/JEANS Z.01 MOM FIT CINTURA ALTA COM VINCO PASSADO CAPA.jpg', 'JEANS Z.01 MOM FIT CINTURA ALTA COM VINCO PASSADO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (148, 51, 'http://localhost:9000/atelier/products/jeans-z01-mom-fit-cintura-alta-com-vinco-passado/JEANS Z.01 MOM FIT CINTURA ALTA COM VINCO PASSADO HOVER.jpg', 'JEANS Z.01 MOM FIT CINTURA ALTA COM VINCO PASSADO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (149, 52, 'http://localhost:9000/atelier/products/jeans-z1975-barrel-cintura-media/JEANS Z1975 BARREL CINTURA MÉDIA 3.jpg', 'JEANS Z1975 BARREL CINTURA MÉDIA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (150, 52, 'http://localhost:9000/atelier/products/jeans-z1975-barrel-cintura-media/JEANS Z1975 BARREL CINTURA MÉDIA CAPA.jpg', 'JEANS Z1975 BARREL CINTURA MÉDIA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (151, 52, 'http://localhost:9000/atelier/products/jeans-z1975-barrel-cintura-media/JEANS Z1975 BARREL CINTURA MÉDIA HOVER.jpg', 'JEANS Z1975 BARREL CINTURA MÉDIA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (152, 53, 'http://localhost:9000/atelier/products/jeans-z1975-cropped-de-cintura-media/JEANS Z1975 CROPPED DE CINTURA MÉDIA 3.jpg', 'JEANS Z1975 CROPPED DE CINTURA MÉDIA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (153, 53, 'http://localhost:9000/atelier/products/jeans-z1975-cropped-de-cintura-media/JEANS Z1975 CROPPED DE CINTURA MÉDIA 4.jpg', 'JEANS Z1975 CROPPED DE CINTURA MÉDIA 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (154, 53, 'http://localhost:9000/atelier/products/jeans-z1975-cropped-de-cintura-media/JEANS Z1975 CROPPED DE CINTURA MÉDIA CAPA.jpg', 'JEANS Z1975 CROPPED DE CINTURA MÉDIA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (155, 53, 'http://localhost:9000/atelier/products/jeans-z1975-cropped-de-cintura-media/JEANS Z1975 CROPPED DE CINTURA MÉDIA HOVER.jpg', 'JEANS Z1975 CROPPED DE CINTURA MÉDIA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (156, 54, 'http://localhost:9000/atelier/products/jeans-z1975-cropped-de-cintura-media/JEANS Z1975 CROPPED DE CINTURA MÉDIA COLOR2 FOTO1.jpg', 'JEANS Z1975 CROPPED DE CINTURA MÉDIA COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (157, 54, 'http://localhost:9000/atelier/products/jeans-z1975-cropped-de-cintura-media/JEANS Z1975 CROPPED DE CINTURA MÉDIA COLOR2 FOTO2.jpg', 'JEANS Z1975 CROPPED DE CINTURA MÉDIA COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (158, 55, 'http://localhost:9000/atelier/products/jeans-z1975-cropped-de-cintura-media/JEANS Z1975 CROPPED DE CINTURA MÉDIA COLOR3 FOTO1.jpg', 'JEANS Z1975 CROPPED DE CINTURA MÉDIA COLOR3 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (159, 55, 'http://localhost:9000/atelier/products/jeans-z1975-cropped-de-cintura-media/JEANS Z1975 CROPPED DE CINTURA MÉDIA COLOR3 FOTO2.jpg', 'JEANS Z1975 CROPPED DE CINTURA MÉDIA COLOR3 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (160, 56, 'http://localhost:9000/atelier/products/jeans-zw-collection-wide-leg-cintura-alta/JEANS ZW COLLECTION WIDE LEG CINTURA ALTA 3.jpg', 'JEANS ZW COLLECTION WIDE LEG CINTURA ALTA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (161, 56, 'http://localhost:9000/atelier/products/jeans-zw-collection-wide-leg-cintura-alta/JEANS ZW COLLECTION WIDE LEG CINTURA ALTA CAPA.jpg', 'JEANS ZW COLLECTION WIDE LEG CINTURA ALTA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (162, 56, 'http://localhost:9000/atelier/products/jeans-zw-collection-wide-leg-cintura-alta/JEANS ZW COLLECTION WIDE LEG CINTURA ALTA HOVER.jpg', 'JEANS ZW COLLECTION WIDE LEG CINTURA ALTA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (163, 57, 'http://localhost:9000/atelier/products/jeans-zw-collection-wide-leg-cintura-alta/JEANS ZW COLLECTION WIDE LEG CINTURA ALTA COLOR2 FOTO2.jpg', 'JEANS ZW COLLECTION WIDE LEG CINTURA ALTA COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (164, 58, 'http://localhost:9000/atelier/products/jeans-zw-collection-wide-leg-cintura-alta/JEANS ZW COLLECTION WIDE LEG CINTURA ALTA COLOR3 FOTO1.jpg', 'JEANS ZW COLLECTION WIDE LEG CINTURA ALTA COLOR3 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (165, 58, 'http://localhost:9000/atelier/products/jeans-zw-collection-wide-leg-cintura-alta/JEANS ZW COLLECTION WIDE LEG CINTURA ALTA COLOR3 FOTO2.jpg', 'JEANS ZW COLLECTION WIDE LEG CINTURA ALTA COLOR3 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (166, 59, 'http://localhost:9000/atelier/products/jeans-zw-collection-wide-leg-cintura-alta-foto2/JEANS ZW COLLECTION WIDE LEG CINTURA ALTA FOTO2 COLOR1.jpg', 'JEANS ZW COLLECTION WIDE LEG CINTURA ALTA FOTO2 COLOR1.jpg', 99, false);

-- ── FEMININO / SHORTS_BERMUDAS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (167, 60, 'http://localhost:9000/atelier/products/short-de-poliuretano/SHORT DE POLIURETANO 3.jpg', 'SHORT DE POLIURETANO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (168, 60, 'http://localhost:9000/atelier/products/short-de-poliuretano/SHORT DE POLIURETANO CAPA.jpg', 'SHORT DE POLIURETANO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (169, 60, 'http://localhost:9000/atelier/products/short-de-poliuretano/SHORT DE POLIURETANO HOVER.jpg', 'SHORT DE POLIURETANO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (170, 61, 'http://localhost:9000/atelier/products/short-de-poliuretano/SHORT DE POLIURETANO COLOR2 FOTO1.jpg', 'SHORT DE POLIURETANO COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (171, 61, 'http://localhost:9000/atelier/products/short-de-poliuretano/SHORT DE POLIURETANO COLOR2 FOTO2.jpg', 'SHORT DE POLIURETANO COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (172, 62, 'http://localhost:9000/atelier/products/short-globo-acetinado-com-renda/SHORT GLOBO ACETINADO COM RENDA 3.jpg', 'SHORT GLOBO ACETINADO COM RENDA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (173, 62, 'http://localhost:9000/atelier/products/short-globo-acetinado-com-renda/SHORT GLOBO ACETINADO COM RENDA CAPA.jpg', 'SHORT GLOBO ACETINADO COM RENDA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (174, 62, 'http://localhost:9000/atelier/products/short-globo-acetinado-com-renda/SHORT GLOBO ACETINADO COM RENDA HOVER.jpg', 'SHORT GLOBO ACETINADO COM RENDA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (175, 63, 'http://localhost:9000/atelier/products/short-saia-de-veludo-cotele-com-cinto/SHORT-SAIA DE VELUDO COTELÊ COM CINTO 3.jpg', 'SHORT-SAIA DE VELUDO COTELÊ COM CINTO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (176, 63, 'http://localhost:9000/atelier/products/short-saia-de-veludo-cotele-com-cinto/SHORT-SAIA DE VELUDO COTELÊ COM CINTO CAPA.jpg', 'SHORT-SAIA DE VELUDO COTELÊ COM CINTO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (177, 63, 'http://localhost:9000/atelier/products/short-saia-de-veludo-cotele-com-cinto/SHORT-SAIA DE VELUDO COTELÊ COM CINTO HOVER.jpg', 'SHORT-SAIA DE VELUDO COTELÊ COM CINTO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (178, 64, 'http://localhost:9000/atelier/products/shorts-com-babado/SHORTS COM BABADO 3.jpg', 'SHORTS COM BABADO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (179, 64, 'http://localhost:9000/atelier/products/shorts-com-babado/SHORTS COM BABADO CAPA.jpg', 'SHORTS COM BABADO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (180, 64, 'http://localhost:9000/atelier/products/shorts-com-babado/SHORTS COM BABADO HOVER.jpg', 'SHORTS COM BABADO HOVER.jpg', 2, false);

-- ── FEMININO / BLAZERS_COLETES ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (181, 65, 'http://localhost:9000/atelier/products/blazer-acinturado-com-ombreiras/BLAZER ACINTURADO COM OMBREIRAS 3.jpg', 'BLAZER ACINTURADO COM OMBREIRAS 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (182, 65, 'http://localhost:9000/atelier/products/blazer-acinturado-com-ombreiras/BLAZER ACINTURADO COM OMBREIRAS CAPA.jpg', 'BLAZER ACINTURADO COM OMBREIRAS CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (183, 65, 'http://localhost:9000/atelier/products/blazer-acinturado-com-ombreiras/BLAZER ACINTURADO COM OMBREIRAS HOVER.jpg', 'BLAZER ACINTURADO COM OMBREIRAS HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (184, 66, 'http://localhost:9000/atelier/products/blazer-cruzado/BLAZER CRUZADO 3.jpg', 'BLAZER CRUZADO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (185, 66, 'http://localhost:9000/atelier/products/blazer-cruzado/BLAZER CRUZADO CAPA.jpg', 'BLAZER CRUZADO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (186, 66, 'http://localhost:9000/atelier/products/blazer-cruzado/BLAZER CRUZADO HOVER.jpg', 'BLAZER CRUZADO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (187, 67, 'http://localhost:9000/atelier/products/blazer-cruzado/BLAZER CRUZADO COLOR2 FOTO1.jpg', 'BLAZER CRUZADO COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (188, 67, 'http://localhost:9000/atelier/products/blazer-cruzado/BLAZER CRUZADO COLOR2 FOTO2.jpg', 'BLAZER CRUZADO COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (189, 67, 'http://localhost:9000/atelier/products/blazer-cruzado/BLAZER CRUZADO COLOR2 FOTO3.jpg', 'BLAZER CRUZADO COLOR2 FOTO3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (190, 68, 'http://localhost:9000/atelier/products/blazer-cruzado/BLAZER CRUZADO COLOR3 FOTO1.jpg', 'BLAZER CRUZADO COLOR3 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (191, 68, 'http://localhost:9000/atelier/products/blazer-cruzado/BLAZER CRUZADO COLOR3 FOTO2.jpg', 'BLAZER CRUZADO COLOR3 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (192, 69, 'http://localhost:9000/atelier/products/blazer-curto-xadrez/BLAZER CURTO XADREZ 3.jpg', 'BLAZER CURTO XADREZ 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (193, 69, 'http://localhost:9000/atelier/products/blazer-curto-xadrez/BLAZER CURTO XADREZ CAPA.jpg', 'BLAZER CURTO XADREZ CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (194, 69, 'http://localhost:9000/atelier/products/blazer-curto-xadrez/BLAZER CURTO XADREZ HOVER.jpg', 'BLAZER CURTO XADREZ HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (195, 70, 'http://localhost:9000/atelier/products/blazer-de-veludo-debrum-em-contraste/BLAZER DE VELUDO DEBRUM EM CONTRASTE 3.jpg', 'BLAZER DE VELUDO DEBRUM EM CONTRASTE 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (196, 70, 'http://localhost:9000/atelier/products/blazer-de-veludo-debrum-em-contraste/BLAZER DE VELUDO DEBRUM EM CONTRASTE 4.jpg', 'BLAZER DE VELUDO DEBRUM EM CONTRASTE 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (197, 70, 'http://localhost:9000/atelier/products/blazer-de-veludo-debrum-em-contraste/BLAZER DE VELUDO DEBRUM EM CONTRASTE CAPA.jpg', 'BLAZER DE VELUDO DEBRUM EM CONTRASTE CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (198, 70, 'http://localhost:9000/atelier/products/blazer-de-veludo-debrum-em-contraste/BLAZER DE VELUDO DEBRUM EM CONTRASTE HOVER.jpg', 'BLAZER DE VELUDO DEBRUM EM CONTRASTE HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (199, 71, 'http://localhost:9000/atelier/products/blazer-de-veludo-debrum-em-contraste/BLAZER DE VELUDO DEBRUM EM CONTRASTE COLOR2 FOTO1.jpg', 'BLAZER DE VELUDO DEBRUM EM CONTRASTE COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (200, 71, 'http://localhost:9000/atelier/products/blazer-de-veludo-debrum-em-contraste/BLAZER DE VELUDO DEBRUM EM CONTRASTE COLOR2 FOTO2.jpg', 'BLAZER DE VELUDO DEBRUM EM CONTRASTE COLOR2 FOTO2.jpg', 2, false);-- =============================================================
-- SQL gerado automaticamente a partir das fotos de produtos
-- Pasta raiz: /home/thierry/development/atelier fotos
-- =============================================================

-- IMPORTANTE: Ajuste os hex_codes das variantes (COLOR2, COLOR3...)
-- e os preços antes de rodar em produção.

-- ─── PRODUTOS ─────────────────────────────────────────────────

-- ── FEMININO / VESTIDOS ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (49, 'Vestido Ampla Estampado', 'vestido-ampla-estampado', 'Vestido Ampla Estampado - peça exclusiva da coleção TSM Atelier.', 599.00, 'DRESS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (50, 'Vestido Curto Com Babado', 'vestido-curto-com-babado', 'Vestido Curto Com Babado - peça exclusiva da coleção TSM Atelier.', 599.00, 'DRESS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (51, 'Vestido Drapeado Gola Alta', 'vestido-drapeado-gola-alta', 'Vestido Drapeado Gola Alta - peça exclusiva da coleção TSM Atelier.', 599.00, 'DRESS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (52, 'Vestido Longo Acetinado Estampado', 'vestido-longo-acetinado-estampado', 'Vestido Longo Acetinado Estampado - peça exclusiva da coleção TSM Atelier.', 599.00, 'DRESS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (53, 'Vestido Longo Halter', 'vestido-longo-halter', 'Vestido Longo Halter - peça exclusiva da coleção TSM Atelier.', 599.00, 'DRESS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (54, 'Vestido Midi Com Cinto', 'vestido-midi-com-cinto', 'Vestido Midi Com Cinto - peça exclusiva da coleção TSM Atelier.', 599.00, 'DRESS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / CALCAS ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (55, 'Calça Acetinada Estampada', 'calca-acetinada-estampada', 'Calça Acetinada Estampada - peça exclusiva da coleção TSM Atelier.', 599.00, 'PANTS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (56, 'Calça Com Pregas', 'calca-com-pregas', 'Calça Com Pregas - peça exclusiva da coleção TSM Atelier.', 599.00, 'PANTS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (57, 'Calça Reta Zw Collection', 'calca-reta-zw-collection', 'Calça Reta Zw Collection - peça exclusiva da coleção TSM Atelier.', 599.00, 'PANTS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (58, 'Calça Xadrez Com Tiras', 'calca-xadrez-com-tiras', 'Calça Xadrez Com Tiras - peça exclusiva da coleção TSM Atelier.', 599.00, 'PANTS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / CAMISAS_BLUSAS ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (59, 'Camisa Acetinada Com Botões Dourados', 'camisa-acetinada-com-botoes-dourados', 'Camisa Acetinada Com Botões Dourados - peça exclusiva da coleção TSM Atelier.', 599.00, 'BLOUSE', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (60, 'Camisa Gasa Oversize Com Bolso', 'camisa-gasa-oversize-com-bolso', 'Camisa Gasa Oversize Com Bolso - peça exclusiva da coleção TSM Atelier.', 599.00, 'BLOUSE', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (61, 'Camisa Listrada Com Laço', 'camisa-listrada-com-laco', 'Camisa Listrada Com Laço - peça exclusiva da coleção TSM Atelier.', 599.00, 'BLOUSE', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (62, 'Camisa Listrada Cruzada', 'camisa-listrada-cruzada', 'Camisa Listrada Cruzada - peça exclusiva da coleção TSM Atelier.', 599.00, 'BLOUSE', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / CAMISETAS ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (63, 'Camiseta Algodão 100% Botões', 'camiseta-algodao-100-botoes', 'Camiseta Algodão 100% Botões - peça exclusiva da coleção TSM Atelier.', 599.00, 'T_SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (64, 'Camiseta Algodão 100% Botões Hover', 'camiseta-algodao-100-botoes-hover', 'Camiseta Algodão 100% Botões Hover - peça exclusiva da coleção TSM Atelier.', 599.00, 'T_SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (65, 'Camiseta Com Texto Em Contraste', 'camiseta-com-texto-em-contraste', 'Camiseta Com Texto Em Contraste - peça exclusiva da coleção TSM Atelier.', 599.00, 'T_SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (66, 'Camiseta Interlock De Manga Curta', 'camiseta-interlock-de-manga-curta', 'Camiseta Interlock De Manga Curta - peça exclusiva da coleção TSM Atelier.', 599.00, 'T_SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (67, 'Camiseta Listrada Com Texto', 'camiseta-listrada-com-texto', 'Camiseta Listrada Com Texto - peça exclusiva da coleção TSM Atelier.', 599.00, 'T_SHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / CASACOS ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (68, 'Casaco Com Lã', 'casaco-com-la', 'Casaco Com Lã - peça exclusiva da coleção TSM Atelier.', 599.00, 'COAT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (69, 'Casaco Curto De Lã', 'casaco-curto-de-la', 'Casaco Curto De Lã - peça exclusiva da coleção TSM Atelier.', 599.00, 'COAT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (70, 'Casaco Curto De Pelo Sintético', 'casaco-curto-de-pelo-sintetico', 'Casaco Curto De Pelo Sintético - peça exclusiva da coleção TSM Atelier.', 599.00, 'COAT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (71, 'Casaco De Mistura De Lã', 'casaco-de-mistura-de-la', 'Casaco De Mistura De Lã - peça exclusiva da coleção TSM Atelier.', 599.00, 'COAT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (72, 'Casaco Lã Zw', 'casaco-la-zw', 'Casaco Lã Zw - peça exclusiva da coleção TSM Atelier.', 599.00, 'COAT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (73, 'Casaco Ombreiras Soft', 'casaco-ombreiras-soft', 'Casaco Ombreiras Soft - peça exclusiva da coleção TSM Atelier.', 599.00, 'COAT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / JAQUETAS ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (74, 'Casaco De Plumas Curto', 'casaco-de-plumas-curto', 'Casaco De Plumas Curto - peça exclusiva da coleção TSM Atelier.', 599.00, 'JACKET', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (75, 'Jaqueta Com Efeito Suede De Gola Alta', 'jaqueta-com-efeito-suede-de-gola-alta', 'Jaqueta Com Efeito Suede De Gola Alta - peça exclusiva da coleção TSM Atelier.', 599.00, 'JACKET', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (76, 'Jaqueta De Poliuretano', 'jaqueta-de-poliuretano', 'Jaqueta De Poliuretano - peça exclusiva da coleção TSM Atelier.', 599.00, 'JACKET', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (77, 'Jaqueta De Veludo Cotelê Com Contraste', 'jaqueta-de-veludo-cotele-com-contraste', 'Jaqueta De Veludo Cotelê Com Contraste - peça exclusiva da coleção TSM Atelier.', 599.00, 'JACKET', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (78, 'Jaqueta Reversível Dupla Face', 'jaqueta-reversivel-dupla-face', 'Jaqueta Reversível Dupla Face - peça exclusiva da coleção TSM Atelier.', 599.00, 'JACKET', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / JEANS ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (79, 'Jeans Trf Skinny Cintura Alta', 'jeans-trf-skinny-cintura-alta', 'Jeans Trf Skinny Cintura Alta - peça exclusiva da coleção TSM Atelier.', 599.00, 'JEANS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (80, 'Jeans Z.01 Mom Fit Cintura Alta Com Vinco Passado', 'jeans-z01-mom-fit-cintura-alta-com-vinco-passado', 'Jeans Z.01 Mom Fit Cintura Alta Com Vinco Passado - peça exclusiva da coleção TSM Atelier.', 599.00, 'JEANS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (81, 'Jeans Z1975 Barrel Cintura Média', 'jeans-z1975-barrel-cintura-media', 'Jeans Z1975 Barrel Cintura Média - peça exclusiva da coleção TSM Atelier.', 599.00, 'JEANS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (82, 'Jeans Z1975 Cropped De Cintura Média', 'jeans-z1975-cropped-de-cintura-media', 'Jeans Z1975 Cropped De Cintura Média - peça exclusiva da coleção TSM Atelier.', 599.00, 'JEANS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (83, 'Jeans Zw Collection Wide Leg Cintura Alta', 'jeans-zw-collection-wide-leg-cintura-alta', 'Jeans Zw Collection Wide Leg Cintura Alta - peça exclusiva da coleção TSM Atelier.', 599.00, 'JEANS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (84, 'Jeans Zw Collection Wide Leg Cintura Alta Foto2', 'jeans-zw-collection-wide-leg-cintura-alta-foto2', 'Jeans Zw Collection Wide Leg Cintura Alta Foto2 - peça exclusiva da coleção TSM Atelier.', 599.00, 'JEANS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / SHORTS_BERMUDAS ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (85, 'Short De Poliuretano', 'short-de-poliuretano', 'Short De Poliuretano - peça exclusiva da coleção TSM Atelier.', 599.00, 'SHORTS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (86, 'Short Globo Acetinado Com Renda', 'short-globo-acetinado-com-renda', 'Short Globo Acetinado Com Renda - peça exclusiva da coleção TSM Atelier.', 599.00, 'SHORTS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (87, 'Short-Saia De Veludo Cotelê Com Cinto', 'short-saia-de-veludo-cotele-com-cinto', 'Short-Saia De Veludo Cotelê Com Cinto - peça exclusiva da coleção TSM Atelier.', 599.00, 'SHORTS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (88, 'Shorts Com Babado', 'shorts-com-babado', 'Shorts Com Babado - peça exclusiva da coleção TSM Atelier.', 599.00, 'SHORTS', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / BLAZERS_COLETES ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (89, 'Blazer Acinturado Com Ombreiras', 'blazer-acinturado-com-ombreiras', 'Blazer Acinturado Com Ombreiras - peça exclusiva da coleção TSM Atelier.', 599.00, 'BLAZER', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (90, 'Blazer Cruzado', 'blazer-cruzado', 'Blazer Cruzado - peça exclusiva da coleção TSM Atelier.', 599.00, 'BLAZER', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (91, 'Blazer Curto Xadrez', 'blazer-curto-xadrez', 'Blazer Curto Xadrez - peça exclusiva da coleção TSM Atelier.', 599.00, 'BLAZER', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (92, 'Blazer De Veludo Debrum Em Contraste', 'blazer-de-veludo-debrum-em-contraste', 'Blazer De Veludo Debrum Em Contraste - peça exclusiva da coleção TSM Atelier.', 599.00, 'BLAZER', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ── FEMININO / MOLETONS ──────────────────
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (93, 'Calça De Moletom Básica Reta Com Bainha Ajustável', 'calca-de-moletom-basica-reta-com-bainha-ajustavel', 'Calça De Moletom Básica Reta Com Bainha Ajustável - peça exclusiva da coleção TSM Atelier.', 599.00, 'SWEATSHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (94, 'Casaco De Moletom Com Zíper', 'casaco-de-moletom-com-ziper', 'Casaco De Moletom Com Zíper - peça exclusiva da coleção TSM Atelier.', 599.00, 'SWEATSHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (95, 'Moletom Com Efeito De Lavagem Don Clemente', 'moletom-com-efeito-de-lavagem-don-clemente', 'Moletom Com Efeito De Lavagem Don Clemente - peça exclusiva da coleção TSM Atelier.', 599.00, 'SWEATSHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (96, 'Moletom Com Mensagem E Capuz', 'moletom-com-mensagem-e-capuz', 'Moletom Com Mensagem E Capuz - peça exclusiva da coleção TSM Atelier.', 599.00, 'SWEATSHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());
INSERT INTO products (id, name, slug, description, price, category, status, target_audience, created_at, updated_at)
VALUES (97, 'Moletom Com Mensagem Em Contraste', 'moletom-com-mensagem-em-contraste', 'Moletom Com Mensagem Em Contraste - peça exclusiva da coleção TSM Atelier.', 599.00, 'SWEATSHIRT', 'DRAFT', 'WOMENSWEAR', NOW(), NOW());

-- ─── CORES ────────────────────────────────────────────────────

-- ── FEMININO / VESTIDOS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (72, 49, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (73, 50, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (74, 51, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (75, 51, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (76, 52, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (77, 53, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (78, 53, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (79, 54, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (80, 54, 'Branco', '#FFFFFF', 0);

-- ── FEMININO / CALCAS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (81, 55, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (82, 56, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (83, 56, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (84, 57, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (85, 58, 'Preto', '#000000', 0);

-- ── FEMININO / CAMISAS_BLUSAS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (86, 59, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (87, 59, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (88, 60, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (89, 61, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (90, 61, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (91, 62, 'Preto', '#000000', 0);

-- ── FEMININO / CAMISETAS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (92, 63, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (93, 64, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (94, 65, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (95, 66, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (96, 67, 'Preto', '#000000', 0);

-- ── FEMININO / CASACOS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (97, 68, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (98, 69, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (99, 70, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (100, 71, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (101, 72, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (102, 72, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (103, 73, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (104, 73, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (105, 73, 'Bege', '#F5F0E8', 0);

-- ── FEMININO / JAQUETAS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (106, 74, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (107, 75, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (108, 75, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (109, 76, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (110, 77, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (111, 77, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (112, 78, 'Preto', '#000000', 0);

-- ── FEMININO / JEANS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (113, 79, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (114, 79, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (115, 79, 'Bege', '#F5F0E8', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (116, 80, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (117, 81, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (118, 82, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (119, 82, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (120, 82, 'Bege', '#F5F0E8', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (121, 83, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (122, 83, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (123, 83, 'Bege', '#F5F0E8', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (124, 84, 'Preto', '#000000', 0);

-- ── FEMININO / SHORTS_BERMUDAS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (125, 85, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (126, 85, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (127, 86, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (128, 87, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (129, 88, 'Preto', '#000000', 0);

-- ── FEMININO / BLAZERS_COLETES ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (130, 89, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (131, 90, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (132, 90, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (133, 90, 'Bege', '#F5F0E8', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (134, 91, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (135, 92, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (136, 92, 'Branco', '#FFFFFF', 0);

-- ── FEMININO / MOLETONS ──────────────────
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (137, 93, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (138, 94, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (139, 94, 'Branco', '#FFFFFF', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (140, 94, 'Bege', '#F5F0E8', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (141, 95, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (142, 96, 'Preto', '#000000', 0);
INSERT INTO product_colors (id, product_id, name, hex_code, version)
VALUES (143, 97, 'Preto', '#000000', 0);

-- ─── IMAGENS ──────────────────────────────────────────────────

-- ── FEMININO / VESTIDOS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (201, 72, 'http://localhost:9000/atelier/products/vestido-ampla-estampado/VESTIDO AMPLA ESTAMPADO 3.jpg', 'VESTIDO AMPLA ESTAMPADO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (202, 72, 'http://localhost:9000/atelier/products/vestido-ampla-estampado/VESTIDO AMPLA ESTAMPADO CAPA.jpg', 'VESTIDO AMPLA ESTAMPADO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (203, 72, 'http://localhost:9000/atelier/products/vestido-ampla-estampado/VESTIDO AMPLA ESTAMPADO HOVER.jpg', 'VESTIDO AMPLA ESTAMPADO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (204, 73, 'http://localhost:9000/atelier/products/vestido-curto-com-babado/VESTIDO CURTO COM BABADO 3.jpg', 'VESTIDO CURTO COM BABADO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (205, 73, 'http://localhost:9000/atelier/products/vestido-curto-com-babado/VESTIDO CURTO COM BABADO CAPA.jpg', 'VESTIDO CURTO COM BABADO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (206, 73, 'http://localhost:9000/atelier/products/vestido-curto-com-babado/VESTIDO CURTO COM BABADO HOVER.jpg', 'VESTIDO CURTO COM BABADO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (207, 74, 'http://localhost:9000/atelier/products/vestido-drapeado-gola-alta/VESTIDO DRAPEADO GOLA ALTA 3.jpg', 'VESTIDO DRAPEADO GOLA ALTA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (208, 74, 'http://localhost:9000/atelier/products/vestido-drapeado-gola-alta/VESTIDO DRAPEADO GOLA ALTA 4.jpg', 'VESTIDO DRAPEADO GOLA ALTA 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (209, 74, 'http://localhost:9000/atelier/products/vestido-drapeado-gola-alta/VESTIDO DRAPEADO GOLA ALTA CAPA.jpg', 'VESTIDO DRAPEADO GOLA ALTA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (210, 74, 'http://localhost:9000/atelier/products/vestido-drapeado-gola-alta/VESTIDO DRAPEADO GOLA ALTA HOVER.jpg', 'VESTIDO DRAPEADO GOLA ALTA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (211, 75, 'http://localhost:9000/atelier/products/vestido-drapeado-gola-alta/VESTIDO DRAPEADO GOLA ALTA COLOR2 FOTO1.jpg', 'VESTIDO DRAPEADO GOLA ALTA COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (212, 75, 'http://localhost:9000/atelier/products/vestido-drapeado-gola-alta/VESTIDO DRAPEADO GOLA ALTA COLOR2 FOTO2.jpg', 'VESTIDO DRAPEADO GOLA ALTA COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (213, 76, 'http://localhost:9000/atelier/products/vestido-longo-acetinado-estampado/VESTIDO LONGO ACETINADO ESTAMPADO 3.jpg', 'VESTIDO LONGO ACETINADO ESTAMPADO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (214, 76, 'http://localhost:9000/atelier/products/vestido-longo-acetinado-estampado/VESTIDO LONGO ACETINADO ESTAMPADO CAPA.jpg', 'VESTIDO LONGO ACETINADO ESTAMPADO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (215, 76, 'http://localhost:9000/atelier/products/vestido-longo-acetinado-estampado/VESTIDO LONGO ACETINADO ESTAMPADO HOVER.jpg', 'VESTIDO LONGO ACETINADO ESTAMPADO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (216, 77, 'http://localhost:9000/atelier/products/vestido-longo-halter/VESTIDO LONGO HALTER 3.jpg', 'VESTIDO LONGO HALTER 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (217, 77, 'http://localhost:9000/atelier/products/vestido-longo-halter/VESTIDO LONGO HALTER 4.jpg', 'VESTIDO LONGO HALTER 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (218, 77, 'http://localhost:9000/atelier/products/vestido-longo-halter/VESTIDO LONGO HALTER CAPA.jpg', 'VESTIDO LONGO HALTER CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (219, 77, 'http://localhost:9000/atelier/products/vestido-longo-halter/VESTIDO LONGO HALTER HOVER.jpg', 'VESTIDO LONGO HALTER HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (220, 78, 'http://localhost:9000/atelier/products/vestido-longo-halter/VESTIDO LONGO HALTER COLOR2 FOTO1.jpg', 'VESTIDO LONGO HALTER COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (221, 78, 'http://localhost:9000/atelier/products/vestido-longo-halter/VESTIDO LONGO HALTER COLOR2 FOTO2.jpg', 'VESTIDO LONGO HALTER COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (222, 79, 'http://localhost:9000/atelier/products/vestido-midi-com-cinto/VESTIDO MIDI COM CINTO 3.jpg', 'VESTIDO MIDI COM CINTO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (223, 79, 'http://localhost:9000/atelier/products/vestido-midi-com-cinto/VESTIDO MIDI COM CINTO CAPA.jpg', 'VESTIDO MIDI COM CINTO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (224, 79, 'http://localhost:9000/atelier/products/vestido-midi-com-cinto/VESTIDO MIDI COM CINTO HOVER.jpg', 'VESTIDO MIDI COM CINTO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (225, 80, 'http://localhost:9000/atelier/products/vestido-midi-com-cinto/VESTIDO MIDI COM CINTO COLOR2 FOTO1.jpg', 'VESTIDO MIDI COM CINTO COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (226, 80, 'http://localhost:9000/atelier/products/vestido-midi-com-cinto/VESTIDO MIDI COM CINTO COLOR2 FOTO2.jpg', 'VESTIDO MIDI COM CINTO COLOR2 FOTO2.jpg', 2, false);

-- ── FEMININO / CALCAS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (227, 81, 'http://localhost:9000/atelier/products/calca-acetinada-estampada/CALÇA ACETINADA ESTAMPADA 3.jpg', 'CALÇA ACETINADA ESTAMPADA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (228, 81, 'http://localhost:9000/atelier/products/calca-acetinada-estampada/CALÇA ACETINADA ESTAMPADA 4.jpg', 'CALÇA ACETINADA ESTAMPADA 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (229, 81, 'http://localhost:9000/atelier/products/calca-acetinada-estampada/CALÇA ACETINADA ESTAMPADA CAPA.jpg', 'CALÇA ACETINADA ESTAMPADA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (230, 81, 'http://localhost:9000/atelier/products/calca-acetinada-estampada/CALÇA ACETINADA ESTAMPADA HOVER.jpg', 'CALÇA ACETINADA ESTAMPADA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (231, 82, 'http://localhost:9000/atelier/products/calca-com-pregas/CALÇA COM PREGAS 3.jpg', 'CALÇA COM PREGAS 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (232, 82, 'http://localhost:9000/atelier/products/calca-com-pregas/CALÇA COM PREGAS CAPA.jpg', 'CALÇA COM PREGAS CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (233, 82, 'http://localhost:9000/atelier/products/calca-com-pregas/CALÇA COM PREGAS HOVER.jpg', 'CALÇA COM PREGAS HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (234, 83, 'http://localhost:9000/atelier/products/calca-com-pregas/CALÇA COM PREGAS COLOR2 FOTO1.jpg', 'CALÇA COM PREGAS COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (235, 83, 'http://localhost:9000/atelier/products/calca-com-pregas/CALÇA COM PREGAS COLOR2 FOTO2.jpg', 'CALÇA COM PREGAS COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (236, 84, 'http://localhost:9000/atelier/products/calca-reta-zw-collection/CALÇA RETA ZW COLLECTION 3.jpg', 'CALÇA RETA ZW COLLECTION 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (237, 84, 'http://localhost:9000/atelier/products/calca-reta-zw-collection/CALÇA RETA ZW COLLECTION CAPA.jpg', 'CALÇA RETA ZW COLLECTION CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (238, 84, 'http://localhost:9000/atelier/products/calca-reta-zw-collection/CALÇA RETA ZW COLLECTION HOVER.jpg', 'CALÇA RETA ZW COLLECTION HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (239, 85, 'http://localhost:9000/atelier/products/calca-xadrez-com-tiras/CALÇA XADREZ COM TIRAS 3.jpg', 'CALÇA XADREZ COM TIRAS 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (240, 85, 'http://localhost:9000/atelier/products/calca-xadrez-com-tiras/CALÇA XADREZ COM TIRAS CAPA.jpg', 'CALÇA XADREZ COM TIRAS CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (241, 85, 'http://localhost:9000/atelier/products/calca-xadrez-com-tiras/CALÇA XADREZ COM TIRAS HOVER.jpg', 'CALÇA XADREZ COM TIRAS HOVER.jpg', 2, false);

-- ── FEMININO / CAMISAS_BLUSAS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (242, 86, 'http://localhost:9000/atelier/products/camisa-acetinada-com-botoes-dourados/CAMISA ACETINADA COM BOTÕES DOURADOS 3.jpg', 'CAMISA ACETINADA COM BOTÕES DOURADOS 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (243, 86, 'http://localhost:9000/atelier/products/camisa-acetinada-com-botoes-dourados/CAMISA ACETINADA COM BOTÕES DOURADOS CAPA.jpg', 'CAMISA ACETINADA COM BOTÕES DOURADOS CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (244, 86, 'http://localhost:9000/atelier/products/camisa-acetinada-com-botoes-dourados/CAMISA ACETINADA COM BOTÕES DOURADOS HOVER.jpg', 'CAMISA ACETINADA COM BOTÕES DOURADOS HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (245, 87, 'http://localhost:9000/atelier/products/camisa-acetinada-com-botoes-dourados/CAMISA ACETINADA COM BOTÕES DOURADOS COLOR2 FOTO1.jpg', 'CAMISA ACETINADA COM BOTÕES DOURADOS COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (246, 87, 'http://localhost:9000/atelier/products/camisa-acetinada-com-botoes-dourados/CAMISA ACETINADA COM BOTÕES DOURADOS COLOR2 FOTO2.jpg', 'CAMISA ACETINADA COM BOTÕES DOURADOS COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (247, 87, 'http://localhost:9000/atelier/products/camisa-acetinada-com-botoes-dourados/CAMISA ACETINADA COM BOTÕES DOURADOS COLOR2 FOTO3.jpg', 'CAMISA ACETINADA COM BOTÕES DOURADOS COLOR2 FOTO3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (248, 88, 'http://localhost:9000/atelier/products/camisa-gasa-oversize-com-bolso/CAMISA GASA OVERSIZE COM BOLSO CAPA.jpg', 'CAMISA GASA OVERSIZE COM BOLSO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (249, 88, 'http://localhost:9000/atelier/products/camisa-gasa-oversize-com-bolso/CAMISA GASA OVERSIZE COM BOLSO HOVER.jpg', 'CAMISA GASA OVERSIZE COM BOLSO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (250, 89, 'http://localhost:9000/atelier/products/camisa-listrada-com-laco/CAMISA LISTRADA COM LAÇO 3.jpg', 'CAMISA LISTRADA COM LAÇO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (251, 89, 'http://localhost:9000/atelier/products/camisa-listrada-com-laco/CAMISA LISTRADA COM LAÇO CAPA.jpg', 'CAMISA LISTRADA COM LAÇO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (252, 89, 'http://localhost:9000/atelier/products/camisa-listrada-com-laco/CAMISA LISTRADA COM LAÇO HOVER.jpg', 'CAMISA LISTRADA COM LAÇO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (253, 90, 'http://localhost:9000/atelier/products/camisa-listrada-com-laco/CAMISA LISTRADA COM LAÇO COLOR2 FOTO1.jpg', 'CAMISA LISTRADA COM LAÇO COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (254, 90, 'http://localhost:9000/atelier/products/camisa-listrada-com-laco/CAMISA LISTRADA COM LAÇO COLOR2 FOTO2.jpg', 'CAMISA LISTRADA COM LAÇO COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (255, 91, 'http://localhost:9000/atelier/products/camisa-listrada-cruzada/CAMISA LISTRADA CRUZADA 3.jpg', 'CAMISA LISTRADA CRUZADA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (256, 91, 'http://localhost:9000/atelier/products/camisa-listrada-cruzada/CAMISA LISTRADA CRUZADA CAPA.jpg', 'CAMISA LISTRADA CRUZADA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (257, 91, 'http://localhost:9000/atelier/products/camisa-listrada-cruzada/CAMISA LISTRADA CRUZADA HOVER.jpg', 'CAMISA LISTRADA CRUZADA HOVER.jpg', 2, false);

-- ── FEMININO / CAMISETAS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (258, 92, 'http://localhost:9000/atelier/products/camiseta-algodao-100-botoes/CAMISETA ALGODÃO 100% BOTÕES 3.jpg', 'CAMISETA ALGODÃO 100% BOTÕES 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (259, 92, 'http://localhost:9000/atelier/products/camiseta-algodao-100-botoes/CAMISETA ALGODÃO 100% BOTÕES CAPA.jpg', 'CAMISETA ALGODÃO 100% BOTÕES CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (260, 92, 'http://localhost:9000/atelier/products/camiseta-algodao-100-botoes/CAMISETA ALGODÃO 100% BOTÕES HOVER.jpg', 'CAMISETA ALGODÃO 100% BOTÕES HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (261, 93, 'http://localhost:9000/atelier/products/camiseta-algodao-100-botoes-hover/CAMISETA ALGODÃO 100% BOTÕES HOVER COLOR2 FOTO1.jpg', 'CAMISETA ALGODÃO 100% BOTÕES HOVER COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (262, 93, 'http://localhost:9000/atelier/products/camiseta-algodao-100-botoes-hover/CAMISETA ALGODÃO 100% BOTÕES HOVER COLOR2 FOTO2.jpg', 'CAMISETA ALGODÃO 100% BOTÕES HOVER COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (263, 94, 'http://localhost:9000/atelier/products/camiseta-com-texto-em-contraste/CAMISETA COM TEXTO EM CONTRASTE 3.jpg', 'CAMISETA COM TEXTO EM CONTRASTE 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (264, 94, 'http://localhost:9000/atelier/products/camiseta-com-texto-em-contraste/CAMISETA COM TEXTO EM CONTRASTE CAPA.jpg', 'CAMISETA COM TEXTO EM CONTRASTE CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (265, 94, 'http://localhost:9000/atelier/products/camiseta-com-texto-em-contraste/CAMISETA COM TEXTO EM CONTRASTE HOVER.jpg', 'CAMISETA COM TEXTO EM CONTRASTE HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (266, 95, 'http://localhost:9000/atelier/products/camiseta-interlock-de-manga-curta/CAMISETA INTERLOCK DE MANGA CURTA 3.jpg', 'CAMISETA INTERLOCK DE MANGA CURTA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (267, 95, 'http://localhost:9000/atelier/products/camiseta-interlock-de-manga-curta/CAMISETA INTERLOCK DE MANGA CURTA 4.jpg', 'CAMISETA INTERLOCK DE MANGA CURTA 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (268, 95, 'http://localhost:9000/atelier/products/camiseta-interlock-de-manga-curta/CAMISETA INTERLOCK DE MANGA CURTA CAPA.jpg', 'CAMISETA INTERLOCK DE MANGA CURTA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (269, 95, 'http://localhost:9000/atelier/products/camiseta-interlock-de-manga-curta/CAMISETA INTERLOCK DE MANGA CURTA HOVER.jpg', 'CAMISETA INTERLOCK DE MANGA CURTA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (270, 96, 'http://localhost:9000/atelier/products/camiseta-listrada-com-texto/CAMISETA LISTRADA COM TEXTO 3.jpg', 'CAMISETA LISTRADA COM TEXTO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (271, 96, 'http://localhost:9000/atelier/products/camiseta-listrada-com-texto/CAMISETA LISTRADA COM TEXTO CAPA.jpg', 'CAMISETA LISTRADA COM TEXTO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (272, 96, 'http://localhost:9000/atelier/products/camiseta-listrada-com-texto/CAMISETA LISTRADA COM TEXTO HOVER.jpg', 'CAMISETA LISTRADA COM TEXTO HOVER.jpg', 2, false);

-- ── FEMININO / CASACOS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (273, 97, 'http://localhost:9000/atelier/products/casaco-com-la/CASACO COM LÃ CAPA.jpg', 'CASACO COM LÃ CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (274, 97, 'http://localhost:9000/atelier/products/casaco-com-la/CASACO COM LÃ HOVER.jpg', 'CASACO COM LÃ HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (275, 98, 'http://localhost:9000/atelier/products/casaco-curto-de-la/CASACO CURTO DE LÃ 3.jpg', 'CASACO CURTO DE LÃ 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (276, 98, 'http://localhost:9000/atelier/products/casaco-curto-de-la/CASACO CURTO DE LÃ CAPA.jpg', 'CASACO CURTO DE LÃ CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (277, 98, 'http://localhost:9000/atelier/products/casaco-curto-de-la/CASACO CURTO DE LÃ HOVER.jpg', 'CASACO CURTO DE LÃ HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (278, 99, 'http://localhost:9000/atelier/products/casaco-curto-de-pelo-sintetico/CASACO CURTO DE PELO SINTÉTICO 3.jpg', 'CASACO CURTO DE PELO SINTÉTICO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (279, 99, 'http://localhost:9000/atelier/products/casaco-curto-de-pelo-sintetico/CASACO CURTO DE PELO SINTÉTICO CAPA.jpg', 'CASACO CURTO DE PELO SINTÉTICO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (280, 99, 'http://localhost:9000/atelier/products/casaco-curto-de-pelo-sintetico/CASACO CURTO DE PELO SINTÉTICO HOVER.jpg', 'CASACO CURTO DE PELO SINTÉTICO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (281, 100, 'http://localhost:9000/atelier/products/casaco-de-mistura-de-la/CASACO DE MISTURA DE LÃ 3.jpg', 'CASACO DE MISTURA DE LÃ 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (282, 100, 'http://localhost:9000/atelier/products/casaco-de-mistura-de-la/CASACO DE MISTURA DE LÃ 4.jpg', 'CASACO DE MISTURA DE LÃ 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (283, 100, 'http://localhost:9000/atelier/products/casaco-de-mistura-de-la/CASACO DE MISTURA DE LÃ CAPA.jpg', 'CASACO DE MISTURA DE LÃ CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (284, 100, 'http://localhost:9000/atelier/products/casaco-de-mistura-de-la/CASACO DE MISTURA DE LÃ HOVER.jpg', 'CASACO DE MISTURA DE LÃ HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (285, 101, 'http://localhost:9000/atelier/products/casaco-la-zw/CASACO LÃ ZW 3.jpg', 'CASACO LÃ ZW 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (286, 101, 'http://localhost:9000/atelier/products/casaco-la-zw/CASACO LÃ ZW CAPA.jpg', 'CASACO LÃ ZW CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (287, 101, 'http://localhost:9000/atelier/products/casaco-la-zw/CASACO LÃ ZW HOVER.jpg', 'CASACO LÃ ZW HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (288, 102, 'http://localhost:9000/atelier/products/casaco-la-zw/CASACO LÃ ZW COLOR2 FOTO1.jpg', 'CASACO LÃ ZW COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (289, 102, 'http://localhost:9000/atelier/products/casaco-la-zw/CASACO LÃ ZW COLOR2 FOTO2.jpg', 'CASACO LÃ ZW COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (290, 102, 'http://localhost:9000/atelier/products/casaco-la-zw/CASACO LÃ ZW COLOR2 FOTO3.jpg', 'CASACO LÃ ZW COLOR2 FOTO3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (291, 103, 'http://localhost:9000/atelier/products/casaco-ombreiras-soft/CASACO OMBREIRAS SOFT 3.jpg', 'CASACO OMBREIRAS SOFT 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (292, 103, 'http://localhost:9000/atelier/products/casaco-ombreiras-soft/CASACO OMBREIRAS SOFT CAPA.jpg', 'CASACO OMBREIRAS SOFT CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (293, 103, 'http://localhost:9000/atelier/products/casaco-ombreiras-soft/CASACO OMBREIRAS SOFT HOVER.jpg', 'CASACO OMBREIRAS SOFT HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (294, 104, 'http://localhost:9000/atelier/products/casaco-ombreiras-soft/CASACO OMBREIRAS SOFT COLOR2 FOTO1.jpg', 'CASACO OMBREIRAS SOFT COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (295, 104, 'http://localhost:9000/atelier/products/casaco-ombreiras-soft/CASACO OMBREIRAS SOFT COLOR2 FOTO2.jpg', 'CASACO OMBREIRAS SOFT COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (296, 104, 'http://localhost:9000/atelier/products/casaco-ombreiras-soft/CASACO OMBREIRAS SOFT COLOR2 FOTO3.jpg', 'CASACO OMBREIRAS SOFT COLOR2 FOTO3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (297, 105, 'http://localhost:9000/atelier/products/casaco-ombreiras-soft/CASACO OMBREIRAS SOFT COLOR3 FOTO1.jpg', 'CASACO OMBREIRAS SOFT COLOR3 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (298, 105, 'http://localhost:9000/atelier/products/casaco-ombreiras-soft/CASACO OMBREIRAS SOFT COLOR3 FOTO2.jpg', 'CASACO OMBREIRAS SOFT COLOR3 FOTO2.jpg', 2, false);

-- ── FEMININO / JAQUETAS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (299, 106, 'http://localhost:9000/atelier/products/casaco-de-plumas-curto/CASACO DE PLUMAS CURTO 3.jpg', 'CASACO DE PLUMAS CURTO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (300, 106, 'http://localhost:9000/atelier/products/casaco-de-plumas-curto/CASACO DE PLUMAS CURTO CAPA.jpg', 'CASACO DE PLUMAS CURTO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (301, 106, 'http://localhost:9000/atelier/products/casaco-de-plumas-curto/CASACO DE PLUMAS CURTO HOVER.jpg', 'CASACO DE PLUMAS CURTO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (302, 107, 'http://localhost:9000/atelier/products/jaqueta-com-efeito-suede-de-gola-alta/JAQUETA COM EFEITO SUEDE DE GOLA ALTA 3.jpg', 'JAQUETA COM EFEITO SUEDE DE GOLA ALTA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (303, 107, 'http://localhost:9000/atelier/products/jaqueta-com-efeito-suede-de-gola-alta/JAQUETA COM EFEITO SUEDE DE GOLA ALTA 4.jpg', 'JAQUETA COM EFEITO SUEDE DE GOLA ALTA 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (304, 107, 'http://localhost:9000/atelier/products/jaqueta-com-efeito-suede-de-gola-alta/JAQUETA COM EFEITO SUEDE DE GOLA ALTA CAPA.jpg', 'JAQUETA COM EFEITO SUEDE DE GOLA ALTA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (305, 107, 'http://localhost:9000/atelier/products/jaqueta-com-efeito-suede-de-gola-alta/JAQUETA COM EFEITO SUEDE DE GOLA ALTA HOVER.jpg', 'JAQUETA COM EFEITO SUEDE DE GOLA ALTA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (306, 108, 'http://localhost:9000/atelier/products/jaqueta-com-efeito-suede-de-gola-alta/JAQUETA COM EFEITO SUEDE DE GOLA ALTA COLOR2 FOTO1.jpg', 'JAQUETA COM EFEITO SUEDE DE GOLA ALTA COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (307, 108, 'http://localhost:9000/atelier/products/jaqueta-com-efeito-suede-de-gola-alta/JAQUETA COM EFEITO SUEDE DE GOLA ALTA COLOR2 FOTO2.jpg', 'JAQUETA COM EFEITO SUEDE DE GOLA ALTA COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (308, 109, 'http://localhost:9000/atelier/products/jaqueta-de-poliuretano/JAQUETA DE POLIURETANO 3.jpg', 'JAQUETA DE POLIURETANO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (309, 109, 'http://localhost:9000/atelier/products/jaqueta-de-poliuretano/JAQUETA DE POLIURETANO CAPA.jpg', 'JAQUETA DE POLIURETANO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (310, 109, 'http://localhost:9000/atelier/products/jaqueta-de-poliuretano/JAQUETA DE POLIURETANO HOVER.jpg', 'JAQUETA DE POLIURETANO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (311, 110, 'http://localhost:9000/atelier/products/jaqueta-de-veludo-cotele-com-contraste/JAQUETA DE VELUDO COTELÊ COM CONTRASTE 3.jpg', 'JAQUETA DE VELUDO COTELÊ COM CONTRASTE 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (312, 110, 'http://localhost:9000/atelier/products/jaqueta-de-veludo-cotele-com-contraste/JAQUETA DE VELUDO COTELÊ COM CONTRASTE CAPA.jpg', 'JAQUETA DE VELUDO COTELÊ COM CONTRASTE CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (313, 110, 'http://localhost:9000/atelier/products/jaqueta-de-veludo-cotele-com-contraste/JAQUETA DE VELUDO COTELÊ COM CONTRASTE HOVER.jpg', 'JAQUETA DE VELUDO COTELÊ COM CONTRASTE HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (314, 111, 'http://localhost:9000/atelier/products/jaqueta-de-veludo-cotele-com-contraste/JAQUETA DE VELUDO COTELÊ COM CONTRASTE COLOR2 FOTO1.jpg', 'JAQUETA DE VELUDO COTELÊ COM CONTRASTE COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (315, 111, 'http://localhost:9000/atelier/products/jaqueta-de-veludo-cotele-com-contraste/JAQUETA DE VELUDO COTELÊ COM CONTRASTE COLOR2 FOTO2.jpg', 'JAQUETA DE VELUDO COTELÊ COM CONTRASTE COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (316, 112, 'http://localhost:9000/atelier/products/jaqueta-reversivel-dupla-face/JAQUETA REVERSÍVEL DUPLA FACE 3.jpg', 'JAQUETA REVERSÍVEL DUPLA FACE 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (317, 112, 'http://localhost:9000/atelier/products/jaqueta-reversivel-dupla-face/JAQUETA REVERSÍVEL DUPLA FACE 4.jpg', 'JAQUETA REVERSÍVEL DUPLA FACE 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (318, 112, 'http://localhost:9000/atelier/products/jaqueta-reversivel-dupla-face/JAQUETA REVERSÍVEL DUPLA FACE CAPA.jpg', 'JAQUETA REVERSÍVEL DUPLA FACE CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (319, 112, 'http://localhost:9000/atelier/products/jaqueta-reversivel-dupla-face/JAQUETA REVERSÍVEL DUPLA FACE HOVER.jpg', 'JAQUETA REVERSÍVEL DUPLA FACE HOVER.jpg', 2, false);

-- ── FEMININO / JEANS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (320, 113, 'http://localhost:9000/atelier/products/jeans-trf-skinny-cintura-alta/JEANS TRF SKINNY CINTURA ALTA 3.jpg', 'JEANS TRF SKINNY CINTURA ALTA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (321, 113, 'http://localhost:9000/atelier/products/jeans-trf-skinny-cintura-alta/JEANS TRF SKINNY CINTURA ALTA CAPA.jpg', 'JEANS TRF SKINNY CINTURA ALTA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (322, 113, 'http://localhost:9000/atelier/products/jeans-trf-skinny-cintura-alta/JEANS TRF SKINNY CINTURA ALTA HOVER.jpg', 'JEANS TRF SKINNY CINTURA ALTA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (323, 114, 'http://localhost:9000/atelier/products/jeans-trf-skinny-cintura-alta/JEANS TRF SKINNY CINTURA ALTA COLOR2 FOTO1.jpg', 'JEANS TRF SKINNY CINTURA ALTA COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (324, 114, 'http://localhost:9000/atelier/products/jeans-trf-skinny-cintura-alta/JEANS TRF SKINNY CINTURA ALTA COLOR2 FOTO2.jpg', 'JEANS TRF SKINNY CINTURA ALTA COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (325, 115, 'http://localhost:9000/atelier/products/jeans-trf-skinny-cintura-alta/JEANS TRF SKINNY CINTURA ALTA COLOR3 FOTO1.jpg', 'JEANS TRF SKINNY CINTURA ALTA COLOR3 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (326, 115, 'http://localhost:9000/atelier/products/jeans-trf-skinny-cintura-alta/JEANS TRF SKINNY CINTURA ALTA COLOR3 FOTO2.jpg', 'JEANS TRF SKINNY CINTURA ALTA COLOR3 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (327, 116, 'http://localhost:9000/atelier/products/jeans-z01-mom-fit-cintura-alta-com-vinco-passado/JEANS Z.01 MOM FIT CINTURA ALTA COM VINCO PASSADO 3.jpg', 'JEANS Z.01 MOM FIT CINTURA ALTA COM VINCO PASSADO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (328, 116, 'http://localhost:9000/atelier/products/jeans-z01-mom-fit-cintura-alta-com-vinco-passado/JEANS Z.01 MOM FIT CINTURA ALTA COM VINCO PASSADO CAPA.jpg', 'JEANS Z.01 MOM FIT CINTURA ALTA COM VINCO PASSADO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (329, 116, 'http://localhost:9000/atelier/products/jeans-z01-mom-fit-cintura-alta-com-vinco-passado/JEANS Z.01 MOM FIT CINTURA ALTA COM VINCO PASSADO HOVER.jpg', 'JEANS Z.01 MOM FIT CINTURA ALTA COM VINCO PASSADO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (330, 117, 'http://localhost:9000/atelier/products/jeans-z1975-barrel-cintura-media/JEANS Z1975 BARREL CINTURA MÉDIA 3.jpg', 'JEANS Z1975 BARREL CINTURA MÉDIA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (331, 117, 'http://localhost:9000/atelier/products/jeans-z1975-barrel-cintura-media/JEANS Z1975 BARREL CINTURA MÉDIA CAPA.jpg', 'JEANS Z1975 BARREL CINTURA MÉDIA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (332, 117, 'http://localhost:9000/atelier/products/jeans-z1975-barrel-cintura-media/JEANS Z1975 BARREL CINTURA MÉDIA HOVER.jpg', 'JEANS Z1975 BARREL CINTURA MÉDIA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (333, 118, 'http://localhost:9000/atelier/products/jeans-z1975-cropped-de-cintura-media/JEANS Z1975 CROPPED DE CINTURA MÉDIA 3.jpg', 'JEANS Z1975 CROPPED DE CINTURA MÉDIA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (334, 118, 'http://localhost:9000/atelier/products/jeans-z1975-cropped-de-cintura-media/JEANS Z1975 CROPPED DE CINTURA MÉDIA 4.jpg', 'JEANS Z1975 CROPPED DE CINTURA MÉDIA 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (335, 118, 'http://localhost:9000/atelier/products/jeans-z1975-cropped-de-cintura-media/JEANS Z1975 CROPPED DE CINTURA MÉDIA CAPA.jpg', 'JEANS Z1975 CROPPED DE CINTURA MÉDIA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (336, 118, 'http://localhost:9000/atelier/products/jeans-z1975-cropped-de-cintura-media/JEANS Z1975 CROPPED DE CINTURA MÉDIA HOVER.jpg', 'JEANS Z1975 CROPPED DE CINTURA MÉDIA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (337, 119, 'http://localhost:9000/atelier/products/jeans-z1975-cropped-de-cintura-media/JEANS Z1975 CROPPED DE CINTURA MÉDIA COLOR2 FOTO1.jpg', 'JEANS Z1975 CROPPED DE CINTURA MÉDIA COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (338, 119, 'http://localhost:9000/atelier/products/jeans-z1975-cropped-de-cintura-media/JEANS Z1975 CROPPED DE CINTURA MÉDIA COLOR2 FOTO2.jpg', 'JEANS Z1975 CROPPED DE CINTURA MÉDIA COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (339, 120, 'http://localhost:9000/atelier/products/jeans-z1975-cropped-de-cintura-media/JEANS Z1975 CROPPED DE CINTURA MÉDIA COLOR3 FOTO1.jpg', 'JEANS Z1975 CROPPED DE CINTURA MÉDIA COLOR3 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (340, 120, 'http://localhost:9000/atelier/products/jeans-z1975-cropped-de-cintura-media/JEANS Z1975 CROPPED DE CINTURA MÉDIA COLOR3 FOTO2.jpg', 'JEANS Z1975 CROPPED DE CINTURA MÉDIA COLOR3 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (341, 121, 'http://localhost:9000/atelier/products/jeans-zw-collection-wide-leg-cintura-alta/JEANS ZW COLLECTION WIDE LEG CINTURA ALTA 3.jpg', 'JEANS ZW COLLECTION WIDE LEG CINTURA ALTA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (342, 121, 'http://localhost:9000/atelier/products/jeans-zw-collection-wide-leg-cintura-alta/JEANS ZW COLLECTION WIDE LEG CINTURA ALTA CAPA.jpg', 'JEANS ZW COLLECTION WIDE LEG CINTURA ALTA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (343, 121, 'http://localhost:9000/atelier/products/jeans-zw-collection-wide-leg-cintura-alta/JEANS ZW COLLECTION WIDE LEG CINTURA ALTA HOVER.jpg', 'JEANS ZW COLLECTION WIDE LEG CINTURA ALTA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (344, 122, 'http://localhost:9000/atelier/products/jeans-zw-collection-wide-leg-cintura-alta/JEANS ZW COLLECTION WIDE LEG CINTURA ALTA COLOR2 FOTO2.jpg', 'JEANS ZW COLLECTION WIDE LEG CINTURA ALTA COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (345, 123, 'http://localhost:9000/atelier/products/jeans-zw-collection-wide-leg-cintura-alta/JEANS ZW COLLECTION WIDE LEG CINTURA ALTA COLOR3 FOTO1.jpg', 'JEANS ZW COLLECTION WIDE LEG CINTURA ALTA COLOR3 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (346, 123, 'http://localhost:9000/atelier/products/jeans-zw-collection-wide-leg-cintura-alta/JEANS ZW COLLECTION WIDE LEG CINTURA ALTA COLOR3 FOTO2.jpg', 'JEANS ZW COLLECTION WIDE LEG CINTURA ALTA COLOR3 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (347, 124, 'http://localhost:9000/atelier/products/jeans-zw-collection-wide-leg-cintura-alta-foto2/JEANS ZW COLLECTION WIDE LEG CINTURA ALTA FOTO2 COLOR1.jpg', 'JEANS ZW COLLECTION WIDE LEG CINTURA ALTA FOTO2 COLOR1.jpg', 99, false);

-- ── FEMININO / SHORTS_BERMUDAS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (348, 125, 'http://localhost:9000/atelier/products/short-de-poliuretano/SHORT DE POLIURETANO 3.jpg', 'SHORT DE POLIURETANO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (349, 125, 'http://localhost:9000/atelier/products/short-de-poliuretano/SHORT DE POLIURETANO CAPA.jpg', 'SHORT DE POLIURETANO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (350, 125, 'http://localhost:9000/atelier/products/short-de-poliuretano/SHORT DE POLIURETANO HOVER.jpg', 'SHORT DE POLIURETANO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (351, 126, 'http://localhost:9000/atelier/products/short-de-poliuretano/SHORT DE POLIURETANO COLOR2 FOTO1.jpg', 'SHORT DE POLIURETANO COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (352, 126, 'http://localhost:9000/atelier/products/short-de-poliuretano/SHORT DE POLIURETANO COLOR2 FOTO2.jpg', 'SHORT DE POLIURETANO COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (353, 127, 'http://localhost:9000/atelier/products/short-globo-acetinado-com-renda/SHORT GLOBO ACETINADO COM RENDA 3.jpg', 'SHORT GLOBO ACETINADO COM RENDA 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (354, 127, 'http://localhost:9000/atelier/products/short-globo-acetinado-com-renda/SHORT GLOBO ACETINADO COM RENDA CAPA.jpg', 'SHORT GLOBO ACETINADO COM RENDA CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (355, 127, 'http://localhost:9000/atelier/products/short-globo-acetinado-com-renda/SHORT GLOBO ACETINADO COM RENDA HOVER.jpg', 'SHORT GLOBO ACETINADO COM RENDA HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (356, 128, 'http://localhost:9000/atelier/products/short-saia-de-veludo-cotele-com-cinto/SHORT-SAIA DE VELUDO COTELÊ COM CINTO 3.jpg', 'SHORT-SAIA DE VELUDO COTELÊ COM CINTO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (357, 128, 'http://localhost:9000/atelier/products/short-saia-de-veludo-cotele-com-cinto/SHORT-SAIA DE VELUDO COTELÊ COM CINTO CAPA.jpg', 'SHORT-SAIA DE VELUDO COTELÊ COM CINTO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (358, 128, 'http://localhost:9000/atelier/products/short-saia-de-veludo-cotele-com-cinto/SHORT-SAIA DE VELUDO COTELÊ COM CINTO HOVER.jpg', 'SHORT-SAIA DE VELUDO COTELÊ COM CINTO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (359, 129, 'http://localhost:9000/atelier/products/shorts-com-babado/SHORTS COM BABADO 3.jpg', 'SHORTS COM BABADO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (360, 129, 'http://localhost:9000/atelier/products/shorts-com-babado/SHORTS COM BABADO CAPA.jpg', 'SHORTS COM BABADO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (361, 129, 'http://localhost:9000/atelier/products/shorts-com-babado/SHORTS COM BABADO HOVER.jpg', 'SHORTS COM BABADO HOVER.jpg', 2, false);

-- ── FEMININO / BLAZERS_COLETES ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (362, 130, 'http://localhost:9000/atelier/products/blazer-acinturado-com-ombreiras/BLAZER ACINTURADO COM OMBREIRAS 3.jpg', 'BLAZER ACINTURADO COM OMBREIRAS 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (363, 130, 'http://localhost:9000/atelier/products/blazer-acinturado-com-ombreiras/BLAZER ACINTURADO COM OMBREIRAS CAPA.jpg', 'BLAZER ACINTURADO COM OMBREIRAS CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (364, 130, 'http://localhost:9000/atelier/products/blazer-acinturado-com-ombreiras/BLAZER ACINTURADO COM OMBREIRAS HOVER.jpg', 'BLAZER ACINTURADO COM OMBREIRAS HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (365, 131, 'http://localhost:9000/atelier/products/blazer-cruzado/BLAZER CRUZADO 3.jpg', 'BLAZER CRUZADO 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (366, 131, 'http://localhost:9000/atelier/products/blazer-cruzado/BLAZER CRUZADO CAPA.jpg', 'BLAZER CRUZADO CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (367, 131, 'http://localhost:9000/atelier/products/blazer-cruzado/BLAZER CRUZADO HOVER.jpg', 'BLAZER CRUZADO HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (368, 132, 'http://localhost:9000/atelier/products/blazer-cruzado/BLAZER CRUZADO COLOR2 FOTO1.jpg', 'BLAZER CRUZADO COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (369, 132, 'http://localhost:9000/atelier/products/blazer-cruzado/BLAZER CRUZADO COLOR2 FOTO2.jpg', 'BLAZER CRUZADO COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (370, 132, 'http://localhost:9000/atelier/products/blazer-cruzado/BLAZER CRUZADO COLOR2 FOTO3.jpg', 'BLAZER CRUZADO COLOR2 FOTO3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (371, 133, 'http://localhost:9000/atelier/products/blazer-cruzado/BLAZER CRUZADO COLOR3 FOTO1.jpg', 'BLAZER CRUZADO COLOR3 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (372, 133, 'http://localhost:9000/atelier/products/blazer-cruzado/BLAZER CRUZADO COLOR3 FOTO2.jpg', 'BLAZER CRUZADO COLOR3 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (373, 134, 'http://localhost:9000/atelier/products/blazer-curto-xadrez/BLAZER CURTO XADREZ 3.jpg', 'BLAZER CURTO XADREZ 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (374, 134, 'http://localhost:9000/atelier/products/blazer-curto-xadrez/BLAZER CURTO XADREZ CAPA.jpg', 'BLAZER CURTO XADREZ CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (375, 134, 'http://localhost:9000/atelier/products/blazer-curto-xadrez/BLAZER CURTO XADREZ HOVER.jpg', 'BLAZER CURTO XADREZ HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (376, 135, 'http://localhost:9000/atelier/products/blazer-de-veludo-debrum-em-contraste/BLAZER DE VELUDO DEBRUM EM CONTRASTE 3.jpg', 'BLAZER DE VELUDO DEBRUM EM CONTRASTE 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (377, 135, 'http://localhost:9000/atelier/products/blazer-de-veludo-debrum-em-contraste/BLAZER DE VELUDO DEBRUM EM CONTRASTE 4.jpg', 'BLAZER DE VELUDO DEBRUM EM CONTRASTE 4.jpg', 4, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (378, 135, 'http://localhost:9000/atelier/products/blazer-de-veludo-debrum-em-contraste/BLAZER DE VELUDO DEBRUM EM CONTRASTE CAPA.jpg', 'BLAZER DE VELUDO DEBRUM EM CONTRASTE CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (379, 135, 'http://localhost:9000/atelier/products/blazer-de-veludo-debrum-em-contraste/BLAZER DE VELUDO DEBRUM EM CONTRASTE HOVER.jpg', 'BLAZER DE VELUDO DEBRUM EM CONTRASTE HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (380, 136, 'http://localhost:9000/atelier/products/blazer-de-veludo-debrum-em-contraste/BLAZER DE VELUDO DEBRUM EM CONTRASTE COLOR2 FOTO1.jpg', 'BLAZER DE VELUDO DEBRUM EM CONTRASTE COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (381, 136, 'http://localhost:9000/atelier/products/blazer-de-veludo-debrum-em-contraste/BLAZER DE VELUDO DEBRUM EM CONTRASTE COLOR2 FOTO2.jpg', 'BLAZER DE VELUDO DEBRUM EM CONTRASTE COLOR2 FOTO2.jpg', 2, false);

-- ── FEMININO / MOLETONS ──────────────────
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (382, 137, 'http://localhost:9000/atelier/products/calca-de-moletom-basica-reta-com-bainha-ajustavel/CALÇA DE MOLETOM BÁSICA RETA COM BAINHA AJUSTÁVEL 3.jpg', 'CALÇA DE MOLETOM BÁSICA RETA COM BAINHA AJUSTÁVEL 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (383, 137, 'http://localhost:9000/atelier/products/calca-de-moletom-basica-reta-com-bainha-ajustavel/CALÇA DE MOLETOM BÁSICA RETA COM BAINHA AJUSTÁVEL CAPA.jpg', 'CALÇA DE MOLETOM BÁSICA RETA COM BAINHA AJUSTÁVEL CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (384, 137, 'http://localhost:9000/atelier/products/calca-de-moletom-basica-reta-com-bainha-ajustavel/CALÇA DE MOLETOM BÁSICA RETA COM BAINHA AJUSTÁVEL HOVER.jpg', 'CALÇA DE MOLETOM BÁSICA RETA COM BAINHA AJUSTÁVEL HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (385, 138, 'http://localhost:9000/atelier/products/casaco-de-moletom-com-ziper/CASACO DE MOLETOM COM ZÍPER 3.jpg', 'CASACO DE MOLETOM COM ZÍPER 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (386, 138, 'http://localhost:9000/atelier/products/casaco-de-moletom-com-ziper/CASACO DE MOLETOM COM ZÍPER CAPA.jpg', 'CASACO DE MOLETOM COM ZÍPER CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (387, 138, 'http://localhost:9000/atelier/products/casaco-de-moletom-com-ziper/CASACO DE MOLETOM COM ZÍPER HOVER.jpg', 'CASACO DE MOLETOM COM ZÍPER HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (388, 139, 'http://localhost:9000/atelier/products/casaco-de-moletom-com-ziper/CASACO DE MOLETOM COM ZÍPER COLOR2 FOTO1.jpg', 'CASACO DE MOLETOM COM ZÍPER COLOR2 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (389, 139, 'http://localhost:9000/atelier/products/casaco-de-moletom-com-ziper/CASACO DE MOLETOM COM ZÍPER COLOR2 FOTO2.jpg', 'CASACO DE MOLETOM COM ZÍPER COLOR2 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (390, 140, 'http://localhost:9000/atelier/products/casaco-de-moletom-com-ziper/CASACO DE MOLETOM COM ZÍPER COLOR3 FOTO1.jpg', 'CASACO DE MOLETOM COM ZÍPER COLOR3 FOTO1.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (391, 140, 'http://localhost:9000/atelier/products/casaco-de-moletom-com-ziper/CASACO DE MOLETOM COM ZÍPER COLOR3 FOTO2.jpg', 'CASACO DE MOLETOM COM ZÍPER COLOR3 FOTO2.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (392, 141, 'http://localhost:9000/atelier/products/moletom-com-efeito-de-lavagem-don-clemente/MOLETOM COM EFEITO DE LAVAGEM DON CLEMENTE 3.jpg', 'MOLETOM COM EFEITO DE LAVAGEM DON CLEMENTE 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (393, 141, 'http://localhost:9000/atelier/products/moletom-com-efeito-de-lavagem-don-clemente/MOLETOM COM EFEITO DE LAVAGEM DON CLEMENTE CAPA.jpg', 'MOLETOM COM EFEITO DE LAVAGEM DON CLEMENTE CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (394, 141, 'http://localhost:9000/atelier/products/moletom-com-efeito-de-lavagem-don-clemente/MOLETOM COM EFEITO DE LAVAGEM DON CLEMENTE HOVER.jpg', 'MOLETOM COM EFEITO DE LAVAGEM DON CLEMENTE HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (395, 142, 'http://localhost:9000/atelier/products/moletom-com-mensagem-e-capuz/MOLETOM COM MENSAGEM E CAPUZ 3.jpg', 'MOLETOM COM MENSAGEM E CAPUZ 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (396, 142, 'http://localhost:9000/atelier/products/moletom-com-mensagem-e-capuz/MOLETOM COM MENSAGEM E CAPUZ CAPA.jpg', 'MOLETOM COM MENSAGEM E CAPUZ CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (397, 142, 'http://localhost:9000/atelier/products/moletom-com-mensagem-e-capuz/MOLETOM COM MENSAGEM E CAPUZ HOVER.jpg', 'MOLETOM COM MENSAGEM E CAPUZ HOVER.jpg', 2, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (398, 143, 'http://localhost:9000/atelier/products/moletom-com-mensagem-em-contraste/MOLETOM COM MENSAGEM EM CONTRASTE 3.jpg', 'MOLETOM COM MENSAGEM EM CONTRASTE 3.jpg', 3, false);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (399, 143, 'http://localhost:9000/atelier/products/moletom-com-mensagem-em-contraste/MOLETOM COM MENSAGEM EM CONTRASTE CAPA.jpg', 'MOLETOM COM MENSAGEM EM CONTRASTE CAPA.jpg', 1, true);
INSERT INTO product_images (id, product_color_id, url, file_name, display_order, is_cover)
VALUES (400, 143, 'http://localhost:9000/atelier/products/moletom-com-mensagem-em-contraste/MOLETOM COM MENSAGEM EM CONTRASTE HOVER.jpg', 'MOLETOM COM MENSAGEM EM CONTRASTE HOVER.jpg', 2, false);
