package com.claudio.gobots.ecommerce.controller

import com.claudio.gobots.ecommerce.dto.request.AddressDTO
import com.claudio.gobots.ecommerce.dto.response.Response
import dto.response.fromAddress
import dto.response.toAddress
import com.claudio.gobots.ecommerce.entity.Address
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.claudio.gobots.ecommerce.service.AddressService

@RestController
@RequestMapping("/address")
class AddressController(
    val addressService: AddressService
) {
    @PostMapping()
    fun postAddress(@RequestBody addressDTO: AddressDTO): ResponseEntity<Response<AddressDTO>> {
        val response: Response<AddressDTO> = Response()
        val address: Address = toAddress(addressDTO)
        addressService.saveAddress(address)
        response.data = fromAddress(address)
        return ResponseEntity.ok(response)
    }
}