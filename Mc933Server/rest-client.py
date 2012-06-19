#!/usr/bin/python

from restful_lib import Connection
conn = Connection("http://localhost:8888")
response = conn.request_get("/getNearestBusStops?lat=-22.8177;lon=-47.0683")

print response['body']

conn.request_put("/sidewinder", {'color': 'blue'}, headers={'content-type':'application/json', 'accept':'application/json'})
