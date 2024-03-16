package com.claudio.gobots.ecommerce.service

import com.claudio.gobots.ecommerce.entity.Address

interface AddressService {
    fun saveAddress(address: Address): Address
}