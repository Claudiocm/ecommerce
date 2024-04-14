package com.claudio.gobots.ecommerce.controller

import com.claudio.gobots.ecommerce.dto.response.*
import com.claudio.gobots.ecommerce.entity.Item
import com.claudio.gobots.ecommerce.service.ItemService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/items")
class ItemController(
    val itemService: ItemService
) {

    @GetMapping("/search")
    fun getItemsPaginated(
        @RequestParam(value = "limit", defaultValue = "10") limit: Int,
        @RequestParam(value = "offset", defaultValue = "0") offset: Int,
        @RequestParam(required = false) startDate: LocalDateTime,
        @RequestParam(required = false) endDate: LocalDateTime
    ): ResponseEntity<PaginatedResponse<ItemDTO>> {

        val pageRequest = itemService.listItemPaginated(limit, offset, startDate, endDate)
        val response = pageRequest.content.map {
            ItemDTO(
                it.itemId!!, it.sku, it.name, it.description,it.price, it.imageUrl
            )
        }
        val paginatedResponse =
            PaginatedResponse(response, pageRequest.number, pageRequest.size, pageRequest.totalElements)

        return ResponseEntity.ok(paginatedResponse)
    }

    @GetMapping("/{itemId}")
    fun getItemById(@PathVariable("itemId") itemId: String): ResponseEntity<Response<ItemDTO>> {
        val response: Response<ItemDTO> = Response<ItemDTO>()
        val item = itemService.findByItemId(itemId)

        if (item == null) {
            response.erros.add("item not found for id $itemId")
            return ResponseEntity.noContent().build()
        }

        response.data = fromItem(item)
        return ResponseEntity.ok(response)
    }

    @PostMapping
    fun postItem(@RequestBody itemDTO: ItemDTO): ResponseEntity<Response<ItemDTO>> {
        val response: Response<ItemDTO> = Response()
        val item: Item = toItem(itemDTO)
        itemService.saveItem(item)
        response.data = fromItem(item)
        return ResponseEntity.ok(response)
    }

    @GetMapping
    fun getAllItem(): ResponseEntity<Response<List<ItemDTO>>> {
        val itemList: List<Item> = itemService.findAll()
        val itemDTOList: List<ItemDTO> = itemList.map { it.toDTO() }
        val response = Response(data = itemDTOList)
        return ResponseEntity.ok(response)
    }

}