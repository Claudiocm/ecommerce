package com.claudio.gobots.ecommerce.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "payments")
data class Payment(
    @Id
    val id: String?,
    val method: PaymentMethod,
    val amount: Double,
    val status: OrderStatus
)