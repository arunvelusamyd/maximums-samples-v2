#Run kafka server & create topics
<br/>
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties

bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic mytopic

#References
<br/>
https://kafka.apache.org/quickstart
<br/>
https://www.baeldung.com/spring-kafka