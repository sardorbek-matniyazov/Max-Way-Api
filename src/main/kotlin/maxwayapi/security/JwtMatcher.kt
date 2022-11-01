package maxwayapi.security

import io.jsonwebtoken.Jwts
import maxwayapi.utils.Constants
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtMatcher {
    fun generateToken(username: String?): String? {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + Constants.EXPIRE_DATE))
            .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, Constants.SECRET_KEY)
            .compact()
    }

    fun parseToken(token: String?): String? {
        try {
            return Jwts.parser()
                .setSigningKey(Constants.SECRET_KEY)
                .parseClaimsJws(token)
                .body
                .subject
        } catch (e: Exception) {
            println("this token is expired")
        }
        return "unDone"
    }
}