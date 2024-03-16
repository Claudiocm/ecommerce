package com.claudio.gobots.ecommerce.controller

import dto.response.ItemDTO
import dto.response.ItemsConverter.fromItem
import dto.response.ItemsConverter.toItem
import com.claudio.gobots.ecommerce.dto.response.Response
import com.claudio.gobots.ecommerce.entity.Item
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.claudio.gobots.ecommerce.service.ItemService
import com.claudio.gobots.ecommerce.util.Utils.Companion.qtdPorPagina

@RestController
@RequestMapping("/api/v1/items")
class ItemController(
    val itemService: ItemService
) {

    @GetMapping("/{itemId}")
    fun getItemsById(
        @PathVariable("itemId") itemId: String,
        @RequestParam(value = "pag", defaultValue = "0") pag: Int,
        @RequestParam(value = "ord", defaultValue = "id") ord: String,
        @RequestParam(value = "dir", defaultValue = "DESC") dir: String
    ): ResponseEntity<Response<Page<ItemDTO>>> {

        val response: Response<Page<ItemDTO>> = Response()
        val pageRequest: PageRequest = PageRequest.of(
            pag,
            qtdPorPagina, Sort.Direction.valueOf(dir), ord
        )
        val items: Page<Item> = itemService.listItemById(itemId, pageRequest)
        val itemDTO: Page<ItemDTO> = items.map { item -> fromItem(item) }

        response.data = itemDTO
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{itemId}")
    fun getItemsById(@PathVariable("itemId") itemId: String): ResponseEntity<Response<ItemDTO>> {
        val response: Response<ItemDTO> = Response<ItemDTO>()
        val item: Item? = itemService.findByItemId(itemId)

        if (item == null) {
            response.erros.add("item not found for id $itemId")
            return ResponseEntity.badRequest().body(response)
        }

        response.data = fromItem(item)
        return ResponseEntity.ok(response)
    }

    @PostMapping()
    fun postItem(@RequestBody itemDTO: ItemDTO): ResponseEntity<Response<ItemDTO>> {
        val response: Response<ItemDTO> = Response()
        val item: Item = toItem(itemDTO)
        itemService.saveItem(item)
        response.data = fromItem(item)
        return ResponseEntity.ok(response)
    }

}