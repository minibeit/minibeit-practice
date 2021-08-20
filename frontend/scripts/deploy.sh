#!/bin/bash

REPOSITORY=/home/ec2-user/app/frontend/zip

echo "> 이전 build 파일 삭제 "
sudo rm -rf /var/www/html/build

echo "> zip 파일 복사 "

sudo cp -r $REPOSITORY/build /var/www/html

echo "> nginx restart"

sudo service nginx restart