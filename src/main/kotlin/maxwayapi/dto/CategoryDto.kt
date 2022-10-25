package maxwayapi.dto

import maxwayapi.model.Category
import javax.validation.constraints.NotBlank

data class CategoryDto(
    @field:NotBlank(message = "category name is required")
    var categoryName: String = "",

    @field:NotBlank(message = "category information is required")
    val categoryInformation: String = ""
) {
    fun toCategoryEntity() = Category(categoryName, categoryInformation)
}
