package maxwayapi.repository

import maxwayapi.model.Role
import maxwayapi.model.enums.RoleName
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RoleRepository: JpaRepository<Role, Long> {
    fun findByRoleName(user: RoleName): Optional<Role>

}
