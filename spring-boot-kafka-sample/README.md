#Run kafka server & create topics
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties

bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic mytopic

#References
https://kafka.apache.org/quickstart
https://www.baeldung.com/spring-kafka