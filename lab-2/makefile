.PHONY: run kill check connect build

all: kill build run

run: 
		docker run -d -p 8080:8080 lab
		@$(MAKE) check

check: 
		@docker ps -q

kill: 
		docker ps -q | xargs -r docker stop

connect: 
		docker ps -q | xargs -r -I {} docker exec -it {} /bin/bash


build:
		mvn clean package
		docker build -t lab .
