package maxwayapi.dto

import javax.validation.constraints.NotNull

data class OrderItemDto (
    @field:NotNull(message = "Product id is required")
    val productId: Long,
    @field:NotNull(message = "Product quantity is required")
    val quantity: Int
)
