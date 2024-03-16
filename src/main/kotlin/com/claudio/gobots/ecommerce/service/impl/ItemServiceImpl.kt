package com.claudio.gobots.ecommerce.service.impl

import com.claudio.gobots.ecommerce.entity.Item
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.claudio.gobots.ecommerce.repository.ItemRepository
import com.claudio.gobots.ecommerce.service.ItemService

@Service
class ItemServiceImpl(val itemRepository: ItemRepository) : ItemService {

    override fun listItemById(id: String, pageRequest: PageRequest): Page<Item> =
        itemRepository.findByItemId(id, pageRequest)

    override fun findByItemId(id: String): Item = itemRepository.findById(id).orElseThrow()

    override fun saveItem(item: Item): Item = itemRepository.save(item)
}