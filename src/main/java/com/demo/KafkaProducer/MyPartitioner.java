
package com.demo.KafkaProducer;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class MyPartitioner implements Partitioner {

    public MyPartitioner (VerifiableProperties props) {

    }

    public int partition(Object key, int a_numPartitions) {
        int partition = 0;
        String stringKey = (String) key;
        partition = Integer.parseInt(stringKey) % a_numPartitions;
        return partition;
    }
}
