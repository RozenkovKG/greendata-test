@echo off
curl -X POST -o %~n0.response.set.json -d @%~n0.request.set.json -H "Content-Type: application/json" "http://localhost:8080/%~n0
curl -X GET -o %~n0.response.get.json -H "Content-Type: application/json" "http://localhost:8080/%~n0
curl -X DELETE -H "Content-Type: application/json" "http://localhost:8080/%~n0/1
curl -X GET -o %~n0.response.getafterdel.json -H "Content-Type: application/json" "http://localhost:8080/%~n0