#!/bin/bash

URL="http://192.168.1.111:8080/api/v1/creataccount/new"

declare -a carros=(
  "BMW" "Audi" "Mercedes" "Ferrari" "Lamborghini"
  "Porsche" "Maserati" "Jaguar" "Bentley" "RollsRoyce"
  "Toyota" "Honda" "Nissan" "Chevrolet" "Ford"
  "Volkswagen" "Peugeot" "Renault" "Fiat" "Hyundai"
)

for carro in "${carros[@]}"
do
  echo "➡️ Criando usuário: $carro"
  curl -s -X POST $URL \
    -H "Content-Type: application/json" \
    -d "{
      \"nome\": \"$carro\",
      \"email\": \"${carro,,}@gmail.com\",
      \"password\": \"12345678\"
    }"
  echo -e "\n----------------------------------"
done
