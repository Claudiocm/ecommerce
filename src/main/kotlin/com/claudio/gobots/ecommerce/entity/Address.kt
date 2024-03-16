package com.claudio.gobots.ecommerce.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "addresses")
data class Address(
    @Id
    val id: String?,
    val postalCode: String,
    val streetName: String,
    val number: String,
    val additionalInfo: String
) {
}