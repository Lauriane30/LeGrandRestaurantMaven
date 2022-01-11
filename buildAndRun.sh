#!/bin/sh
mvn clean package && docker build -t com.calculatricesonar/leGrandRestaurantMaven .
docker rm -f leGrandRestaurantMaven || true && docker run -d -p 9080:9080 -p 9443:9443 --name leGrandRestaurantMaven com.calculatricesonar/leGrandRestaurantMaven