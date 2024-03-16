package com.claudio.gobots.ecommerce.service.impl

import com.claudio.gobots.ecommerce.entity.Order
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.claudio.gobots.ecommerce.repository.OrderRepository
import com.claudio.gobots.ecommerce.service.OrderService

@Service
class OrderServiceImpl(
    val orderRepository: OrderRepository
) : OrderService {

    override fun listByOrderId(id: String, pageRequest: PageRequest): Page<Order> {

        if (id.isNotBlank()) {
            throw NotFoundException()
        }
        return orderRepository.findByOrderId(id, pageRequest);
    }

    override fun findOrderById(id: String): Order? {

        return orderRepository.findById(id).orElseThrow();
    }

    override fun saveOrder(order: Order): Order {
        return orderRepository.save(order)
    }
}