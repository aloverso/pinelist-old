#!/usr/bin/env bash

psql -U postgres -d pinelistlocal -c "insert into pinelists(id,name) values ('12345', 'Christmas List 2020')"
psql -U postgres -d pinelistlocal -c "insert into items(id,name,pinelist_id) values ('1', 'laptop', '12345')"
psql -U postgres -d pinelistlocal -c "insert into items(id,name,pinelist_id) values ('2', 'potted plant', '12345')"