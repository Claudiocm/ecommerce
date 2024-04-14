package com.claudio.gobots.ecommerce.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.stereotype.Repository

@Repository
class CustomOrderRepository(@Autowired val mongoOperations: MongoOperations) {

}