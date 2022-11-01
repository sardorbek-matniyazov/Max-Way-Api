package maxwayapi.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import maxwayapi.model.enums.OrderAction
import maxwayapi.model.enums.OrderStatus
import maxwayapi.model.enums.OrderType
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity(name = "orders")
class Order(
    @JsonIgnoreProperties(*["hibernateLazyInitializer", "handler"])
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE])
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    var products: MutableList<ProductItem>? = null,

    @Column(name = "order_price", nullable = false)
    val allPrice: Double = 0.0,

    @Column(name = "order_comment", nullable = false)
    var comment: String = "This comment is EMPTY",

    @Column(nullable = false, name = "order_name")
    @Enumerated(EnumType.STRING)
    var type: OrderType = OrderType.SIMPLE

) : BaseModel() {

    init {
        NUMBER_GENERATOR = (Math.random()*1000L).toLong()
    }

    @Column(nullable = false, name = "order_action")
    @Enumerated(EnumType.STRING)
    var orderAction: OrderAction = OrderAction.ACTIVE

    @Column(name = "order_number", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var number: Long = NUMBER_GENERATOR

    @Column(nullable = false, name = "order_status")
    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.ORDERED

    companion object {
        private var NUMBER_GENERATOR = 120L
    }
}