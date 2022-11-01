package maxwayapi.service

import maxwayapi.dao.SuperResponse
import maxwayapi.dto.UserDto
import maxwayapi.model.User
import maxwayapi.model.enums.RoleName
import maxwayapi.repository.RoleRepository
import maxwayapi.repository.UserRepository
import maxwayapi.security.JwtMatcher
import maxwayapi.service.functionality.Creatable
import maxwayapi.service.functionality.InstanceReturnable
import maxwayapi.service.functionality.Updatable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired private val repository: UserRepository,
    @Autowired private val roleRepository: RoleRepository,
    @Autowired private val jwtMatcher: JwtMatcher
) : InstanceReturnable<User>, Creatable<UserDto>, Updatable<Long, UserDto> {
    override fun getInstanceWithId(id: Long): User = repository.findById(id).orElse(null)

    override fun getAllInstances(): List<User> = repository.findAll(Sort.by(Sort.Direction.DESC, "id"))

    override fun register(dto: UserDto): SuperResponse =
        repository.findByFullNameAndPhoneNumber(dto.fullName, dto.phoneNumber).map {
            it!!.token = jwtMatcher.generateToken(it.phoneNumber)!!
            println(it)
            SuperResponse.LOGIN_SUCCESSFULLY.setData(
                repository.save(it).token
            )
        }.orElse(
            if (repository.existsByPhoneNumber(dto.phoneNumber)) {
                SuperResponse.PHONE_NUMBER_TAKEN
            } else {
                SuperResponse.LOGIN_SUCCESSFULLY.setData(
                    repository.save(
                        dto.toUserEntity(
                            jwtMatcher.generateToken(
                                dto.phoneNumber
                            )!!,
                            roleRepository.findByRoleName(RoleName.USER).get()
                        )
                    ).token
                )
            }
        )

    override fun update(id: Long, dto: UserDto) = if (!repository.existsByPhoneNumberAndIdIsNot(dto.phoneNumber, id)) {
        repository.findById(id).map {
            if (it == null) SuperResponse.USER_NOT_FOUND
            else SuperResponse.UPDATED_SUCCESSFULLY.setData(
                repository.save(
                    it.setFullNameAndPhoneNumber(
                        dto.fullName,
                        dto.phoneNumber
                    )
                )
            )
        }.get()
    } else SuperResponse.PHONE_NUMBER_TAKEN
}
