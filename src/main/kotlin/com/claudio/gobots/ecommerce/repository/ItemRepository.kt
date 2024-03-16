package com.claudio.gobots.ecommerce.repository

import com.claudio.gobots.ecommerce.entity.Item
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository


interface ItemRepository : MongoRepository<Item, String> {
    fun findByItemId(id: String, pageable: Pageable): Page<Item>
}