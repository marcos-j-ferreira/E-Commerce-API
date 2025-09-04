#!/bin/bash

IP="127.0.0.1"
URL="http://$IP:8080/api/v1/cartShopping/add"

# Without a token it is not possible to make requests
TOKEN="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjZWx0YUBnbWFpbC5jb20iLCJpYXQiOjE3NTY5OTQzNzAsImV4cCI6MTc1Njk5NDczMH0.fGTYU7bDMU-lUnzCfn-sjOn-89ybXKhcNioGVk0421A"

# Usuários (IDs de 1 até 21)
usuarios=(1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21)

# Produtos (IDs de 1 até 61)
produtos=(5 8 13 20 25 30 35 40 45 50 55 60 2 7 12 18 22 28 33 48)

# Quantidades aleatórias
quantidades=(1 2 3 4 5)

for i in "${!usuarios[@]}"
do
  userId=${usuarios[$i]}
  produtoId=${produtos[$i]}
  qtd=${quantidades[$((RANDOM % ${#quantidades[@]}))]}

  echo "➡️ Criando carrinho: userId=$userId produtoId=$produtoId qtd=$qtd"
  curl -s -X POST $URL \
    -H "Authorization: Bearer $TOKEN" \
    -H "Content-Type: application/json" \
    -d "{
      \"userId\": \"$userId\",
      \"produtoId\": \"$produtoId\",
      \"quantidade\": $qtd
    }"
  echo -e "\n----------------------------------"
done
