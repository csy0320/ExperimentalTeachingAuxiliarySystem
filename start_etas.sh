#!/bin/sh
nohup java -jar /etas-fdfs-0.0.1.jar &
nohup java -jar /etas-auth-0.0.1.jar &
#nohup java -jar /etas-gateway-0.0.1.jar &
nohup java -jar /etas-user-0.0.1.jar &
nohup java -jar /etas-search-0.0.1.jar &
nohup java -jar /etas-manage-0.0.1.jar &
