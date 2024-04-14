package com.claudio.gobots.ecommerce.dto.request

import com.claudio.gobots.ecommerce.entity.*
import com.fasterxml.jackson.annotation.JsonInclude
import com.claudio.gobots.ecommerce.dto.response.OrderDTO
import java.util.*

@JsonInclude
data class OrderDTO(
    val orderId: UUID?,
    val createdAt: Long?,
    val updatedAt: Long?,
    val status: OrderEnum,
    val items: List<ItemDTO>,
    val sellerId: String,
    val buyer: String,
    val shippingAddress: String,
    val billingAddress: String,
    val payment: Payment
)

@JsonInclude
data class ItemDTO(
    var id: String,
    var sku: String,
    var name: String,
    var description: String,
    var price: Double,
    var imageUrl: String
)

@JsonInclude
data class PaymentDTO(
    val method: PaymentMethod,
    val amount: Double,
    val status: OrderStatus
)

object OrderConverter {
    fun toOrder(orderDTO: OrderDTO): Order {
        return Order(
            orderId = orderDTO.orderId,
            createdAt = orderDTO.createdAt,
            updatedAt = orderDTO.updatedAt,
            status = OrderEnum.valueOf(
                orderDTO.status
            ),
            items = orderDTO.items,
            seller = orderDTO.sellerId,
            buyer = orderDTO.buyer,
            shippingAddress = orderDTO.shippingAddress,
            billingAddress = orderDTO.billingAddress,
            payment = orderDTO.payment
        )
    }

    fun toOrderItem(itemDTO: com.claudio.gobots.ecommerce.dto.response.ItemDTO): Item {
        return Item(
            itemId = itemDTO.id,
            sku = itemDTO.sku,
            name = itemDTO.name,
            description = itemDTO.description,
            price = itemDTO.price,
            imageUrl = itemDTO.imageUrl
        )
    }

}
