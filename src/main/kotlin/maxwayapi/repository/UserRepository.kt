package maxwayapi.repository

import maxwayapi.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, Long> {
    fun existsByPhoneNumber(phoneNumber: String?): Boolean
    fun existsByPhoneNumberAndIdIsNot(phoneNumber: String, id: Long): Boolean
    fun findByPhoneNumber(username: String): Optional<User?>
    fun findByFullNameAndPhoneNumber(fullName: String, phoneNumber: String): Optional<User?>
}
