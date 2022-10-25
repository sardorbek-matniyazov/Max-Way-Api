package maxwayapi.model

import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "category")
class Category(name: String, information: String) : BaseModel() {
    @Column(name = "ct_name", nullable = false, unique = true)
    var name: String? = name

    @Column(name = "ct_info")
    var information: String? = information
}