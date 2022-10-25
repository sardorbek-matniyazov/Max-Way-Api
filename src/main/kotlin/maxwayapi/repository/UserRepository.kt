package maxwayapi.repository

import maxwayapi.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun existsByPhoneNumber(phoneNumber: String?): Boolean
    fun existsByPhoneNumberAndIdIsNot(phoneNumber: String, id: Long): Boolean
}
