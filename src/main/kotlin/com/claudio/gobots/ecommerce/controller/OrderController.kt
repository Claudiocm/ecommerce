package com.claudio.gobots.ecommerce.controller

import com.claudio.gobots.ecommerce.dto.request.OrderConverter.toOrder
import com.claudio.gobots.ecommerce.dto.response.*
import com.claudio.gobots.ecommerce.entity.Item
import com.claudio.gobots.ecommerce.entity.Order
import com.claudio.gobots.ecommerce.service.ItemService
import com.claudio.gobots.ecommerce.service.OrderService
import com.claudio.gobots.ecommerce.service.PaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/orders")
class OrderController(
    val orderService: OrderService,
    val itemService: ItemService,
    val paymentService: PaymentService
) {
    @GetMapping("/search")
    fun getOrderPagination(
        @RequestParam(value = "limit", defaultValue = "10") limit: Int,
        @RequestParam(value = "offset", defaultValue = "0") offset: Int,
        @RequestParam(required = false) startDate: LocalDateTime,
        @RequestParam(required = false) endDate: LocalDateTime
    ): ResponseEntity<PaginatedResponse<OrderDTO>> {

        val pageRequest = orderService.listByOrderPaginated(limit, offset, startDate, endDate)
        val response = pageRequest.content.map {
            OrderDTO(
                it.orderId, it.createdAt, it.updatedAt, it.status.toString(),
                it.items, it.seller, it.buyer, it.shippingAddress, it.billingAddress, it.payment
            )
        }
        val paginatedResponse =
            PaginatedResponse(response, pageRequest.number, pageRequest.size, pageRequest.totalElements)

        return ResponseEntity.ok(paginatedResponse)
    }

    @GetMapping("/{orderId}")
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

        orderService.saveOrder(order)
        paymentService.savePayment(order.payment)

        response.data = fromOrder(order)
        return ResponseEntity.ok(response)
    }

}