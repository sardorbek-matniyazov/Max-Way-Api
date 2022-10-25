package maxwayapi.repository

import maxwayapi.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {
    fun existsByName(name: String?): Boolean
    fun existsByNameAndIdIsNot(name: String, id: Long): Boolean

}
