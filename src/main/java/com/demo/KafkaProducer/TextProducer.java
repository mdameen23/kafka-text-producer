package com.demo.KafkaProducer;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class TextProducer {

    private static Logger logger = LogManager.getLogger(TextProducer.class.getName());
    private static Producer<String, String> myKafkaProducer;

    private String TopicID;

    public void setTopicId(String id) {
        TopicID = id;
    }

    public TextProducer(Properties props) {
        logger.debug("Initializing with Properties: " + props.toString());
        ProducerConfig config = new ProducerConfig(props);
        myKafkaProducer = new Producer<String, String>(config);
    }

    public void sendMessage(String msgKey, String msg) throws Exception {
        logger.debug("Sending Message: " + msgKey + " = " + msg);
        KeyedMessage<String, String> data = new KeyedMessage<String, String>(TopicID, msgKey, msg);
        myKafkaProducer.send(data);
    }
}
