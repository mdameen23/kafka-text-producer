# Kafka Text Producer

This is a simple producer for Kafka that publishes messages by reading
input from a text file. Each line in the input file is published as a
different message.

Modify the file producer.properties to update the property
metadata.broker.list. This should point to host:port where the
Kafka broker is running.


This project was run on the MapR sandbox.
1. Setup Kafka on the sandbox
The following link provides information on setting up Kafka
http://kafka.apache.org/07/quickstart.html
or download and install from
http://mirror.cogentco.com/pub/apache/kafka/0.8.1.1/kafka_2.10-0.8.1.1.tgz

2. Clone the git repos locally

3. Build the project using maven
mvn clean package

4. Start a Kafka server
Kafka was installed to /opt/kafka/
bin/kafka-server-start.sh config/server.properties > logs/kafka-server.log 2>&1 &

5. Create the Kafka topic
The topic used in the code is "TextStream"
bin/kafka-topics.sh --zookeeper maprdemo:5181 --create --topic TextStream --partitions 1 --replication-factor 1

6. Execute the project
mvn exec:java -Dexec.mainClass="com.demo.KafkaProducer.App" -Dexec.args="input.txt"
