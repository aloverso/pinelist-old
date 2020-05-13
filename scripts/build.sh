#!/usr/bin/env bash

set -e
cd $(git rev-parse --show-toplevel)

./gradlew clean build

echo "  _           _ _     _                                 _"
echo " | |__  _   _(_) | __| |  _ __   __ _ ___ ___  ___  ___| |"
echo " | '_ \| | | | | |/ _\` | | '_ \ / _\` / __/ __|/ _ \/ __| |"
echo " | |_) | |_| | | | (_| | | |_) | (_| \__ \__ \  __/\__ \_|"
echo " |_.__/ \__,_|_|_|\__,_| | .__/ \__,_|___/___/\___||___(_)"
echo "                         |_|"
echo ""