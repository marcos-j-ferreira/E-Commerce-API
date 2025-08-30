#!/bin/bash

URL="http://192.168.1.111:8080/api/v1/search"
TOKEN="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjZWx0YUBnbWFpbC5jb20iLCJpYXQiOjE3NTY0MTc0MjYsImV4cCI6MTc1NjQxNzc4Nn0.xQBx3U7LQAqqYssBCoanBLUVQdT34vTPrRAM3zvIT7c"

# 1) Buscar por nome
echo "🔎 Busca por nome = 'Notebook'"
curl -s -X GET "$URL?nome=Notebook" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

# 2) Buscar por categoria
echo "🔎 Busca por categoria = 'Eletrônicos'"
curl -s -X GET "$URL?categoria=Eletrônicos" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

# 3) Buscar por faixa de preço
echo "🔎 Produtos entre R$200 e R$1000"
curl -s -X GET "$URL?minPreco=200&maxPreco=1000" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

# 4) Buscar nome + faixa de preço
echo "🔎 'Camiseta' até R$150"
curl -s -X GET "$URL?nome=Camiseta&maxPreco=150" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

# 5) Paginação simples (primeira página, 5 itens)
echo "🔎 Página 0, 5 itens"
curl -s -X GET "$URL?page=0&size=5" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

# 6) Paginação com ordenação (por preço decrescente)
echo "🔎 Ordenando por preço (desc)"
curl -s -X GET "$URL?page=0&size=5&sort=preco,desc" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

# 7) Buscar por categoria + ordenação
echo "🔎 Categoria 'Moda' ordenado por nome asc"
curl -s -X GET "$URL?categoria=Moda&sort=nome,asc" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

# 8) Busca vazia (traz tudo, paginado)
echo "🔎 Todos os produtos (page=0, size=10)"
curl -s -X GET "$URL?page=0&size=10" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

# 9) Buscar só por estoque barato
echo "🔎 Produtos até R$100"
curl -s -X GET "$URL?maxPreco=100" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

# 10) Combinação completa
echo "🔎 Categoria 'Eletrônicos', preço entre 500 e 2000, ordenado por nome desc"
curl -s -X GET "$URL?categoria=Eletrônicos&minPreco=500&maxPreco=2000&sort=nome,desc" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"
