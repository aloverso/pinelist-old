#!/usr/bin/env bash

psql -U postgres -d pinelistfeaturetest -c "delete from items"
psql -U postgres -d pinelistfeaturetest -c "delete from pinelists"