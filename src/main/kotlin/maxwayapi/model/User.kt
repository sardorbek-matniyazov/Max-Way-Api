package maxwayapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*

@Entity(name = "users")
class User() : BaseModel(), java.io.Serializable, UserDetails {

    constructor(fullName: String, phoneNumber: String, token: String, role: Role) : this() {
        this.fullName = fullName
        this.phoneNumber = phoneNumber
        this.role = role
        this.token = token
    }

    constructor(fullName: String, phoneNumber: String) : this() {
        this.fullName = fullName
        this.phoneNumber = phoneNumber
        this.role = role
    }

    @Column(name = "usr_full_name", nullable = false)
    var fullName: String = ""

    @Column(name = "usr_phone_number", nullable = false, unique = true)
    var phoneNumber: String = ""

    @JsonIgnoreProperties(*["hibernateLazyInitializer", "handler"])
    @OneToOne(
        orphanRemoval = true,
        cascade = [CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST],
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    var address: Address? = null

    @Column(name = "usr_active")
    var active: Boolean = true

    @ManyToOne(optional = false)
    var role: Role? = null

    @JsonIgnore
    @Column(nullable = false, name = "usr_password")
    private var password: String = "This is user client"

    @JsonIgnore
    @Column(nullable = false, name = "usr_current_token")
    var token: String = ""

    fun setFullNameAndPhoneNumber(fullName: String, phoneNumber: String): User {
        this.fullName = fullName
        this.phoneNumber = phoneNumber
        return this
    }

    @JsonIgnore
    override fun getPassword() = this.password

    override fun getAuthorities(): MutableCollection<Role> = Collections.singletonList(role)

    override fun getUsername() = this.phoneNumber

    @JsonIgnore
    override fun isAccountNonExpired() = true

    @JsonIgnore
    override fun isAccountNonLocked() = true

    @JsonIgnore
    override fun isCredentialsNonExpired() = true

    @JsonIgnore
    override fun isEnabled() = this.active
}