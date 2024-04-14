package com.claudio.gobots.ecommerce.service.impl

import com.claudio.gobots.ecommerce.entity.Order
import com.claudio.gobots.ecommerce.exception.BadRequestException
import com.claudio.gobots.ecommerce.exception.NotFoundException
import com.claudio.gobots.ecommerce.repository.OrderRepository
import com.claudio.gobots.ecommerce.service.OrderService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderServiceImpl(
    val orderRepository: OrderRepository
) : OrderService {
    override fun listByOrderPaginated(
        limit: Int,
        offset: Int,
        startDate: LocalDateTime?,
        endDate: LocalDateTime
    ): Page<Order> {
        val pageable = PageRequest.of(offset, limit, Sort.by("createdAt").descending())
        return orderRepository.findAll(
            pageable
        )
    }


    override fun findOrderById(id: String): Order? {
        return orderRepository.findById(id).orElseThrow{
            throw NotFoundException("Not Found orderId!")
        }
    }

    override fun saveOrder(order: Order): Order {
        if(order.orderId == null){
          throw BadRequestException("The order id cannot have empty or null!")
        }
        return orderRepository.save(order)
    }
}