package maxwayapi.dto

import maxwayapi.model.Role
import maxwayapi.model.User
import javax.validation.constraints.NotBlank

data class UserDto(
    @field:NotBlank(message = "User's full name is required")
    val fullName: String = "",
    @field:NotBlank(message = "User's phone number is required")
    val phoneNumber: String = ""
) {
    fun toUserEntity(token: String, role: Role) = User(fullName, phoneNumber, token, role)
}
