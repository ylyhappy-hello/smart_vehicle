mvn -T 8 clean install package -Dmaven.test.skip=true
java -jar ./vehicle-server/target/vehicle-server.jar
