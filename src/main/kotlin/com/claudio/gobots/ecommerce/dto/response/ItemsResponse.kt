package com.claudio.gobots.ecommerce.dto.response

import com.claudio.gobots.ecommerce.dto.request.ItemRequest
import com.claudio.gobots.ecommerce.entity.Item
import com.claudio.gobots.ecommerce.entity.Order

data class ItemsResponse(
    val id: String,
    val sku: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String
)

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

fun Item.toItemRequest(): ItemRequest {
    return ItemRequest( itemId, sku, name, description, price, imageUrl)
}

fun toItem(itemDTO: ItemDTO): Item {
    return Item(
        itemId = itemDTO.id, sku = itemDTO.sku, name = itemDTO.name, description = itemDTO.description,
        price = itemDTO.price, imageUrl = itemDTO.imageUrl
    )
}

fun Item.toDTO(): ItemDTO {
    return ItemDTO(itemId!!, sku, name, description, price, imageUrl)
}

fun itemList(): List<ItemDTO> {
    return  listOf(ItemDTO(
        id = "123456789",
        sku = "abc123456",
        name = "Bolsa de couro",
        description = "Bolsa de couro feminino tipo Channel",
        price = 1000.00,
        imageUrl = ""
    ),
        ItemDTO(
            id = "198765432",
            sku = "DEF123456",
            name = "Calça sarja",
            description = "Calça sarja, estilo malhado",
            price = 300.00,
            imageUrl = ""
        )
    )
}
