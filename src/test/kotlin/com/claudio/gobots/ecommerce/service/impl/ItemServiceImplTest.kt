package com.claudio.gobots.ecommerce.service.impl

import com.claudio.gobots.ecommerce.entity.Item
import com.claudio.gobots.ecommerce.repository.ItemRepository
import com.claudio.gobots.ecommerce.util.Utils
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile

@SpringBootTest
@ExtendWith(MockitoExtension::class)
@Profile("test")
class ItemServiceImplTest {
    @Mock
    lateinit var itemRepository: ItemRepository

    @InjectMocks
    lateinit var itemService: ItemServiceImpl

    @Test
    fun returnItemsWhenSearchItems() {
        val items: List<Item> = Utils.itemList()
        every { itemRepository.findAll()} returns items
        val result = itemService.findAll()

        assertThat(result)
        verify(exactly = 1) { itemRepository.findAll() }
    }

    @Test
    fun returnItemWhenSaveItem(){
        val item = Utils.item
        every { itemRepository.save(any())} returns item
        val result = itemService.saveItem(item)

        assertThat(result)
        verify(exactly = 1) { itemRepository.save(any()) }
    }

    @Test
    fun returnItemWhenFindByItemId() {
         every { itemRepository.findByItemId("BCD456") } returns Utils.item
         val result = itemService.findByItemId("BCD456")

         assertThat(result)

         verify(exactly = 1){ itemRepository.findByItemId(anyString()) }
         verify(exactly = 1) { itemService.findByItemId(anyString()) }
    }


}