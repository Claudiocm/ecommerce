package com.claudio.gobots.ecommerce.dto.response

import com.claudio.gobots.ecommerce.entity.*
import java.time.LocalDateTime
import java.util.*

data class OrderDTO(
    val orderId: UUID?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
    val status: String,
    val items: List<Item>,
    val sellerId: String,
    val buyer: Buyer,
    val shippingAddress: String,
    val billingAddress: String,
    val payment: Payment
)

data class ItemDTO(
    val id: String,
    val sku: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String
)

data class BuyerDTO(val id: UUID, val name: String, val email: String)

data class PaymentDTO(
    val method: PaymentMethod,
    val amount: Double,
    val status: OrderStatus
)

fun Order.toOrderDTO(): OrderDTO {
    return OrderDTO(
        orderId, createdAt, updatedAt, status.toString(), items, seller, buyer,
        shippingAddress,
        billingAddress,
        payment
    )
}

fun fromOrder(order: Order): OrderDTO {
    return OrderDTO(
        orderId = order.orderId,
        createdAt = order.createdAt,
        updatedAt = order.updatedAt,
        status = order.status.toString(),
        items = order.items,
        sellerId = order.seller,
        buyer = order.buyer,
        shippingAddress = order.shippingAddress,
        billingAddress = order.billingAddress,
        payment = order.payment
    )
}

fun toOrder(orderDTO: OrderDTO): Order {
    return Order(
        orderId = orderDTO.orderId,
        createdAt = orderDTO.createdAt,
        updatedAt = orderDTO.updatedAt,
        status = OrderEnum.valueOf(
            orderDTO.status.toString()
        ),
        items = orderDTO.items,
        seller = orderDTO.sellerId,
        buyer = orderDTO.buyer,
        shippingAddress = orderDTO.shippingAddress,
        billingAddress = orderDTO.billingAddress,
        payment = orderDTO.payment
    )
}


