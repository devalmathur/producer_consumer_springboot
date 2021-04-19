package com.example.springboot;

import com.example.springboot.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/produce")
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping ("/{message}")
    public String sendMessage(@RequestBody Employee message) {

        try {
            kafkaTemplate.send(AppConstant.TOPIC_NAME, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json message sent successfully";
    }

}