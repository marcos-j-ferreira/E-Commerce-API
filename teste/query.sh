#!/bin/bash

URL="http://192.168.1.111:8080/api/v1/search"

TOKEN="enter with token valid"

curl -s -X GET "$URL?nome=Notebook" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

curl -s -X GET "$URL?categoria=Eletrônicos" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

curl -s -X GET "$URL?minPreco=200&maxPreco=1000" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

curl -s -X GET "$URL?nome=Camiseta&maxPreco=150" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

curl -s -X GET "$URL?page=0&size=5" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

curl -s -X GET "$URL?page=0&size=5&sort=preco,desc" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

curl -s -X GET "$URL?categoria=Moda&sort=nome,asc" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

curl -s -X GET "$URL?page=0&size=10" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

curl -s -X GET "$URL?maxPreco=100" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"

curl -s -X GET "$URL?categoria=Eletrônicos&minPreco=500&maxPreco=2000&sort=nome,desc" -H "Authorization: Bearer $TOKEN"
echo -e "\n----------------------------------"
