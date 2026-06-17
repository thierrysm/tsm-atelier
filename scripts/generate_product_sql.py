#!/usr/bin/env python3
"""
Script para gerar SQL de inserção de produtos com base nos nomes de arquivos de imagem.
Processa múltiplas pastas de uma vez, mapeando cada pasta para uma categoria de produto.

Convenção de nomes de arquivos:
  <NOME DO PRODUTO> <DETALHE>.jpg

Onde DETALHE pode ser:
  CAPA          → imagem capa da cor principal (is_cover=true,  display_order=1)
  HOVER         → imagem hover da cor principal (is_cover=false, display_order=2)
  3, 4, 5, ...  → demais fotos da cor principal (is_cover=false, display_order=número)
  COLOR2 FOTO1  → 1ª foto de uma segunda variante de cor (is_cover=true,  display_order=1)
  COLOR2 FOTO2  → 2ª foto de uma segunda variante de cor (is_cover=false, display_order=2)
"""

import os
import re
from collections import defaultdict

# ─── CONFIGURAÇÕES GLOBAIS ────────────────────────────────────────────────────

ROOT_FOLDER = "/home/thierry/development/atelier fotos"

# Mapeamento: (gênero, pasta) → (categoria do enum, target_audience)
FOLDER_MAP = {
    ("feminino", "vestidos"):        ("DRESS",    "WOMENSWEAR"),
    ("feminino", "calcas"):          ("PANTS",    "WOMENSWEAR"),
    ("feminino", "camisas_blusas"):  ("BLOUSE",   "WOMENSWEAR"),
    ("feminino", "camisetas"):       ("T_SHIRT",  "WOMENSWEAR"),
    ("feminino", "casacos"):         ("COAT",     "WOMENSWEAR"),
    ("feminino", "jaquetas"):        ("JACKET",   "WOMENSWEAR"),
    ("feminino", "jeans"):           ("JEANS",    "WOMENSWEAR"),
    ("feminino", "shorts_bermudas"): ("SHORTS",   "WOMENSWEAR"),
    ("feminino", "blazers_coletes"): ("BLAZER",   "WOMENSWEAR"),
    ("feminino", "moletons"):        ("SWEATSHIRT", "WOMENSWEAR"),
}

STATUS = "DRAFT"
PRICE = "599.00"
BASE_URL = "http://localhost:9000/atelier/products"

# Cor padrão (COLOR1 - primeira cor de cada produto)
DEFAULT_COLOR_NAME = "Preto"
DEFAULT_COLOR_HEX  = "#000000"

# Cores para variantes COLOR2, COLOR3...
# Ajuste com as cores reais de cada produto
VARIANT_COLORS = {
    "COLOR2": ("Branco",  "#FFFFFF"),
    "COLOR3": ("Bege",    "#F5F0E8"),
    "COLOR4": ("Azul",    "#1E3A5F"),
    "COLOR5": ("Cinza",   "#808080"),
}

# IDs iniciais (para não conflitar com dados já existentes no banco)
START_PRODUCT_ID = 100
START_COLOR_ID   = 100
START_IMAGE_ID   = 100

# ─── HELPERS ─────────────────────────────────────────────────────────────────

def to_slug(name: str) -> str:
    slug = name.strip().lower()
    slug = re.sub(r'[àáâãä]', 'a', slug)
    slug = re.sub(r'[èéêë]',  'e', slug)
    slug = re.sub(r'[ìíîï]',  'i', slug)
    slug = re.sub(r'[òóôõö]', 'o', slug)
    slug = re.sub(r'[ùúûü]',  'u', slug)
    slug = re.sub(r'[ç]',     'c', slug)
    slug = re.sub(r'[^a-z0-9\s-]', '', slug)
    slug = re.sub(r'\s+', '-', slug)
    return slug.strip('-')


def to_title(name: str) -> str:
    """VESTIDO LONGO HALTER → Vestido Longo Halter"""
    return name.strip().title()


def escape_sql(s: str) -> str:
    return s.replace("'", "''")


# ─── PARSING ─────────────────────────────────────────────────────────────────

def parse_folder(pasta: str):
    """
    Lê os arquivos da pasta e organiza por produto e cor.
    Retorna: { "NOME PRODUTO": { "COLOR1": [arquivo, ...], "COLOR2": [...], ... } }
    """
    try:
        arquivos = [
            f for f in os.listdir(pasta)
            if f.lower().endswith(('.jpg', '.jpeg', '.png', '.webp'))
        ]
    except FileNotFoundError:
        print(f"  [AVISO] Pasta não encontrada: {pasta}")
        return {}

    produtos = defaultdict(lambda: defaultdict(list))

    for arquivo in sorted(arquivos):
        nome_sem_ext = os.path.splitext(arquivo)[0].upper()

        # Detecta COLOR2, COLOR3...
        color_match = re.search(r'\bCOLOR(\d+)\b', nome_sem_ext)

        if color_match:
            color_key   = f"COLOR{color_match.group(1)}"
            nome_produto = nome_sem_ext[:color_match.start()].strip()
        elif re.search(r'\bCAPA\b', nome_sem_ext):
            color_key   = "COLOR1"
            nome_produto = re.sub(r'\bCAPA\b', '', nome_sem_ext).strip()
        elif re.search(r'\bHOVER\b', nome_sem_ext):
            color_key   = "COLOR1"
            nome_produto = re.sub(r'\bHOVER\b', '', nome_sem_ext).strip()
        elif re.search(r'\s\d+$', nome_sem_ext):
            # Termina com número: "VESTIDO LONGO HALTER 3"
            color_key   = "COLOR1"
            nome_produto = re.sub(r'\s+\d+$', '', nome_sem_ext).strip()
        else:
            color_key   = "COLOR1"
            nome_produto = nome_sem_ext

        produtos[nome_produto][color_key].append(arquivo)

    return produtos


