-- V3__insert_mock_data.sql

-- ─── Collections ─────────────────────────────────────────────────────────────
INSERT INTO collections (name, slug, description, image_url, status, featured, show_in_header, is_new, display_order)
VALUES
    ('Verão 2026',      'verao-2026', 'Coleção inspirada nas praias brasileiras com peças leves e coloridas.', '/images/collections/verao-2026.jpg',      'ACTIVE', true,  true,  true,  1),
    ('Inverno 2025',    'inverno-2025', 'Peças sofisticadas para os dias frios com tecidos nobres e elegantes.',  '/images/collections/inverno-2025.jpg',    'ACTIVE', true,  true,  false, 2),
    ('Primavera 2025',  'primavera-2025', 'Estampas florais e cores vibrantes para a estação mais alegre do ano.',  '/images/collections/primavera-2025.jpg',  'ACTIVE', false, false, false, 3),
    ('Edição Limitada', 'edicao-limitada', 'Peças exclusivas em edição limitada para ocasiões especiais.',           '/images/collections/edicao-limitada.jpg', 'DRAFT',  false, false, false, 4);

-- ─── Products ─────────────────────────────────────────────────────────────────
INSERT INTO products (name, slug, description, price, promotional_price, category, target_audience, status, collection_id)
VALUES
    ('Vestido Linho Premium',     'vestido-linho-premium',     'Vestido confeccionado em linho premium com corte elegante e moderno.',              299.90, NULL,   'VESTIDOS',  'WOMENSWEAR', 'ACTIVE', 1),
    ('Blusa Seda Floral',         'blusa-seda-floral',         'Blusa em seda natural com estampa floral exclusiva da coleção.',                    189.90, 149.90, 'CASACOS',   'WOMENSWEAR', 'ACTIVE', 1),
    ('Calça Alfaiataria',         'calca-alfaiataria',         'Calça de alfaiataria com caimento impecável e tecido de alta gramatura.',           349.90, NULL,   'CALCAS',    'WOMENSWEAR', 'ACTIVE', 2),
    ('Casaco Lã Merino',          'casaco-la-merino',          'Casaco confeccionado em lã merino australiana com forro de seda.',                  599.90, NULL,   'CASACOS',   'WOMENSWEAR', 'ACTIVE', 2),
    ('Vestido Midi Estampado',    'vestido-midi-estampado',    'Vestido midi com estampa exclusiva desenvolvida pelos artistas da coleção.',        259.90, 199.90, 'VESTIDOS',  'WOMENSWEAR', 'ACTIVE', 3),
    ('Conjunto Linho Primavera',  'conjunto-linho-primavera',  'Conjunto de calça e blusa em linho com bordados artesanais.',                       459.90, NULL,   'CAMISETAS', 'WOMENSWEAR', 'ACTIVE', 3);

-- ─── Product Colors ───────────────────────────────────────────────────────────
INSERT INTO product_colors (product_id, name, hex_code)
VALUES
    (1, 'Areia',       '#C2B280'),
    (1, 'Off-White',   '#FAF9F6'),
    (2, 'Rosa Blush',  '#FFB6C1'),
    (2, 'Azul Céu',    '#87CEEB'),
    (3, 'Preto',       '#000000'),
    (3, 'Caramelo',    '#C68642'),
    (4, 'Chumbo',      '#4A4A4A'),
    (4, 'Vinho',       '#722F37'),
    (5, 'Terracota',   '#E2725B'),
    (5, 'Verde Sage',  '#8FAF8F'),
    (6, 'Branco',      '#FFFFFF'),
    (6, 'Bege',        '#F5F0E8');

-- ─── Product Images ───────────────────────────────────────────────────────────
INSERT INTO product_images (product_color_id, url, file_name, display_order, is_cover)
VALUES
    (1,  '/images/vestido-linho-areia-1.jpg',      'vestido-linho-areia-1.jpg',      1, true),
    (1,  '/images/vestido-linho-areia-2.jpg',      'vestido-linho-areia-2.jpg',      2, false),
    (2,  '/images/vestido-linho-offwhite-1.jpg',   'vestido-linho-offwhite-1.jpg',   1, true),
    (2,  '/images/vestido-linho-offwhite-2.jpg',   'vestido-linho-offwhite-2.jpg',   2, false),
    (3,  '/images/blusa-seda-rosa-1.jpg',          'blusa-seda-rosa-1.jpg',          1, true),
    (4,  '/images/blusa-seda-azul-1.jpg',          'blusa-seda-azul-1.jpg',          1, true),
    (5,  '/images/calca-alfaiataria-preta-1.jpg',  'calca-alfaiataria-preta-1.jpg',  1, true),
    (5,  '/images/calca-alfaiataria-preta-2.jpg',  'calca-alfaiataria-preta-2.jpg',  2, false),
    (6,  '/images/calca-alfaiataria-caramelo.jpg', 'calca-alfaiataria-caramelo.jpg', 1, true),
    (7,  '/images/casaco-la-chumbo-1.jpg',         'casaco-la-chumbo-1.jpg',         1, true),
    (8,  '/images/casaco-la-vinho-1.jpg',          'casaco-la-vinho-1.jpg',          1, true),
    (9,  '/images/vestido-midi-terracota-1.jpg',   'vestido-midi-terracota-1.jpg',   1, true),
    (10, '/images/vestido-midi-sage-1.jpg',        'vestido-midi-sage-1.jpg',        1, true),
    (11, '/images/conjunto-linho-branco-1.jpg',    'conjunto-linho-branco-1.jpg',    1, true),
    (12, '/images/conjunto-linho-bege-1.jpg',      'conjunto-linho-bege-1.jpg',      1, true);

