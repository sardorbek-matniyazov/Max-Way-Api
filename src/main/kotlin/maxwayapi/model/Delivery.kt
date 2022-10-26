package maxwayapi.model

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity(name = "delivery")
class Delivery(
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(optional = false, fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    var order: Order = Order()
) : BaseModel() {

    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @ManyToOne
    var deliverer: User = User()
}
