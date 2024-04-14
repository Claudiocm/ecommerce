package com.claudio.gobots.ecommerce.entity

import lombok.Data
import org.apache.kafka.common.protocol.types.Field
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Data
@Document(collection = "orders")
data class Order(
    @Id
    val orderId: UUID?,
    val createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?,
    var status: OrderEnum,
    @DBRef
    val items: List<Item>,
    val seller: String,
    @DBRef
    val buyer: Buyer,
    val shippingAddress: String,
    val billingAddress: String,
    @DBRef
    val payment: Payment
)