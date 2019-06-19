package com.kafka.consumer.listener

import org.springframework.stereotype.Service
import org.springframework.kafka.annotation.KafkaListener



@Service
class KafkaConsumer{

    @KafkaListener(topics = ["test"], groupId = "group_id")
    fun consume(message: String) {
        println("Consumed message: $message")
    }

    @KafkaListener(topics = ["test_json"], groupId = "group_json",
            containerFactory = "userKafkaListenerFactory")
    fun consumeJson(message: Any) {
        println("Consumed message: $message")
    }

}