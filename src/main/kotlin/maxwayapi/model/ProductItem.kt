package maxwayapi.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity(name = "order_item")
class ProductItem(

    @JsonIgnoreProperties(*["hibernateLazyInitializer", "handler"])
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @ManyToOne(
        fetch = FetchType.LAZY,
        optional = false
    )
    val product: Product? = null,

    var quantity: Int = 0
) : BaseModel()
