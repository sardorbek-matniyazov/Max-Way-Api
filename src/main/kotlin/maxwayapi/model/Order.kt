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
    var comment: String = "This comment is EMPTY",

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: OrderType = OrderType.SIMPLE

) : BaseModel() {

    init {
        NUMBER_GENERATOR = (Math.random()*1000L).toLong()
    }

    @Column(name = "order_number", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var number: Long = NUMBER_GENERATOR

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.ONE

    companion object {
        private var NUMBER_GENERATOR = 120L
    }
}