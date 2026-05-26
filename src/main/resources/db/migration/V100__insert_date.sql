-- V3__insert_mock_data.sql

-- ─── Collections ─────────────────────────────────────────────────────────────
INSERT INTO collections (name, slug, description, image_url, status, featured, show_in_header, is_new, display_order, target_audience)
VALUES
    ('Verão 2026',      'verao-2026', 'Coleção inspirada nas praias brasileiras com peças leves e coloridas.', '/images/collections/verao-2026.jpg',      'ACTIVE', true,  true,  true,  1, 'WOMENSWEAR'),
    ('Inverno 2025',    'inverno-2025', 'Peças sofisticadas para os dias frios com tecidos nobres e elegantes.',  '/images/collections/inverno-2025.jpg',    'ACTIVE', true,  true,  false, 2, 'MENSWEAR'),
    ('Primavera 2025',  'primavera-2025', 'Estampas florais e cores vibrantes para a estação mais alegre do ano.',  '/images/collections/primavera-2025.jpg',  'ACTIVE', false, false, false, 3, 'UNISEX'),
    ('Edição Limitada', 'edicao-limitada', 'Peças exclusivas em edição limitada para ocasiões especiais.',           '/images/collections/edicao-limitada.jpg', 'DRAFT',  false, false, false, 4, 'WOMENSWEAR'),
    ('Outono 2026',         'outono-2026',         'Tons terrosos e texturas acolhedoras para a transição de estação.',     '/images/collections/outono-2026.jpg',         'ACTIVE', true,  false, true,  5, 'WOMENSWEAR'),
    ('Essentials Masculino','essentials-masculino', 'Peças atemporais e versáteis que todo homem precisa no guarda-roupa.',  '/images/collections/essentials-masculino.jpg', 'ACTIVE', true,  false, false, 6, 'MENSWEAR'),
    ('Resort 2026',         'resort-2026',         'Looks sofisticados para viagens e destinos paradisíacos.',              '/images/collections/resort-2026.jpg',         'ACTIVE', false, false, true,  7, 'WOMENSWEAR'),
    ('Urban Street',        'urban-street',        'Moda urbana contemporânea com DNA streetwear e acabamento premium.',    '/images/collections/urban-street.jpg',         'ACTIVE', true,  false, false, 8, 'UNISEX'),
    ('Mini Atelier',        'mini-atelier',        'Coleção infantil com tecidos hipoalergênicos e design lúdico.',         '/images/collections/mini-atelier.jpg',         'ACTIVE', false, false, true,  9, 'KIDS');