-- ─── Product Variants ─────────────────────────────────────────────────────────
INSERT INTO product_variants (product_color_id, size, stock, version)
VALUES
    -- Vestido Linho — Areia
    (1, 'P',  5, 0), (1, 'M',  8, 0), (1, 'G',  3, 0), (1, 'GG', 2, 0),
    -- Vestido Linho — Off-White
    (2, 'P',  4, 0), (2, 'M',  6, 0), (2, 'G',  0, 0), (2, 'GG', 1, 0),
    -- Blusa Seda — Rosa Blush
    (3, 'P',  7, 0), (3, 'M',  5, 0), (3, 'G',  4, 0),
    -- Blusa Seda — Azul Céu
    (4, 'P',  3, 0), (4, 'M',  9, 0), (4, 'G',  2, 0),
    -- Calça Alfaiataria — Preta
    (5, 'P',  6, 0), (5, 'M',  4, 0), (5, 'G',  3, 0), (5, 'GG', 2, 0),
    -- Calça Alfaiataria — Caramelo
    (6, 'P',  2, 0), (6, 'M',  5, 0), (6, 'G',  4, 0),
    -- Casaco Lã — Chumbo
    (7, 'P',  3, 0), (7, 'M',  4, 0), (7, 'G',  2, 0),
    -- Casaco Lã — Vinho
    (8, 'P',  2, 0), (8, 'M',  3, 0), (8, 'G',  1, 0),
    -- Vestido Midi — Terracota
    (9,  'P', 5, 0), (9,  'M', 7, 0), (9,  'G', 3, 0),
    -- Vestido Midi — Verde Sage
    (10, 'P', 4, 0), (10, 'M', 6, 0), (10, 'G', 2, 0),
    -- Conjunto Linho — Branco
    (11, 'P', 3, 0), (11, 'M', 5, 0), (11, 'G', 4, 0),
    -- Conjunto Linho — Bege
    (12, 'P', 6, 0), (12, 'M', 4, 0), (12, 'G', 2, 0);

-- ─── Product Compositions ─────────────────────────────────────────────────────
INSERT INTO product_compositions (product_id, type)
VALUES
    (1, 'EXTERNAL'), (1, 'INTERNAL'),
    (2, 'EXTERNAL'),
    (3, 'EXTERNAL'),
    (4, 'EXTERNAL'), (4, 'INTERNAL'),
    (5, 'EXTERNAL'),
    (6, 'EXTERNAL'), (6, 'INTERNAL');

-- ─── Composition Materials ────────────────────────────────────────────────────
INSERT INTO composition_materials (composition_id, name, percentage)
VALUES
    (1, 'Linho', 100),
    (2, 'Algodão', 100),
    (3, 'Seda', 100),
    (4, 'Lã', 95), (4, 'Elastano', 5),
    (5, 'Lã Merino', 100),
    (6, 'Seda', 100),
    (7, 'Viscose', 80), (7, 'Linho', 20),
    (8, 'Linho', 100),
    (9, 'Algodão', 100);

-- ─── Product Cares ────────────────────────────────────────────────────────────
INSERT INTO product_cares (product_id, care_instruction)
VALUES
    (1, 'Lavar à mão com água fria'),
    (1, 'Não utilizar alvejante'),
    (1, 'Passar a ferro em temperatura máx. 110°C'),
    (1, 'Não secar na secadora'),
    (2, 'Lavar à mão com sabão neutro'),
    (2, 'Não torcer'),
    (2, 'Secar à sombra'),
    (3, 'Lavar a seco'),
    (3, 'Não molhar'),
    (3, 'Passar a ferro no avesso'),
    (4, 'Lavar a seco somente'),
    (4, 'Não lavar em máquina'),
    (4, 'Guardar em cabide'),
    (5, 'Lavar à mão com água fria'),
    (5, 'Não utilizar alvejante'),
    (5, 'Secar à sombra'),
    (6, 'Lavar à mão'),
    (6, 'Não torcer'),
    (6, 'Passar a ferro levemente úmido');

-- ─── Media Assets ────────────────────────────────────────────────────────────

INSERT INTO media_assets (id, "position", url, file_name, alt_text, link_url)
VALUES
    (1, 'HEADER_LOGO', 'https://tsmatelier.com.br/assets/banners/colecao-outono.jpg', 'colecao-outono.jpg', 'Banner principal da coleção de Outono', '/colecoes/outono'),
    (2, 'HOME_BANNER_MAIN', 'https://tsmatelier.com.br/assets/banners/promo-vestidos.png', 'promo-vestidos.png', 'Promoção de vestidos de festa', '/categoria/vestidos'),
    (3, 'HOME_SECTION_TWO', 'https://tsmatelier.com.br/assets/banners/novos-acessorios.webp', 'novos-acessorios.webp', 'Novos colares e brincos', '/categoria/acessorios'),
    (4, 'HOME_SECTION_THREE', 'https://tsmatelier.com.br/assets/popups/cupom-primeira-compra.png', 'cupom-primeira-compra.png', 'Cupom de 10% na primeira compra', '/cadastro'),
    (5, 'FOOTER_LOGO', 'https://tsmatelier.com.br/assets/institucional/nossa-historia.jpg', 'nossa-historia.jpg', 'Costureiras trabalhando no ateliê', '/sobre-nos');