@echo off
call mvn clean package
call docker build -t com.calculatricesonar/leGrandRestaurantMaven .
call docker rm -f leGrandRestaurantMaven
call docker run -d -p 9080:9080 -p 9443:9443 --name leGrandRestaurantMaven com.calculatricesonar/leGrandRestaurantMaven