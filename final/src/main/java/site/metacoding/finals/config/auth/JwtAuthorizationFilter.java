package site.metacoding.finals.config.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.domain.user.User;
import site.metacoding.finals.domain.user.UserRepository;

// @Profile("dev")
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String jwtHeader = request.getHeader("Authorization");

        if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");

        String username = JWT.require(Algorithm.HMAC256("SPRING_SECURITY_FORM_PASSWORD_KEY"))
                .build()
                .verify(jwtToken)
                .getClaim("username")
                .asString();

        if (username != null) {
            User userEntity = userRepository.findByUsername(username);

            PrincipalUser principalDetails = new PrincipalUser(userEntity, null);

            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null,
                    principalDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        chain.doFilter(request, response);

        // https://velog.io/@max9106/OAuth
    }
}