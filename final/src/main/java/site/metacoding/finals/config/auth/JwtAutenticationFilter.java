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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.dto.LoginDto;

@Slf4j // 나중에 삭제
@RequiredArgsConstructor
public class JwtAutenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        System.out.println("진입");

        // 1. username,password 를 받아서
        try {
            // System.out.println(request.getInputStream().toString());
            ObjectMapper om = new ObjectMapper();
            LoginDto loginDto = om.readValue(request.getInputStream(), LoginDto.class);
            // System.out.println(loginDto.getUsername());

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(), loginDto.getPassword());

            // System.out.println("저장됨??????????");

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // System.out.println("------------실행됨-------------------");
            // PrincipalUser testuser = (PrincipalUser) authentication.getPrincipal();
            // System.out.println(testuser.getUsername());

            return authentication;
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    // // 인증 성공 후
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        System.out.println("인증완료");

        PrincipalUser principalUser = (PrincipalUser) authResult.getPrincipal();
        // 토큰 생성 + 해시 암호화
        String jwtToken = JWT.create()
                .withSubject("auth")
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60 * 60)))
                .withClaim("username", principalUser.getUsername())
                .withClaim("role", principalUser.getAuthorities().toString())
                .sign(Algorithm.HMAC256("SPRING_SECURITY_FORM_PASSWORD_KEY"));

        response.addHeader("Authorization", "Bearer " + jwtToken);
        super.successfulAuthentication(request, response, chain, authResult);
        System.out.println("로그인 완료");
    }

}