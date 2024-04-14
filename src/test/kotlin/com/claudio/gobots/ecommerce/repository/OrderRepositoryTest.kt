package com.claudio.gobots.ecommerce.repository

import com.claudio.gobots.ecommerce.util.Utils
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.mongodb.core.MongoOperations

@SpringBootTest
@ExtendWith
class OrderRepositoryTest {

    @MockBean
    lateinit var orderRepository: OrderRepository

    @Mock
    lateinit var mongodbOperations: MongoOperations

    @Test
    fun returnOrderOrderWhenFindOrderId() {
        val order = Utils.order
    }

    @Test
    fun returnWithOrderWhenSearchOrder() {
        val order = Utils.order
        val orders = listOf(order)
        Mockito.`when`(orderRepository.findAll()).thenReturn(orders);

        assertEquals("1", order.orderId.toString())

        verify(exactly = 1) { orderRepository.findAll() }

    }

}