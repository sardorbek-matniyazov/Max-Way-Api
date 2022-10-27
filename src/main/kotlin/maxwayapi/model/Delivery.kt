package maxwayapi.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity(name = "delivery")
class Delivery(
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE])
    var order: Order? = null,

    @JsonIgnoreProperties(*["hibernateLazyInitializer", "handler"])
    @OneToOne(
        optional = false,
        orphanRemoval = true,
        cascade = [CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST],
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    var address: Address? = null
) : BaseModel() {
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @ManyToOne
    var deliverer: User? = null
}
