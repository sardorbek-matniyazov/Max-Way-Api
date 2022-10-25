package maxwayapi.dto

import maxwayapi.model.Address
import maxwayapi.model.User
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UserDto(
    @field:NotBlank(message = "User's full name is required")
    val fullName: String = "",
    @field:NotBlank(message = "User's phone number is required")
    val phoneNumber: String = "",
    @field:NotNull(message = "User's address is required")
    val address: Address? = null
) {
    fun toUserEntity() = User(fullName, phoneNumber, address!!)
}
