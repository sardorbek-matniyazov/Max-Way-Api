package maxwayapi.dto

import maxwayapi.model.Product
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ProductDto(
    @field:NotBlank(message = "Product Name is required")
    val name: String = "",

    @field:NotNull(message = "Category is required for product")
    val categoryId: Long = 0L,

    @field:NotBlank(message = "Image Url is required")
    val imageUrl: String = "",

    @field:NotNull(message = "Product price is required for product")
    val price: Double? = null,

    @field:NotBlank(message = "Product description is required")
    val description: String = ""
) {
    fun toProductEntity(): Product {
        return Product(
            this.name,
            this.imageUrl,
            this.price,
            this.description
        )
    }
}
