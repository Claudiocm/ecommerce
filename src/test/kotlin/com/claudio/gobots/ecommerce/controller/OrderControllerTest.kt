package com.claudio.gobots.ecommerce.controller

import com.claudio.gobots.ecommerce.service.impl.OrderServiceImpl
import com.claudio.gobots.ecommerce.util.Utils
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Profile
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@SpringBootTest
@WebMvcTest
@Profile("test")
class OrderControllerTest(
    @Autowired
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {
    @MockBean
    lateinit var orderService: OrderServiceImpl
    private val url: String = "/api/v1/orders"

    @Test
    fun returnOkWhenFindOrders() {

    }

    @Test
    fun returnOkWhenFindOrderById() {
        val order = Utils.order

        every { orderService.findOrderById(order.orderId.toString()) } returns order
        mockMvc.perform(get(url + "/" + order.orderId))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(ResultMatcher { jsonPath("$.orderId").value(UUID.randomUUID()) })

        verify(exactly = 1) { orderService.findOrderById(any()) }
    }

    @Test
    fun returnOkWhenPostOrder() {
        val order = Utils.order

        every { orderService.saveOrder(any()) } returns order

        mockMvc.perform(
            post(url).content(objectMapper.writeValueAsString(order)).contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))

        verify(exactly = 1) { orderService.saveOrder(any()) }
    }

}