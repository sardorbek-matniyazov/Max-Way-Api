package maxwayapi.model

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToOne

@Entity(name = "address")
class Address(
    @Column(name = "add_lon", nullable = false) var lon: Double,
    @Column(name = "add_lat", nullable = false) var lat: Double
) : BaseModel() {
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private val user: User? = null
}