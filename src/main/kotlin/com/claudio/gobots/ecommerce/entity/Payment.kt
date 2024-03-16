package com.claudio.gobots.ecommerce.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "payments")
data class Payment(
    @Id
    val id: String?,
    val method: MethodEnum,
    val amount: Double,
    val status: StatusEnum
)