-- ─── Products ─────────────────────────────────────────────────────────────────
INSERT INTO products (name, slug, description, price, promotional_price, category, target_audience, status, collection_id)
VALUES
    ('Vestido Linho Premium',     'vestido-linho-premium',     'Vestido confeccionado em linho premium com corte elegante e moderno.',              299.90, NULL,   'VESTIDOS',  'WOMENSWEAR', 'ACTIVE', 1),
    ('Blusa Seda Floral',         'blusa-seda-floral',         'Blusa em seda natural com estampa floral exclusiva da coleção.',                    189.90, 149.90, 'CASACOS',   'WOMENSWEAR', 'ACTIVE', 1),
    ('Calça Alfaiataria',         'calca-alfaiataria',         'Calça de alfaiataria com caimento impecável e tecido de alta gramatura.',           349.90, NULL,   'CALCAS',    'WOMENSWEAR', 'ACTIVE', 2),
    ('Casaco Lã Merino',          'casaco-la-merino',          'Casaco confeccionado em lã merino australiana com forro de seda.',                  599.90, NULL,   'CASACOS',   'WOMENSWEAR', 'ACTIVE', 2),
    ('Vestido Midi Estampado',    'vestido-midi-estampado',    'Vestido midi com estampa exclusiva desenvolvida pelos artistas da coleção.',        259.90, 199.90, 'VESTIDOS',  'WOMENSWEAR', 'ACTIVE', 3),
    ('Conjunto Linho Primavera',  'conjunto-linho-primavera',  'Conjunto de calça e blusa em linho com bordados artesanais.',                       459.90, NULL,   'CAMISETAS', 'WOMENSWEAR', 'ACTIVE', 3),
    ('Terno Alfaiataria Slim',    'terno-alfaiataria-slim',    'Terno masculino slim-fit em lã fria super 120s, ideal para eventos de gala.',       1299.90, 999.90, 'BLAZERS_COLETES', 'MENSWEAR', 'ACTIVE', 2),
    ('Camisa Oxford Clássica',    'camisa-oxford-classica',    'Camisa masculina de botão feita 100% em algodão Oxford macio e durável.',           219.90, NULL,   'CAMISAS', 'MENSWEAR', 'ACTIVE', 1),
    ('Moletom Canguru Essencial', 'moletom-canguru-essencial', 'Moletom canguru unissex minimalista com interior peluciado.',                       199.90, NULL,   'MOLETOM', 'UNISEX', 'ACTIVE', 3),
    ('Jaqueta Jeans Infantil',    'jaqueta-jeans-infantil',    'Jaqueta jeans premium super resistente para crianças de 4 a 12 anos.',              159.90, NULL,   'JAQUETAS', 'KIDS', 'ACTIVE', 1),
    ('Camiseta Básica Premium',   'camiseta-basica-premium',   'Camiseta 100% algodão egípcio, modelagem clássica.',                                129.90, NULL,   'CAMISETAS', 'MENSWEAR', 'ACTIVE', 1),
    ('Calça Jeans Skinny',        'calca-jeans-skinny',        'Calça jeans com elastano para maior conforto no dia a dia.',                        249.90, 199.90, 'JEANS',     'MENSWEAR', 'ACTIVE', 2),
    ('Vestido Longo Verão',       'vestido-longo-verao',       'Vestido longo fluido com estampa tropical.',                                        329.90, NULL,   'VESTIDOS',  'WOMENSWEAR', 'ACTIVE', 1),
    ('Shorts Alfaiataria',        'shorts-alfaiataria',        'Shorts com corte reto e cintura alta.',                                             179.90, NULL,   'SHORTS',    'WOMENSWEAR', 'ACTIVE', 1),
    ('Camisa Polo Infantil',      'camisa-polo-infantil',      'Polo clássica de algodão piquet para crianças.',                                    99.90,  NULL,   'CAMISAS',   'KIDS',       'ACTIVE', 3),
    ('Jaqueta Corta-Vento',       'jaqueta-corta-vento',       'Jaqueta unissex ultraleve e resistente à água.',                                    279.90, NULL,   'JAQUETAS',  'UNISEX',     'ACTIVE', 3);

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
    (6, 'Bege',        '#F5F0E8'),
    (7, 'Azul Marinho', '#000080'),
    (8, 'Branco',       '#FFFFFF'),
    (9, 'Mescla',       '#808080'),
    (10, 'Denim Claro', '#79BAEC'),
    (11, 'Preto',       '#000000'),
    (12, 'Azul Escuro', '#00008B'),
    (13, 'Amarelo',     '#FFFF00'),
    (14, 'Areia',       '#C2B280'),
    (15, 'Vermelho',    '#FF0000'),
    (16, 'Cinza',       '#808080');

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
    (12, '/images/conjunto-linho-bege-1.jpg',      'conjunto-linho-bege-1.jpg',      1, true),
    (13, '/images/terno-azul-1.jpg',               'terno-azul-1.jpg',               1, true),
    (14, '/images/camisa-branca-1.jpg',            'camisa-branca-1.jpg',            1, true),
    (15, '/images/moletom-mescla-1.jpg',           'moletom-mescla-1.jpg',           1, true),
    (16, '/images/jaqueta-infantil-1.jpg',         'jaqueta-infantil-1.jpg',         1, true),
    (17, '/images/camiseta-preta-1.jpg',           'camiseta-preta-1.jpg',           1, true),
    (18, '/images/calca-jeans-1.jpg',              'calca-jeans-1.jpg',              1, true),
    (19, '/images/vestido-amarelo-1.jpg',          'vestido-amarelo-1.jpg',          1, true),
    (20, '/images/shorts-areia-1.jpg',             'shorts-areia-1.jpg',             1, true),
    (21, '/images/polo-vermelha-1.jpg',            'polo-vermelha-1.jpg',            1, true),
    (22, '/images/corta-vento-cinza-1.jpg',        'corta-vento-cinza-1.jpg',        1, true);

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
    (12, 'P', 6, 0), (12, 'M', 4, 0), (12, 'G', 2, 0),
    -- Terno Alfaiataria — Azul Marinho
    (13, 'M', 10, 0), (13, 'G', 8, 0),
    -- Camisa Oxford — Branca
    (14, 'P', 15, 0), (14, 'M', 20, 0),
    -- Moletom — Mescla
    (15, 'M', 30, 0), (15, 'G', 25, 0),
    -- Jaqueta Infantil — Denim
    (16, 'P', 12, 0), (16, 'M', 18, 0),
    -- Camiseta Básica Premium
    (17, 'P', 20, 0), (17, 'M', 30, 0), (17, 'G', 15, 0),
    -- Calça Jeans Skinny
    (18, 'M', 10, 0), (18, 'G', 8, 0),
    -- Vestido Longo Verão
    (19, 'P', 12, 0), (19, 'M', 14, 0),
    -- Shorts Alfaiataria
    (20, 'P', 8, 0), (20, 'M', 10, 0),
    -- Camisa Polo Infantil
    (21, 'P', 25, 0), (21, 'M', 20, 0),
    -- Jaqueta Corta-Vento
    (22, 'M', 15, 0), (22, 'G', 18, 0);

