package com.claudio.gobots.ecommerce.dto.response

data class PaginatedResponse<T>(
    val content: List<T>,
    val page: Int,
    val size: Int,
    val totalElements: Long
)