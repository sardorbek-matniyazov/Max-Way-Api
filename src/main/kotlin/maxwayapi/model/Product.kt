package maxwayapi.model

import maxwayapi.dto.ProductDto
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity(name = "product")
class Product() : BaseModel() {
    fun setUpdatableValuesFromDto(dto: ProductDto): Product {
        this.name = dto.name
        this.description = dto.description
        this.imageUrl = dto.imageUrl
        this.price = dto.price
        return this
    }

    @Column(name = "pr_name", nullable = false, unique = true)
    var name: String = ""

    @ManyToOne(optional = false)
    var category: Category? = null

    @Column(name = "pr_image_url", nullable = false)
    var imageUrl: String = ""

    @Column(name = "pr_price", nullable = false)
    var price: Double? = 0.0

    @Column(name = "pr_desc", nullable = false)
    var description: String = ""

    constructor(name: String, imageUrl: String, price: Double?, description: String): this() {
        this.name = name
        this.description = description
        this.imageUrl = imageUrl
        this.price = price
    }
    fun setCategory(category: Category): Product {
        this.category = category
        return this
    }
}