# Kafka Text Producer

This is a simple producer for Kafka that publishes messages by reading
input from a text file. Each line in the input file is published as a 
different message.

Please modify the file producer.properties to update the property
metadata.broker.list. This should point to <host>:<port> where the 
Kafka broker is running.
