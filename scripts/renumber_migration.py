#!/usr/bin/env python3
"""
Renumera todos os IDs da migration V100 começando do 1,
mantendo as foreign keys (product_id, product_color_id) consistentes.
Corrige qualquer gap na sequência de IDs via abordagem de 2 passos.
"""

import re

INPUT  = "src/main/resources/db/migration/V100__insert_date.sql"
OUTPUT = "src/main/resources/db/migration/V100__insert_date.sql"

def renumber(sql: str) -> str:
    lines = sql.splitlines(keepends=True)

    # ── PASSO 1: Coleta todos os old IDs na ordem em que aparecem ────────
    old_product_ids = []
    old_color_ids   = []
    old_image_ids   = []

    i = 0
    while i < len(lines):
        line = lines[i]
        next_line = lines[i + 1].strip() if i + 1 < len(lines) else ""

        if line.strip().startswith("INSERT INTO products "):
            m = re.match(r"VALUES \((\d+),", next_line)
            if m:
                old_product_ids.append(int(m.group(1)))
        elif line.strip().startswith("INSERT INTO product_colors "):
            m = re.match(r"VALUES \((\d+),", next_line)
            if m:
                old_color_ids.append(int(m.group(1)))
        elif line.strip().startswith("INSERT INTO product_images "):
            m = re.match(r"VALUES \((\d+),", next_line)
            if m:
                old_image_ids.append(int(m.group(1)))
        i += 1

    # ── PASSO 2: Monta os mapas old_id → new_id (sequencial, sem gaps) ──
    product_map = {old: new for new, old in enumerate(old_product_ids, start=1)}
    color_map   = {old: new for new, old in enumerate(old_color_ids,   start=1)}
    image_map   = {old: new for new, old in enumerate(old_image_ids,   start=1)}

    # ── PASSO 3: Reescreve o arquivo aplicando os mapas ─────────────────
    result = []
    i = 0
    while i < len(lines):
        line = lines[i]

        if line.strip().startswith("INSERT INTO products "):
            values_line = lines[i + 1] if i + 1 < len(lines) else ""
            m = re.match(r"(VALUES \()\d+(,.*)", values_line.strip())
            if m:
                old_id = int(re.match(r"VALUES \((\d+),", values_line.strip()).group(1))
                values_line = f"{m.group(1)}{product_map[old_id]}{m.group(2)}\n"
                result.append(line)
                result.append(values_line)
                i += 2
                continue

        elif line.strip().startswith("INSERT INTO product_colors "):
            values_line = lines[i + 1] if i + 1 < len(lines) else ""
            m = re.match(r"VALUES \((\d+),\s*(\d+),(.*)", values_line.strip())
            if m:
                old_color_id   = int(m.group(1))
                old_product_id = int(m.group(2))
                new_color_id   = color_map[old_color_id]
                new_product_id = product_map.get(old_product_id, old_product_id)
                values_line = f"VALUES ({new_color_id}, {new_product_id},{m.group(3)}\n"
                result.append(line)
                result.append(values_line)
                i += 2
                continue

        elif line.strip().startswith("INSERT INTO product_images "):
            values_line = lines[i + 1] if i + 1 < len(lines) else ""
            m = re.match(r"VALUES \((\d+),\s*(\d+),(.*)", values_line.strip())
            if m:
                old_image_id = int(m.group(1))
                old_color_id = int(m.group(2))
                new_image_id = image_map[old_image_id]
                new_color_id = color_map.get(old_color_id, old_color_id)
                values_line = f"VALUES ({new_image_id}, {new_color_id},{m.group(3)}\n"
                result.append(line)
                result.append(values_line)
                i += 2
                continue

        result.append(line)
        i += 1

    return "".join(result)


if __name__ == "__main__":
    with open(INPUT, "r", encoding="utf-8") as f:
        original = f.read()

    renumbered = renumber(original)

    with open(OUTPUT, "w", encoding="utf-8") as f:
        f.write(renumbered)

    n_products = renumbered.count("INSERT INTO products ")
    n_colors   = renumbered.count("INSERT INTO product_colors ")
    n_images   = renumbered.count("INSERT INTO product_images ")
    print(f"✅ IDs alinhados e sequenciais!")
    print(f"   Produtos : 1 → {n_products}")
    print(f"   Cores    : 1 → {n_colors}")
    print(f"   Imagens  : 1 → {n_images}")
    print(f"   Arquivo  : {OUTPUT}")
