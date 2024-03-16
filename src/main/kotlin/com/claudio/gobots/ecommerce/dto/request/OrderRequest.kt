package com.claudio.gobots.ecommerce.dto.request

import com.claudio.gobots.ecommerce.entity.*
import dto.response.OrderDTO

data class OrderDTO(
    val orderId: String?,
    val createdAt: Long?,
    val updatedAt: Long?,
    val status: OrderEnum,
    val items: List<ItemDTO>,
    val sellerId: String,
    val buyer: String,
    val shippingAddress: Address,
    val billingAddress: Address,
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

data class AddressDTO(
    val id: String,
    val postalCode: String,
    val streetName: String,
    val number: String,
    val additionalInfo: String
)

data class PaymentDTO(
    val method: MethodEnum,
    val amount: Double,
    val status: StatusEnum
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
            items = orderDTO.items.map { toOrderItem(it) },
            seller = orderDTO.sellerId,
            buyer = orderDTO.buyer,
            shippingAddress = orderDTO.shippingAddress,
            billingAddress = orderDTO.billingAddress,
            payment = orderDTO.payment
        )
    }

    fun toOrderItem(itemDTO: dto.response.ItemDTO): Item {
        return Item(
            itemId = itemDTO.id,
            sku = itemDTO.sku,
            name = itemDTO.name,
            description = itemDTO.description,
            price = itemDTO.price,
            imageUrl = itemDTO.imageUrl
        )
    }

    private fun fromOrderToAddress(order: Order): AddressDTO {
        return AddressDTO(
            id = order.billingAddress.id.toString(),
            number = order.billingAddress.number,
            postalCode = order.billingAddress.postalCode,
            streetName = order.billingAddress.streetName,
            additionalInfo = order.billingAddress.additionalInfo
        )
    }
}
