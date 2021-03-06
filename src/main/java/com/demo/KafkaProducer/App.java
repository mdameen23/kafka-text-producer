package com.demo.KafkaProducer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class App
{
    private static Logger logger = LogManager.getLogger(App.class.getName());

    public static void main( String[] args )
    {

        Properties props = new Properties();
        try {
            logger.debug("Loading Properties for Kafka");
            props.load(new FileInputStream("src/main/resources/producer.properties"));
        } catch(IOException ex)
        {
            System.out.println(ex.toString());
            return;
        }

        if (args.length != 1) {
            System.out.println("Please specify input file");
            System.exit(1);
        }

        logger.debug("Loading text data from: " + args[0]);
        TextProducer myTextProducer = new TextProducer(props);
        myTextProducer.setTopicId("TextStream");

        BufferedReader br = null;
        String msgLine = "";
        int count = 0;
        try {
            br = new BufferedReader(new FileReader(args[0]));
            while ((msgLine = br.readLine()) != null) {
                count++;
				myTextProducer.sendMessage(Integer.toString(count), msgLine);
            }
        } catch(Exception ex) {
			logger.debug("Exception while sending message: " + ex.toString());
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        logger.info("Published " + count + " message(s) to Kafka");
		myTextProducer.close();
    }
}
