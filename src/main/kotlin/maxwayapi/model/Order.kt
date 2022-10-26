package maxwayapi.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import maxwayapi.model.enums.OrderStatus
import maxwayapi.model.enums.OrderType
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.util.Random
import javax.persistence.*

@Entity(name = "orders")
class Order(

    @JsonIgnoreProperties(*["hibernateLazyInitializer", "handler"])
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @ManyToOne(optional = false)
    var user: User = User(),

    @JsonIgnoreProperties(*["hibernateLazyInitializer", "handler"])
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE])
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    var products: MutableList<ProductItem>? = null,

    @Column(name = "order_price", nullable = false)
    val allPrice: Double = 0.0,

    @Column(name = "order_comment", nullable = false)
    var comment: String = "This comment is EMPTY"
) : BaseModel() {

    init {
        NUMBER_GENERATOR ++
    }

    @Column(name = "order_number", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var number: Long = NUMBER_GENERATOR

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: OrderType = OrderType.SIMPLE

    @JsonIgnoreProperties(*["hibernateLazyInitializer", "handler"])
    @OneToOne(
        mappedBy = "order",
        orphanRemoval = true,
        fetch = FetchType.EAGER,
        cascade = [CascadeType.PERSIST, CascadeType.REMOVE]
    )
    @JoinTable(
        name = "delivery",
        joinColumns = [JoinColumn(name = "order_id", referencedColumnName = "id")]
    )
    var delivery: Delivery? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.ONE

    constructor(
        user: User,
        products: MutableList<ProductItem>?,
        allPrice: Double,
        comment: String,
        delivery: Delivery
    ): this(user, products, allPrice, comment) {
        this.type = OrderType.DELIVERY
        this.delivery = delivery
    }
    companion object {
        private var NUMBER_GENERATOR = 100L
    }
}