package maxwayapi.repository

import maxwayapi.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductRepository: JpaRepository<Product, Long> {
    fun existsByName(name: String?): Boolean
    fun existsByNameAndIdIsNot(name: String, id: Long): Boolean
}
