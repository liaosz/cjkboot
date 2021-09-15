#!/bin/sh
dir=`cd $(dirname $0); pwd -P`
jar=$(find ./lib -type f -name  cjkboot*.jar)
echo $jar
nohup java -server -Duser.dir=$dir -XX:-UseGCOverheadLimit  -XX:PermSize=256M -XX:MaxPermSize=512M -Dloader.path=./lib,./config -jar $jar > /dev/null &
