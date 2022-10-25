package maxwayapi.service

import maxwayapi.dao.SuperResponse
import maxwayapi.dto.CategoryDto
import maxwayapi.model.Category
import maxwayapi.repository.CategoryRepository
import maxwayapi.service.functionality.Creatable
import maxwayapi.service.functionality.Deletable
import maxwayapi.service.functionality.InstanceReturnable
import maxwayapi.service.functionality.Updatable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val repository: CategoryRepository
) : InstanceReturnable<Category>, Updatable<Long, CategoryDto>, Deletable<Long>, Creatable<CategoryDto>
{

    override fun getInstanceWithId(id: Long): Category = repository.findById(id).orElse(null)

    override fun getAllInstances(): List<Category> = repository.findAll(Sort.by(Sort.Direction.DESC, "id"))

    override fun create(dto: CategoryDto) = if (!repository.existsByName(dto.categoryName)) {
        SuperResponse.CREATED_SUCCESSFULLY.setData(repository.save(dto.toCategoryEntity()))
    } else SuperResponse.CATEGORY_EXISTS

    override fun delete(id: Long): SuperResponse {
        TODO("Not yet implemented")
    }

    override fun update(id: Long, dto: CategoryDto): SuperResponse {
        TODO("Not yet implemented")
    }
}