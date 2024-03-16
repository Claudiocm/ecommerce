package com.claudio.gobots.ecommerce.repository

import com.claudio.gobots.ecommerce.entity.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : MongoRepository<Order, String> {
    fun findByOrderId(orderId: String, pageable: Pageable): Page<Order>
}