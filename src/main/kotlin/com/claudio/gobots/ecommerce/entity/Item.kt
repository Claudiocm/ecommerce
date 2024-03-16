package com.claudio.gobots.ecommerce.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "items")
class Item(
    @Id
    val itemId: String?,
    val sku: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String
)
