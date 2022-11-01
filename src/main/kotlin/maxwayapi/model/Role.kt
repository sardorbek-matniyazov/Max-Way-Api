package maxwayapi.model

import maxwayapi.model.enums.RoleName
import org.springframework.security.core.GrantedAuthority
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity(name = "role")
class Role(
    @Column(nullable = false, name = "role_name", unique = true)
    @Enumerated(EnumType.STRING)
    var roleName: RoleName = RoleName.USER
): GrantedAuthority, BaseModel() {

    override fun getAuthority() = roleName.name

}
