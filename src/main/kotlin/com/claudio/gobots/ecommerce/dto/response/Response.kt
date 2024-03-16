package com.claudio.gobots.ecommerce.dto.response

data class Response<T>(
    val erros: ArrayList<String> = arrayListOf(),
    var data: T? = null
) {

}
