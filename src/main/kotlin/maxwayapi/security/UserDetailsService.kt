package maxwayapi.security

import maxwayapi.model.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsService: org.springframework.security.core.userdetails.UserDetailsService {
    @Throws(UsernameNotFoundException::class)

    override fun loadUserByUsername(username: String?): UserDetails? = User()

}