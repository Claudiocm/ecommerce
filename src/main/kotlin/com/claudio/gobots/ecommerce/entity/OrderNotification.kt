package com.claudio.gobots.ecommerce.entity

import java.util.*

data class OrderNotification(
    val orderId: UUID,
    val status: String,
    val message: String
)