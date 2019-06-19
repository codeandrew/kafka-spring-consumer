package com.kafka.consumer.config

import com.kafka.consumer.model.User
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer


@EnableKafka
@Configuration
class KafkaConfiguration {

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Any> {
        val config = mutableMapOf<String, Any >()

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "my-kafka:9092")
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id")
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)

        return DefaultKafkaConsumerFactory(config)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Any>()
        factory.setConsumerFactory(consumerFactory())
        return factory
    }

    @Bean
    fun userConsumerFactory(): ConsumerFactory<String, User> {
        val config = mutableMapOf<String, Any >()

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "my-kafka:9092")
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json")
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer::class.java)
        return DefaultKafkaConsumerFactory<String, User>(config, StringDeserializer(),
                JsonDeserializer(User::class.java))
    }

    @Bean
    fun userKafkaListenerFactory(): ConcurrentKafkaListenerContainerFactory<String, User> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, User>()
        factory.setConsumerFactory(userConsumerFactory())
        return factory
    }


}
