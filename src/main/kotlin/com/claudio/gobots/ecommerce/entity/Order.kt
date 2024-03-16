package com.claudio.gobots.ecommerce.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "orders")
data class Order(
    @Id
    val orderId: String?,
    val createdAt: Long?,
    var updatedAt: Long?,
    var status: OrderEnum,
    val items: List<Item>,
    val seller: String,
    val buyer: String,
    val shippingAddress: Address,
    val billingAddress: Address,
    val payment: Payment
)