package com.alsace.simplejavastorm.storm;

import com.alsace.simplejavastorm.component.PropertiesConfig;
import com.alsace.simplejavastorm.util.InitSpringBean;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * <p>
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/19 0019
 */
public class Spout extends BaseRichSpout {

    private KafkaConsumer<String, String> consumer;
    private ConsumerRecords<String, String> msgList;
    private SpoutOutputCollector collector;
    private PropertiesConfig properties;

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector collector) {
        properties = InitSpringBean.getBean(PropertiesConfig.class);
        this.collector = collector;
        kafkaInit();
    }

    @Override
    public void nextTuple() {
        for (;;){
            msgList = consumer.poll(100);
            for (ConsumerRecord record : msgList){
                Object value = record.value();
                System.out.println(value);
            }
            if (msgList.count() >= 10){
                collector.emit(new Values(msgList));
            }
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("metric"));
    }

    /**
     * 初始化kafka配置
     */
    private void kafkaInit(){
        Properties props = new Properties();
        props.put("bootstrap.servers", properties.getServers());
        props.put("max.poll.records", properties.getMaxPollRecords());
        props.put("enable.auto.commit", properties.getAutoCommit());
        props.put("group.id", properties.getGroupId());
        props.put("auto.offset.reset", properties.getCommitRule());
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<String, String>(props);
        String topic = properties.getTopicName();
        this.consumer.subscribe(Arrays.asList(topic));
    }
}
