package com.claudio.gobots.ecommerce.service.notification

import com.claudio.gobots.ecommerce.entity.Order
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class OrderNotificationConsumer(
    private final val orderNotificationService: OrderNotificationService
) {
    private val logger = LoggerFactory.getLogger(OrderNotificationConsumer::class.java)


    @KafkaListener(topics = ["order-notifications"], groupId = "order-notification-group")
    fun listenOrderNotifications(order: Order) {
        logger.info("Order processed: {}", order)
        // Processar a notificação do pedido conforme necessário
        orderNotificationService.processOrderNotification(order)
    }
}