package site.metacoding.finals.config.auth;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.dto.LoginDto;

@RequiredArgsConstructor
public class JwtAutenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        System.out.println("진입");

        try {
            ObjectMapper om = new ObjectMapper();
            LoginDto loginDto = om.readValue(request.getInputStream(), LoginDto.class);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(), loginDto.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            return authentication;
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        System.out.println("인증완료");

        PrincipalUser principalUser = (PrincipalUser) authResult.getPrincipal();
        String jwtToken = JWT.create()
                .withSubject("auth")
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60 * 60)))
                .withClaim("username", principalUser.getUsername())
                .withClaim("role", principalUser.getAuthorities().toString())
                .sign(Algorithm.HMAC256("SPRING_SECURITY_FORM_PASSWORD_KEY"));

        response.addHeader("Authorization", "Bearer " + jwtToken);
        System.out.println("로그인 완료");

    }

}
