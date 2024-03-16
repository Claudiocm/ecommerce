package com.claudio.gobots.ecommerce.controller

import com.claudio.gobots.ecommerce.dto.request.OrderConverter.toOrder
import dto.response.OrderDTO
import com.claudio.gobots.ecommerce.dto.response.Response
import dto.response.fromOrder
import com.claudio.gobots.ecommerce.entity.Item
import com.claudio.gobots.ecommerce.entity.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.claudio.gobots.ecommerce.service.AddressService
import com.claudio.gobots.ecommerce.service.ItemService
import com.claudio.gobots.ecommerce.service.OrderService
import com.claudio.gobots.ecommerce.service.PaymentService
import com.claudio.gobots.ecommerce.util.Utils.Companion.qtdPorPagina

@RestController
@RequestMapping("/api/v1/order")
class OrderController(
    val orderService: OrderService,
    val itemService: ItemService,
    val addressService: AddressService,
    val paymentService: PaymentService
) {
    @GetMapping("/{orderId}")
    fun getOrderPagination(
        @PathVariable("orderId") id: String,
        @RequestParam(value = "pag", defaultValue = "0") pag: Int,
        @RequestParam(value = "ord", defaultValue = "id") ord: String,
        @RequestParam(value = "dir", defaultValue = "DESC") dir: String
    ): ResponseEntity<Response<Page<OrderDTO>>> {
        val response: Response<Page<OrderDTO>> = Response()

        val pageRequest: PageRequest = PageRequest.of(pag, qtdPorPagina, Sort.Direction.valueOf(dir), ord)
        val pedidos: Page<Order> = orderService.listByOrderId(id, pageRequest)

        val orderDTO: Page<OrderDTO> = pedidos.map { order -> fromOrder(order) }

        response.data = orderDTO
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{orderId")
    fun getOrderById(@PathVariable("orderId") orderId: String): ResponseEntity<Response<OrderDTO>> {
        val response: Response<OrderDTO> = Response<OrderDTO>()
        val order: Order? = orderService.findOrderById(orderId)

        if (order == null) {
            response.erros.add("Order not found for id $orderId")
            return ResponseEntity.badRequest().body(response)
        }

        response.data = fromOrder(order)
        return ResponseEntity.ok(response)
    }

    @PostMapping
    fun postOrder(@RequestBody orderDTO: OrderDTO): ResponseEntity<Response<OrderDTO>> {
        val response: Response<OrderDTO> = Response()
        val order: Order = toOrder(orderDTO)
        val item: Item? = itemService.findByItemId(orderDTO.orderId)

        orderService.saveOrder(order)
        addressService.saveAddress(order.billingAddress)
        addressService.saveAddress(order.shippingAddress)
        paymentService.savePayment(order.payment)
        item?.let { itemService.saveItem(it) }

        response.data = fromOrder(order)
        return ResponseEntity.ok(response)
    }

}