package com.claudio.gobots.ecommerce.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "buyers")
data class Buyer(@Id val id: UUID, val name: String, val email: String) {
}