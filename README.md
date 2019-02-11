# Client
`mvn package` to create fatJar

run fatJar with `java -jar target/client-1.0-SNAPSHOT.jar --help`

Options:

    -c, --clearCache
      Clear cache
      Default: false
    -i, --ignoreCache
      to ignore server cache
      Default: false
    -m, --medallion
      Cab Medallion
      Default: []
    -d, --pickupDate
      Trip pickup date format YYYY-MM-DD

## Example
`java -jar target/client-1.0-SNAPSHOT.jar -m D7D598CD99978BD012A87A76A7C891B7 -d 2013-12-01`


# Server
`mvn package` to create fatJar

Config file: src/main/java/com/dius/dennis/simplecab/simple-cab.yml

Note: need to set DB

run fatJar with `java -jar target/server-1.0-SNAPSHOT.jar server src/dist/config/simple-cab.yml`

## Docker

SQL dump file needs to be in server/db-dump folder

run `docker-compose up` to start server

# Task

Provides insights on the open data about NY cab trips

Cab trips in NY are public available as csv downloadable files. In order to make it more useful we want to wrap the data in a public API.

Data format is as follow:

``` 
medallion, hack_license, vendor_id, rate_code, store_and_fwd_flag,
pickup_datetime, dropoff_datetime, passenger_count,
trip_time_in_secs, trip_distance
```

The medallion is the cab identification.
API should provide a way to query how many trips a particular cab (medallion) has made given a particular pickup date ( using pickup_datetime and only considering the date part)
The API must receive one or more medallions and return how many trips each medallion has made.
Considering that the query creates a heavy load on the database, the results must be cached.
The API must allow user to ask for fresh data, ignoring the cache.
There must be also be a method to clear the cache.

What do we provide:
* Running java project with initial structure and suggested API methods
* SQL statements to populate database from the csv
we provide a template using spring boot, but there’s no need to use that. If you’d rather use something else, all good

Your code will be evaluated in 2 parts:
1. Server that provides REST endpoints
2. Simple command line client that will consume the APIs provided by the server
Please provide the instructions to execute