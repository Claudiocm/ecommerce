package com.claudio.gobots.ecommerce.util

import com.claudio.gobots.ecommerce.entity.*
import org.springframework.beans.factory.annotation.Value
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class Utils {
    companion object {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        @Value("\${paginacao.qtd_por_pagina}")
        val qtdPorPagina: Int = 10

        val order = Order(
            orderId = UUID.randomUUID(),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now().plusSeconds(10),
            status = OrderEnum.NEW,
            items = listOf(
                Item(
                    itemId = "BCD456", sku = "GFD123456", name = "Calça brim",
                    description = "Calça brim parda", 100.00, ""
                )
            ),
            seller = "123-456-789",
            buyer = Buyer(UUID.randomUUID(), "Claudio Costa Matos", "meuemail@email.com"),
            shippingAddress = "Rua do comercio, 100, Jardim Real - SP",
            billingAddress = "Rua das aroeiras, 8, Paraiso - SP",
            payment = Payment(
                id = "PAY-123456", method = PaymentMethod.DEBIT, 100.00, status = OrderStatus.PENDING
            )
        )

        val item = Item(
            itemId = "BCD456", sku = "GFD123456", name = "Calça brim",
            description = "Calça brim parda", 100.00, ""
        )

        fun itemList(): List<Item> {
            val list = listOf(
                Item(
                    itemId = "123456789",
                    sku = "abc123456",
                    name = "Bolsa de couro",
                    description = "Bolsa de couro feminino tipo Channel",
                    price = 1000.00,
                    imageUrl = ""
                ),
                Item(
                    itemId = "198765432",
                    sku = "DEF123456",
                    name = "Calça sarja",
                    description = "Calça sarja, estilo malhado",
                    price = 300.00,
                    imageUrl = ""
                )
            )
            return list
        }
    }


}