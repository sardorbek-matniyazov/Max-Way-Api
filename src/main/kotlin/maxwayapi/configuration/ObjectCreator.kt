package maxwayapi.configuration

import maxwayapi.model.Role
import maxwayapi.model.enums.RoleName
import maxwayapi.repository.RoleRepository
import maxwayapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

//@Component
class ObjectCreator(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val roleRepository: RoleRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {

        roleRepository.save(
            Role(RoleName.USER)
        )
        roleRepository.save(
            Role(RoleName.ADMIN)
        )
    }
}