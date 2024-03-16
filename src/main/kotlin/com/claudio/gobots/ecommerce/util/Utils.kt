package com.claudio.gobots.ecommerce.util

import org.springframework.beans.factory.annotation.Value
import java.text.SimpleDateFormat

class Utils {
    companion object {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        @Value("\${paginacao.qtd_por_pagina}")
        val qtdPorPagina: Int = 10
    }
}