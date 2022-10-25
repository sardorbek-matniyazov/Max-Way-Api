package maxwayapi.repository

import maxwayapi.model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {
    fun existsByName(name: String): Boolean
}