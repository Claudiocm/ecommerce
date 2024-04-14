package com.claudio.gobots.ecommerce.service.impl

import com.claudio.gobots.ecommerce.entity.Payment
import org.springframework.stereotype.Service
import com.claudio.gobots.ecommerce.repository.PaymentRepository
import com.claudio.gobots.ecommerce.service.PaymentService

@Service
class PaymentServiceImpl(val paymentRepository: PaymentRepository) : PaymentService {
    override fun savePayment(payment: Payment): Payment = paymentRepository.save(payment)

    override fun findPaymentByStatus(status: String): Payment = paymentRepository.findPaymentByStatus(status)
}