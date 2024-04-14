package com.claudio.gobots.ecommerce.service

import com.claudio.gobots.ecommerce.entity.Item
import org.springframework.data.domain.Page
import java.time.LocalDateTime

interface ItemService {
    fun findAll(): List<Item>

    fun listItemPaginated(limit: Int, offset: Int, startDate: LocalDateTime?, endDate: LocalDateTime): Page<Item>

    fun findByItemId(id: String): Item?

    fun saveItem(item: Item): Item
}