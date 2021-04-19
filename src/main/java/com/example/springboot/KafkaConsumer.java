package com.example.springboot;

import com.example.springboot.data.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


@RestController
@RequestMapping("/consume")
public class KafkaConsumer {

    @Autowired
    private ConcurrentKafkaListenerContainerFactory<String, Employee> factory;

    @GetMapping("/message")
    public List<Employee> receiveMessage() {
        List<Employee> students = new ArrayList<>();
        ConsumerFactory<String, Employee> consumerFactory = factory.getConsumerFactory();
        Consumer<String, Employee> consumer = consumerFactory.createConsumer();
        try {
            consumer.subscribe(Arrays.asList(AppConstant.TOPIC_NAME));
            ConsumerRecords<String, Employee> consumerRecords = consumer.poll(10000);
            Iterable<ConsumerRecord<String, Employee>> records = consumerRecords.records(AppConstant.TOPIC_NAME);
            Iterator<ConsumerRecord<String, Employee>> iterator = records.iterator();

            while (iterator.hasNext()) {
                students.add(iterator.next().value());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}