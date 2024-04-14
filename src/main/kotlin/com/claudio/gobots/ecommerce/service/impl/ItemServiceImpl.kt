package com.claudio.gobots.ecommerce.service.impl

import com.claudio.gobots.ecommerce.entity.Item
import com.claudio.gobots.ecommerce.entity.Order
import com.claudio.gobots.ecommerce.exception.BadRequestException
import com.claudio.gobots.ecommerce.exception.NotFoundException
import com.claudio.gobots.ecommerce.repository.ItemRepository
import com.claudio.gobots.ecommerce.service.ItemService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ItemServiceImpl(val itemRepository: ItemRepository) : ItemService {

    override fun findByItemId(id: String): Item? {
        if(id.isEmpty()){
            throw NotFoundException("Not Found Item id!: ")
        }
        val itemId = itemRepository.findByItemId(id)
        return itemId
    }

    override
    fun saveItem(item: Item): Item {
        if(item.itemId.isNullOrEmpty()){
            throw BadRequestException("The item id cannot have empty or null")
        }
        val save = itemRepository.save(item)
        return save
    }

    override
    fun findAll(): List<Item> {
        val list = itemRepository.findAll()
        return list
    }

    override fun listItemPaginated(
        limit: Int,
        offset: Int,
        startDate: LocalDateTime?,
        endDate: LocalDateTime
    ): Page<Item> {
        val pageable = PageRequest.of(offset, limit, Sort.by("createdAt").descending())
        return itemRepository.findAll(
            pageable
        )
    }
}