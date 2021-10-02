package com.alsace.simplejavastorm.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * <p>
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/18 0018
 */
public class KafkaProducerTest implements Runnable {

    private final KafkaProducer<String, String> producer;
    private final String topic;

    public KafkaProducerTest(String topic) {
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "47.101.56.21:9092");
        //确保数据不会丢失
        prop.put("acks", "all");
        //客户端会在消息发送失败时重新发送
        prop.put("retries", 0);
        prop.put("batch.size", 1000);
        prop.put("key.serializer", StringSerializer.class.getName());
        prop.put("value.serializer", StringSerializer.class.getName());

        this.producer = new KafkaProducer<>(prop);
        this.topic = topic;
    }

    @Override
    public void run() {
        int msgNo = 1;
        try {
            for (int i = 0; i <= 1000; i++) {
                String msg = "这是第" + msgNo + "条数据";
                producer.send(new ProducerRecord<>(topic, "msg", msg));
                msgNo++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

    public static void main(String[] args) {
        KafkaProducerTest producerTest = new KafkaProducerTest("test");
        new Thread(producerTest).start();
    }
}
