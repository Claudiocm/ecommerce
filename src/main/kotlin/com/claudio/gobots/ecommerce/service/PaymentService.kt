package com.claudio.gobots.ecommerce.service

import com.claudio.gobots.ecommerce.entity.Payment

interface PaymentService {
    fun savePayment(payment: Payment): Payment

    fun findPaymentByStatus(status: String): Payment
}