def get_display_order(arquivo: str, color_key: str):
    """Retorna (display_order, is_cover) baseado no nome do arquivo."""
    nome = os.path.splitext(arquivo)[0].upper()

    if color_key == "COLOR1":
        if re.search(r'\bCAPA\b', nome):
            return 1, True
        elif re.search(r'\bHOVER\b', nome):
            return 2, False
        else:
            num_match = re.search(r'\b(\d+)\b$', nome)
            num = int(num_match.group(1)) if num_match else 99
            return num, False
    else:
        foto_match = re.search(r'\bFOTO(\d+)\b', nome)
        num = int(foto_match.group(1)) if foto_match else 99
        return num, (num == 1)


# ─── GERAÇÃO DO SQL ──────────────────────────────────────────────────────────

def gerar_sql() -> str:
    produto_id = START_PRODUCT_ID
    color_id   = START_COLOR_ID
    image_id   = START_IMAGE_ID

    produto_inserts = []
    color_inserts   = []
    image_inserts   = []

    for (genero, pasta_nome), (categoria, audience) in FOLDER_MAP.items():
        pasta_path = os.path.join(ROOT_FOLDER, genero, pasta_nome)
        produtos   = parse_folder(pasta_path)

        if not produtos:
            continue

        produto_inserts.append(f"\n-- ── {genero.upper()} / {pasta_nome.upper()} ──────────────────")
        color_inserts.append(  f"\n-- ── {genero.upper()} / {pasta_nome.upper()} ──────────────────")
        image_inserts.append(  f"\n-- ── {genero.upper()} / {pasta_nome.upper()} ──────────────────")

        for nome_upper, cores in sorted(produtos.items()):
            nome_fmt  = to_title(nome_upper)
            slug      = to_slug(nome_fmt)
            descricao = escape_sql(f"{nome_fmt} - peça exclusiva da coleção TSM Atelier.")

            produto_inserts.append(
                f"INSERT INTO products "
                f"(id, name, slug, description, price, category, status, target_audience, created_at, updated_at)\n"
                f"VALUES ({produto_id}, '{escape_sql(nome_fmt)}', '{slug}', '{descricao}', "
                f"{PRICE}, '{categoria}', '{STATUS}', '{audience}', NOW(), NOW());"
            )

            for color_key, arquivos in sorted(cores.items()):
                if color_key == "COLOR1":
                    cor_nome, cor_hex = DEFAULT_COLOR_NAME, DEFAULT_COLOR_HEX
                else:
                    cor_nome, cor_hex = VARIANT_COLORS.get(color_key, ("Variante", "#CCCCCC"))

                color_inserts.append(
                    f"INSERT INTO product_colors (id, product_id, name, hex_code, version)\n"
                    f"VALUES ({color_id}, {produto_id}, '{cor_nome}', '{cor_hex}', 0);"
                )

                for arquivo in sorted(arquivos):
                    order, is_cover = get_display_order(arquivo, color_key)
                    url = f"{BASE_URL}/{slug}/{escape_sql(arquivo)}"

                    image_inserts.append(
                        f"INSERT INTO product_images "
                        f"(id, product_color_id, url, file_name, display_order, is_cover)\n"
                        f"VALUES ({image_id}, {color_id}, '{url}', "
                        f"'{escape_sql(arquivo)}', {order}, {'true' if is_cover else 'false'});"
                    )
                    image_id += 1

                color_id += 1

            produto_id += 1

    linhas = [
        "-- =============================================================",
        "-- SQL gerado automaticamente a partir das fotos de produtos",
        f"-- Pasta raiz: {ROOT_FOLDER}",
        "-- =============================================================",
        "",
        "-- IMPORTANTE: Ajuste os hex_codes das variantes (COLOR2, COLOR3...)",
        "-- e os preços antes de rodar em produção.",
        "",
        "-- ─── PRODUTOS ─────────────────────────────────────────────────",
    ]
    linhas.extend(produto_inserts)
    linhas += ["", "-- ─── CORES ────────────────────────────────────────────────────"]
    linhas.extend(color_inserts)
    linhas += ["", "-- ─── IMAGENS ──────────────────────────────────────────────────"]
    linhas.extend(image_inserts)

    return "\n".join(linhas)


# ─── MAIN ────────────────────────────────────────────────────────────────────

if __name__ == "__main__":
    print("Gerando SQL...", flush=True)

    sql = gerar_sql()

    saida = os.path.join(
        os.path.dirname(os.path.abspath(__file__)),
        "produtos_gerados.sql"
    )

    with open(saida, "w", encoding="utf-8") as f:
        f.write(sql)

    # Conta inserções
    n_produtos = sql.count("INSERT INTO products ")
    n_cores    = sql.count("INSERT INTO product_colors ")
    n_imagens  = sql.count("INSERT INTO product_images ")

    print(f"✅ Concluído!")
    print(f"   Produtos : {n_produtos}")
    print(f"   Cores    : {n_cores}")
    print(f"   Imagens  : {n_imagens}")
    print(f"   Arquivo  : {saida}")
