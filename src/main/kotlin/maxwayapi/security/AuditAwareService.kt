package maxwayapi.security

import maxwayapi.model.User
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

class AuditAwareService: AuditorAware<User> {
    override fun getCurrentAuditor(): Optional<User> {
        val authentication = SecurityContextHolder.getContext().authentication
        if (
            authentication != null && authentication.isAuthenticated
        ) return Optional.of(authentication.principal as User)
        return Optional.of(User())
    }
}