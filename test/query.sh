#!/bin/bash

#set your IP
IP="127.0.0.1";

set -e;

URL="http://$IP:8080/api/v1/search"

TOKEN="enter with token valid"

curl GET "$URL?nome=Notebook" #-H "Authorization: Bearer $TOKEN"
echo -e "\n---------------------------------- ^ GET 01"
echo "  "

# curl -s -X GET "$URL?categoria=Eletrônicos" #.../
curl GET "$URL?sort=nome,asc" # -H "Authorization: Bearer $TOKEN"
echo -e "\n ---------------------------------- ^ GET 02"

curl GET "$URL?minPreco=200&maxPreco=1000" # -H "Authorization: Bearer $TOKEN"
echo -e "\n---------------------------------- ^ GET 03"

curl GET "$URL?nome=Camiseta&maxPreco=150" # -H "Authorization: Bearer $TOKEN"
echo -e "\n---------------------------------- ^ GET 04"

curl GET "$URL?page=0&size=5" # -H "Authorization: Bearer $TOKEN"
echo -e "\n---------------------------------- ^ GET 05"

curl GET "$URL?page=0&size=5&sort=preco,desc" # -H "Authorization: Bearer $TOKEN"
echo -e "\n---------------------------------- ^ GET 06"

# Is not valid
# curl GET "$URL?categoria=Moda&sort=nome,asc" #  -H "Authorization: Bearer $TOKEN"
# echo -e "\n---------------------------------- ^ GET 07"

curl GET "$URL?page=0&size=10" # -H "Authorization: Bearer $TOKEN"
echo -e "\n---------------------------------- ^ GET 08"

curl GET "$URL?maxPreco=100" # -H "Authorization: Bearer $TOKEN"
echo -e "\n---------------------------------- ^ GET 09"


# is not valid
# curl -s -X GET "$URL?categoria=Eletrônicos&minPreco=500&maxPreco=2000&sort=nome,desc" # -H "Authorization: Bearer $TOKEN"
# echo -e "\n----------------------------------"
