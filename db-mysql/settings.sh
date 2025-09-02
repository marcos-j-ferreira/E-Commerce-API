#!/bin/bash

mkdir -p ./mysql-data

set -e

docker run -d \
  --name dataBase-ecommerce \
  -e MYSQL_ROOT_PASSWORD=senha123 \
  -e MYSQL_DATABASE=system-01 \
  -e MYSQL_USER=marcos \
  -e MYSQL_PASSWORD=senha123 \
  -v $(pwd)/mysql-data:/var/lib/mysql \
  -p 3306:3306 \
  --restart unless-stopped \
  mysql:8.0.30

  # -v $(pwd)/mysql-config:/etc/mysql/conf.d \
