package maxwayapi.repository

import maxwayapi.model.ProductItem
import org.springframework.data.jpa.repository.JpaRepository

interface ProductItemRepository: JpaRepository<ProductItem, Long> {

}
