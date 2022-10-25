package maxwayapi.service

import maxwayapi.dao.SuperResponse
import maxwayapi.dto.ProductDto
import maxwayapi.model.Product
import maxwayapi.repository.CategoryRepository
import maxwayapi.repository.ProductRepository
import maxwayapi.service.functionality.Creatable
import maxwayapi.service.functionality.InstanceReturnable
import maxwayapi.service.functionality.Updatable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class ProductService(
    @Autowired private val repository: ProductRepository,
    @Autowired private val categoryRepository: CategoryRepository
) : InstanceReturnable<Product>, Creatable<ProductDto>, Updatable<Long, ProductDto> {

    override fun create(dto: ProductDto) = SuperResponse.CREATED_SUCCESSFULLY.setData(
        if (repository.existsByName(dto.name)) SuperResponse.PRODUCT_EXISTS
        else categoryRepository.findById(dto.categoryId).map {
            if (it == null) SuperResponse.CATEGORY_NOT_FOUND
            else repository.save(dto.toProductEntity().setCategory(it))
        }

    )

    override fun getInstanceWithId(id: Long): Product = repository.findById(id).orElse(null)

    override fun getAllInstances(): List<Product> = repository.findAll(Sort.by(Sort.Direction.DESC, "id"))

    override fun update(id: Long, dto: ProductDto): SuperResponse = if (!repository.existsByNameAndIdIsNot(dto.name, id)) {
        SuperResponse.UPDATED_SUCCESSFULLY.setData(
            repository.findById(id).map {
                if (it == null) SuperResponse.PRODUCT_NOT_FOUND
                else repository.save(it.setUpdatableValuesFromDto(dto))
            }
        )
    } else SuperResponse.PRODUCT_EXISTS

}
