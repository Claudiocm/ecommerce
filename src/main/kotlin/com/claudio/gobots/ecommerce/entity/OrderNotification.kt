package com.claudio.gobots.ecommerce.entity

data class OrderNotification(
    val orderId: String,
    val status: String,
    val message: String
)