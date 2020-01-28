#!/usr/bin/env bash

set -e

cd $(git rev-parse --show-toplevel)

# linting
cd client
npm run lint

# test frontend
npm run test-ci

# test backend
cd $(git rev-parse --show-toplevel)
./gradlew build

# feature tests
# TODO

# ship it
git push origin master

cat << "EOF"


 ___ _   _  ___ ___ ___  ___ ___
/ __| | | |/ __/ __/ _ \/ __/ __|
\__ \ |_| | (_| (_|  __/\__ \__ \
|___/\__,_|\___\___\___||___/___/

___________   _______________________________________^__
 ___   ___ |||  ___   ___   ___    ___ ___  |   __  ,----\
|   | |   |||| |   | |   | |   |  |   |   | |  |  | |_____\
|___| |___|||| |___| |___| |___|  | O | O | |  |  |        \
           |||                    |___|___| |  |__|         )
___________|||______________________________|______________/
           |||                                        /--------
-----------'''---------------------------------------'


EOF