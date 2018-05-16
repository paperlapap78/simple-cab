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