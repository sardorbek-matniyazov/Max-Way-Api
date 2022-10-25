package maxwayapi.service

import maxwayapi.dao.SuperResponse
import maxwayapi.dto.UserDto
import maxwayapi.model.User
import maxwayapi.repository.UserRepository
import maxwayapi.service.functionality.Creatable
import maxwayapi.service.functionality.InstanceReturnable
import maxwayapi.service.functionality.Updatable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired private val repository: UserRepository
) : InstanceReturnable<User>, Creatable<UserDto>, Updatable<Long, UserDto> {
    override fun getInstanceWithId(id: Long): User = repository.findById(id).orElse(null)

    override fun getAllInstances(): List<User> = repository.findAll(Sort.by(Sort.Direction.DESC, "id"))

    override fun create(dto: UserDto) = if (!repository.existsByPhoneNumber(dto.phoneNumber)) {
        SuperResponse.CREATED_SUCCESSFULLY.setData(repository.save(dto.toUserEntity()))
    } else SuperResponse.PHONE_NUMBER_TAKEN

    override fun update(id: Long, dto: UserDto) = if (!repository.existsByPhoneNumberAndIdIsNot(dto.phoneNumber, id)) {
        SuperResponse.UPDATED_SUCCESSFULLY.setData(
            repository.findById(id).map {
                if (it == null) SuperResponse.USER_NOT_FOUND
                else repository.save(it.setFullNameAndPhoneNumber(dto.fullName, dto.phoneNumber))
            }
        )
    } else SuperResponse.PHONE_NUMBER_TAKEN
}
