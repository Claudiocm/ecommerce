package com.claudio.gobots.ecommerce.service.impl

import com.claudio.gobots.ecommerce.entity.Address
import org.springframework.stereotype.Service
import com.claudio.gobots.ecommerce.repository.AddressRepository
import com.claudio.gobots.ecommerce.service.AddressService

@Service
class AddressServiceImpl(val addressRepository: AddressRepository) : AddressService
{
    override fun saveAddress(address: Address): Address {
        return addressRepository.save(address)
    }
}