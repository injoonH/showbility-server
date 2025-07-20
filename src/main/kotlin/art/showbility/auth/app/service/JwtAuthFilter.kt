package art.showbility.auth.app.service

import art.showbility.auth.domain.AUTH_TOKEN_HEADER
import art.showbility.auth.domain.AuthPrincipal
import art.showbility.user.domain.UserId
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthFilter(
    private val jwtProvider: JwtProvider,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token = request.getHeader(AUTH_TOKEN_HEADER)

        if (!token.isNullOrBlank()) {
            val signature = jwtProvider.parseToken(token)
            if (signature == null) {
                setUnauthorized(response)
                return
            }

            val userId = UserId(signature.payload.subject.toULong())
            val principal = AuthPrincipal(userId)
            val authentication = UsernamePasswordAuthenticationToken(principal, null, emptyList())
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    private fun setUnauthorized(response: HttpServletResponse) {
        response.status = 401
    }
}
