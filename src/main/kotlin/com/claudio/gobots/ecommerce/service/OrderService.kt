package com.claudio.gobots.ecommerce.service

import com.claudio.gobots.ecommerce.entity.Order
import org.springframework.data.domain.Page
import java.time.LocalDateTime

interface OrderService {
    fun listByOrderPaginated(limit: Int, offset: Int, startDate: LocalDateTime?, endDate: LocalDateTime): Page<Order>

    fun findOrderById(id: String): Order?

    fun saveOrder(order: Order): Order
}