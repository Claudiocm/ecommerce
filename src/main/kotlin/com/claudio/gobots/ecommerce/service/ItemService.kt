package com.claudio.gobots.ecommerce.service

import com.claudio.gobots.ecommerce.entity.Item
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface ItemService {
    fun listItemById(id: String, pageRequest: PageRequest): Page<Item>

    fun findByItemId(id: String): Item?

    fun saveItem(item: Item): Item
}