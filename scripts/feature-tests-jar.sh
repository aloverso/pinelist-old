#!/usr/bin/env bash

# This script assumes jar has already been built

cd $(git rev-parse --show-toplevel)

APP_PORT=8080

kill $(lsof -i:${APP_PORT} -t)

set -e

echo "starting app"
java -jar pinelist-deployable/build/libs/pinelist-deployable-1.0.0.jar -Dserver.port=${APP_PORT} -Dspring.profiles.active=local &
while ! echo exit | nc localhost ${APP_PORT}; do sleep 3; done

echo "app started"

npm --prefix=web run cypress:run -- --config baseUrl=http://localhost:8080

kill $(lsof -i:${APP_PORT} -t)

echo "   __            _                                             _"
echo "  / _| ___  __ _| |_ _   _ _ __ ___  ___   _ __   __ _ ___ ___| |"
echo " | |_ / _ \/ _\` | __| | | | '__/ _ \/ __| | '_ \ / _\` / __/ __| |"
echo " |  _|  __/ (_| | |_| |_| | | |  __/\__ \ | |_) | (_| \__ \__ \_|"
echo " |_|  \___|\__,_|\__|\__,_|_|  \___||___/ | .__/ \__,_|___/___(_)"
echo "                                          |_|                    "
echo ""