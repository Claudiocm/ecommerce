package com.claudio.gobots.ecommerce.dto.request

import com.claudio.gobots.ecommerce.entity.Item
import org.springframework.data.domain.Page

data class ItemRequest(
    val id: String?,
    val sku: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String
)

