package maxwayapi.model

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity(name = "delivery")
class Delivery() : BaseModel() {
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @ManyToOne(optional = false)
    var deliverer: User = User()

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    var order: Order = Order()
}
