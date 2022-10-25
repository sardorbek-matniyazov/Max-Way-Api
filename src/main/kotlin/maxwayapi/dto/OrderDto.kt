package maxwayapi.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class OrderDto (
    @field:NotNull(message = "User id is required")
    val userId: Long,
    @field:NotNull(message = "Order products are required")
    val items: HashSet<OrderItemDto>,
    @field:NotBlank(message = "Comment is required")
    val comment: String
)
