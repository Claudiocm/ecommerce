package com.claudio.gobots.ecommerce.repository

import com.claudio.gobots.ecommerce.entity.Address
import org.springframework.data.mongodb.repository.MongoRepository

interface AddressRepository : MongoRepository<Address, String> {
}