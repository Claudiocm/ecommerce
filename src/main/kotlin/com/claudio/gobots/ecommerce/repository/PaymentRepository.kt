package com.claudio.gobots.ecommerce.repository

import com.claudio.gobots.ecommerce.entity.Payment
import org.springframework.data.mongodb.repository.MongoRepository

interface PaymentRepository : MongoRepository<Payment, String> {
    fun findPaymentByStatus(status: String): Payment
}