package maxwayapi.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity(name = "users")
class User() : BaseModel(), java.io.Serializable {

    constructor(fullName: String, phoneNumber: String, address: Address) : this() {
        this.fullName = fullName
        this.phoneNumber = phoneNumber
        this.address = address
    }

    @Column(name = "usr_full_name", nullable = false)
    var fullName: String = ""

    @Column(name = "usr_phone_number", nullable = false, unique = true)
    var phoneNumber: String = ""

    @JsonIgnoreProperties(*["hibernateLazyInitializer", "handler"])
    @OneToOne(
        optional = false,
        orphanRemoval = true,
        cascade = [CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST],
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    var address: Address? = null

    @Column(name = "usr_active")
    var active: Boolean = true

    fun setFullNameAndPhoneNumber(fullName: String, phoneNumber: String): User {
        this.fullName = fullName
        this.phoneNumber = phoneNumber
        return this
    }
}