-- ─── Product Compositions ─────────────────────────────────────────────────────
INSERT INTO product_compositions (product_id, type)
VALUES
    (1, 'EXTERNAL'), (1, 'INTERNAL'),
    (2, 'EXTERNAL'),
    (3, 'EXTERNAL'),
    (4, 'EXTERNAL'), (4, 'INTERNAL'),
    (5, 'EXTERNAL'),
    (6, 'EXTERNAL'), (6, 'INTERNAL'),
    (7, 'EXTERNAL'), (8, 'EXTERNAL'), (9, 'EXTERNAL'), (10, 'EXTERNAL'),
    (11, 'EXTERNAL'), (12, 'EXTERNAL'), (13, 'EXTERNAL'), (14, 'EXTERNAL'), (15, 'EXTERNAL'), (16, 'EXTERNAL');

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
    (9, 'Algodão', 100),
    (10, 'Lã Fria', 100),
    (11, 'Algodão', 100),
    (12, 'Algodão', 80), (12, 'Poliéster', 20),
    (13, 'Algodão', 98), (13, 'Elastano', 2),
    (14, 'Algodão Egípcio', 100),
    (15, 'Algodão', 98), (15, 'Elastano', 2),
    (16, 'Viscose', 100),
    (17, 'Linho', 100),
    (18, 'Algodão Piquet', 100),
    (19, 'Poliéster', 100);

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
    (6, 'Passar a ferro levemente úmido'),
    (7, 'Lavar a seco apenas'),
    (8, 'Lavagem em máquina até 40ºC'),
    (9, 'Lavar do avesso'),
    (10, 'Pode usar secadora'),
    (11, 'Lavagem em máquina até 40ºC'),
    (12, 'Lavar do avesso'),
    (13, 'Lavar à mão com água fria'),
    (14, 'Passar a ferro em temperatura máx. 110°C'),
    (15, 'Pode usar secadora'),
    (16, 'Não passar a ferro');

-- ─── Media Assets ────────────────────────────────────────────────────────────

INSERT INTO media_assets ("position", url, file_name, alt_text, link_url)
VALUES
    ('HEADER_LOGO', 'https://tsmatelier.com.br/assets/banners/colecao-outono.jpg', 'colecao-outono.jpg', 'Banner principal da coleção de Outono', '/colecoes/outono'),
    ('HOME_BANNER_MAIN', 'https://tsmatelier.com.br/assets/banners/promo-vestidos.png', 'promo-vestidos.png', 'Promoção de vestidos de festa', '/categoria/vestidos'),
    ('HOME_SECTION_TWO', 'https://tsmatelier.com.br/assets/banners/novos-acessorios.webp', 'novos-acessorios.webp', 'Novos colares e brincos', '/categoria/acessorios'),
    ('HOME_SECTION_THREE', 'https://tsmatelier.com.br/assets/popups/cupom-primeira-compra.png', 'cupom-primeira-compra.png', 'Cupom de 10% na primeira compra', '/cadastro'),
    ('FOOTER_LOGO', 'https://tsmatelier.com.br/assets/institucional/nossa-historia.jpg', 'nossa-historia.jpg', 'Costureiras trabalhando no ateliê', '/sobre-nos');