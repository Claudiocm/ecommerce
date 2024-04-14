package com.claudio.gobots.ecommerce.message.producer

import com.claudio.gobots.ecommerce.entity.Order
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaProducerConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapServers: String

    @Value("\${kafka.topic}")
    private lateinit var kafkaTopic: String

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Order> {
        val producerFactory: ProducerFactory<String, Order> = DefaultKafkaProducerFactory(producerConfigs())
        return KafkaTemplate(producerFactory)
    }

    @Bean
    fun producerConfigs(): Map<String, Any> {
        val props: MutableMap<String, Any> = HashMap()
        props["bootstrap.servers"] = bootstrapServers
        props["key.serializer"] = StringSerializer::class.java
        props["value.serializer"] = JsonSerializer::class.java
        return props
    }

    @Bean
    fun topic(): NewTopic {
        return NewTopic(kafkaTopic, 1, 1.toShort())
    }
}