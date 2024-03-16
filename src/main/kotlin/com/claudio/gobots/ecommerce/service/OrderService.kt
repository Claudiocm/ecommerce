package com.claudio.gobots.ecommerce.service

import com.claudio.gobots.ecommerce.entity.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface OrderService {
    fun listByOrderId(id:String, pageRequest: PageRequest): Page<Order>

    fun findOrderById(id: String): Order?

    fun saveOrder(order: Order): Order
}