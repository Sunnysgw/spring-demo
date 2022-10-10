package com.sgw.provider.common.listener;

import lombok.extern.java.Log;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Log
public class KafkaListener {

    @org.springframework.kafka.annotation.KafkaListener(topics = "event",groupId = "sgw")
    public void listenZhugeGroup(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        log.info("kafka message receive, topic:" + record.topic());
        log.info("kafka message receive, value:" + value);
        //手动提交offset
        //ack.acknowledge();
    }

}
