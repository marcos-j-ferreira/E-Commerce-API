#!/bin/bash


# Your IP;
IP="127.0.0.1";

URL="http://$IP:8080/api/v1/product/create"

# Without a token it is not possible to make requests

TOKEN="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjZWx0YUBnbWFpbC5jb20iLCJpYXQiOjE3NTY5OTQzNzAsImV4cCI6MTc1Njk5NDczMH0.fGTYU7bDMU-lUnzCfn-sjOn-89ybXKhcNioGVk0421A"

set -e;

# Lista de emails dos carros
emails=(
  "bmw@gmail.com" "audi@gmail.com" "mercedes@gmail.com" "ferrari@gmail.com" "lamborghini@gmail.com"
  "porsche@gmail.com" "maserati@gmail.com" "jaguar@gmail.com" "bentley@gmail.com" "rollsroyce@gmail.com"
  "toyota@gmail.com" "honda@gmail.com" "nissan@gmail.com" "chevrolet@gmail.com" "ford@gmail.com"
  "volkswagen@gmail.com" "peugeot@gmail.com" "renault@gmail.com" "fiat@gmail.com" "hyundai@gmail.com"
)

# Produtos variados
nomes=(
  "Carrinho de brinquedo" "Volante gamer" "Camiseta esportiva" "Tênis de corrida"
  "Fone de ouvido Bluetooth" "Teclado mecânico" "Mouse gamer" "Copo térmico"
  "Bolsa de viagem" "Relógio digital" "Bicicleta infantil" "Smartwatch"
  "Câmera fotográfica" "Caixa de som portátil" "Patinete elétrico"
  "Notebook" "Tablet" "Monitor 4K" "Mochila escolar" "Caneca personalizada"
)

precos=(120 450 80 300 250 500 220 70 600 150 900 750 1500 200 1800 3500 1200 2000 90 50)
estoques=(2 10 25 8 15 5 30 20 12 18 7 10 3 40 6 4 9 11 100 60)
descricoes=(
  "Um ótimo produto para todas as idades"
  "Qualidade premium e durabilidade garantida"
  "Design moderno e inovador"
  "Produto muito procurado no mercado"
  "Ideal para presente"
  "Versão limitada e exclusiva"
  "Alta performance para o dia a dia"
  "Compacto, leve e prático"
  "Modelo novo, excelente custo-benefício"
  "Conforto e estilo em um só produto"
  "Perfeito para colecionadores"
  "Resistente e de fácil manutenção"
  "Top de linha para quem busca qualidade"
  "Som cristalino e envolvente"
  "Diversão e praticidade garantidas"
  "Ideal para trabalho e estudos"
  "Leve, rápido e versátil"
  "Imagem nítida e cores vibrantes"
  "Perfeito para uso diário"
  "Produto exclusivo da nossa loja"
)

for i in "${!nomes[@]}"
do
  echo " Criando produto: ${nomes[$i]} para ${emails[$i]}"
  curl -s -X POST $URL \
    -H "Authorization: Bearer $TOKEN" \
    -H "Content-Type: application/json" \
    -d "{
      \"nome\": \"${nomes[$i]}\",
      \"preco\": ${precos[$i]},
      \"estoque\": ${estoques[$i]},
      \"descricao\": \"${descricoes[$i]}\",
      \"email\": \"${emails[$i]}\"
    }"
  echo -e "\n----------------------------------"
done
