package art.showbility.auth.app.service

import art.showbility.user.domain.UserId
import io.github.oshai.kotlinlogging.KotlinLogging
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.Date

private val logger = KotlinLogging.logger {}

@Component
class JwtProvider(
    jwtProperties: JwtProperties,
) {
    private val key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.secret))
    private val parser = Jwts.parser().verifyWith(key).build()

    fun generateToken(
        id: UserId,
        validitySeconds: Long,
    ): String {
        val expiryDate = Date.from(Instant.now().plusSeconds(validitySeconds))
        return Jwts
            .builder()
            .subject(id.value.toString())
            .expiration(expiryDate)
            .signWith(key, Jwts.SIG.HS256)
            .compact()
    }

    fun parseToken(token: String): Jws<Claims>? =
        try {
            parser.parseSignedClaims(token)
        } catch (e: JwtException) {
            logger.error(e) { "Failed to validate token" }
            null
        }
}
