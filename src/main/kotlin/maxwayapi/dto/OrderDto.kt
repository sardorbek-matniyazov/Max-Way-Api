package maxwayapi.dto

import maxwayapi.model.Address
import maxwayapi.model.enums.OrderType
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class OrderDto (
    @field:NotNull(message = "Order products are required")
    val allOrderValue: HashSet<OrderItemDto>,
    @field:NotBlank(message = "Comment is required")
    val comment: String,
    @field:NotNull(message = "Order type is required")
    val orderType: OrderType,
    val address: Address? = null
)
