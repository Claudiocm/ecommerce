package com.claudio.gobots.ecommerce.service.notification

import lombok.AllArgsConstructor
import lombok.Data

@AllArgsConstructor
@Data
data class NotificationDTO(
    val message: Boolean
)