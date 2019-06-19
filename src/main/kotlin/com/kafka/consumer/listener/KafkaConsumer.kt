package com.kafka.consumer.listener

import org.springframework.stereotype.Service
import org.springframework.kafka.annotation.KafkaListener



@Service
class KafkaConsumer{

    @KafkaListener(topics = ["test"], groupId = "group")
    fun consume(message: String) {
        println("Consumed message: $message")
    }


}