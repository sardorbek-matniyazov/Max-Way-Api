package maxwayapi.security

import maxwayapi.model.User
import maxwayapi.repository.UserRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SecurityFilter(
    private var userRepository: UserRepository,
    private val jwtMatcher: JwtMatcher
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        var token = request.getHeader("Authorization")
        if (token != null && token.startsWith("Bearer")) {
            token = token.substring(7)
            val username: String? = jwtMatcher.parseToken(token)
            val optionalUser: Optional<User?> = userRepository.findByPhoneNumber(username!!)
            if (optionalUser.isPresent) {
                val user: User = optionalUser.get()
                if (token.equals(user.token)) {
                    val authenticationToken = UsernamePasswordAuthenticationToken(user, null, user.authorities)
                    SecurityContextHolder.getContext().authentication = authenticationToken
                }
            }
        }
        filterChain.doFilter(request, response)
    }
}