package com.claudio.gobots.ecommerce.service.notification

import com.claudio.gobots.ecommerce.entity.Order
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import com.claudio.gobots.ecommerce.service.OrderService

@Service
class OrderNotificationService(
    private val kafkaTemplate: KafkaTemplate<String, Order>,
    private val orderService: OrderService
) {

    private val logger = LoggerFactory.getLogger(OrderNotificationService::class.java)

    @Value("\${kafka.topic}")
    private lateinit var kafkaTopic: String

    fun sendOrderNotification(order: Order) {
        logger.info("Sending order notification for order: ${order.orderId}")
        kafkaTemplate.send(kafkaTopic, order)
    }

    fun processOrderNotification(order: Order) {
        logger.info("Processing order notification for order: ${order.orderId}")
        sendOrderNotification(order)
        orderService.saveOrder(order)
    }
}