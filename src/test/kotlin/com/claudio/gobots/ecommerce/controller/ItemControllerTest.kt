package com.claudio.gobots.ecommerce.controller

import com.claudio.gobots.ecommerce.entity.Item
import com.claudio.gobots.ecommerce.service.impl.ItemServiceImpl
import com.claudio.gobots.ecommerce.util.Utils
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Profile
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@ExtendWith(MockitoExtension::class)
@Profile("test")
class ItemControllerTest(
    @Autowired
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {
    @MockBean
    lateinit var itemService: ItemServiceImpl
    private val url: String = "/api/v1/items"

    @Test
    fun returnOkWhenFindItems() {
        val items: List<Item> = Utils.itemList()

        every { itemService.findAll() } returns items

        mockMvc.perform(get(url))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))

        verify(exactly = 1) { itemService.findAll() }
    }

    @Test
    fun returnOkWhenFindItemById() {
        val item = Utils.item

        every { itemService.findByItemId(item.itemId!!) } returns item
        mockMvc.perform(get(url + "" + item.itemId))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(ResultMatcher { jsonPath("$.itemId").value("BCD456") })

        verify(exactly = 1) { itemService.findByItemId(any()) }
    }

    @Test
    fun returnOkWhenPostItem() {
        val item = Utils.item

        every { itemService.saveItem(item) } returns item

        mockMvc.perform(
            post(url).content(objectMapper.writeValueAsString(item)).contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))

        verify(exactly = 1) { itemService.saveItem(any()) }
    }

}