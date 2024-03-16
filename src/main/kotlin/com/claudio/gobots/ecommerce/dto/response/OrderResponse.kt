package dto.response

import com.claudio.gobots.ecommerce.entity.*
import com.claudio.gobots.ecommerce.dto.request.AddressDTO
import com.claudio.gobots.ecommerce.dto.request.OrderConverter

data class OrderDTO(
    val orderId: String,
    val createdAt: Long?,
    val updatedAt: Long?,
    val status: String,
    val items: List<ItemDTO>,
    val sellerId: String,
    val buyer: String,
    val shippingAddress: Address,
    val billingAddress: Address,
    val payment: Payment
)

data class AddressDTO(
    val id: String?,
    val postalCode: String,
    val streetName: String,
    val number: String,
    val additionalInfo: String
)

data class ItemDTO(
    val id: String,
    val sku: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String
)

data class PaymentDTO(
    val method: MethodEnum,
    val amount: Double,
    val status: StatusEnum
)

object ItemsConverter {
    fun fromItem(item: Item): ItemDTO {
        return ItemDTO(
            id = item.itemId.toString(),
            sku = item.sku,
            name = item.name,
            description = item.description,
            price = item.price,
            imageUrl = item.imageUrl
        )
    }

    fun toItem(itemDTO: ItemDTO): Item {
        return Item(
            itemId = itemDTO.id, sku = itemDTO.sku, name = itemDTO.name, description = itemDTO.description,
            price = itemDTO.price, imageUrl = itemDTO.imageUrl
        )
    }
}

fun fromOrder(order: Order): OrderDTO {
    return OrderDTO(
        orderId = order.toString(),
        createdAt = order.createdAt,
        updatedAt = order.updatedAt,
        status = order.status.toString(),
        items = order.items.map {
            ItemDTO(
                id = it.itemId.toString(),
                sku = it.sku,
                name = it.name,
                description = it.description,
                price = it.price,
                imageUrl = it.imageUrl
            )
        },
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
        items = orderDTO.items.map { OrderConverter.toOrderItem(it) },
        seller = orderDTO.sellerId,
        buyer = orderDTO.buyer,
        shippingAddress = orderDTO.shippingAddress,
        billingAddress = orderDTO.billingAddress,
        payment = orderDTO.payment
    )
}

fun toAddress(addressDTO: AddressDTO): Address {
    return Address(
        id = addressDTO.id,
        postalCode = addressDTO.postalCode,
        streetName = addressDTO.streetName,
        number = addressDTO.number,
        additionalInfo = addressDTO.additionalInfo
    )
}

fun fromAddress(address: Address): AddressDTO {
    return AddressDTO(
        id = address.id.toString(),
        postalCode = address.postalCode,
        streetName = address.streetName,
        number = address.number,
        additionalInfo = address.additionalInfo
    )
}

