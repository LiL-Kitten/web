# .PHONY: run kill check connect build
#
# all: kill run
#
# run:
# # 		docker run -d -p 8080:8080 lab
#     docker-compose up -d
# 	@$(MAKE) check
#
# check:
# 	@docker ps -q
#
# kill:
# # 		docker ps -q | xargs -r docker stop
#     docker-compose down
#
# connect:
# # 		docker ps -q | xargs -r -I {} docker exec -it {} /bin/bash
#         docker-compose exec wildfly /bin/bash
#
# # build:
# # 		mvn clean package
# # 		docker build -t lab .


.PHONY: run kill check connect build log

all: kill build run

run:
# 		docker run -d -p 8080:8080 lab
	docker-compose up -d
	@$(MAKE) check

check:

	@docker ps -q

kill:
# 		docker ps -q | xargs -r docker stop
	docker-compose down

connect:
	docker-compose exec wildfly /bin/bash

log:
	docker-compose logs -f

build:
	mvn clean package
# 	docker build -t lab .

