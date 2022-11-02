package maxwayapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "order_item")
class ProductItem(
    @JsonIgnoreProperties(*["hibernateLazyInitializer", "handler"])
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @ManyToOne(
        fetch = FetchType.LAZY,
        optional = false
    )
    val product: Product? = null,

    @Column(nullable = false, name = "item_quantity")
    var quantity: Int = 0
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    var createdDate: Timestamp = Timestamp.valueOf(LocalDateTime.now())

    @JsonIgnore
    @UpdateTimestamp
    var updatedDate: Timestamp = Timestamp.valueOf(LocalDateTime.now())
}
