package site.metacoding.finals.config;

import javax.servlet.Filter;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.config.auth.JwtAutenticationFilter;
import site.metacoding.finals.config.auth.JwtAuthorizationFilter;
import site.metacoding.finals.domain.user.UserRepository;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final CorsConfig corsConfig;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    // JWT 기반 로그인 시큐리티 설정, 주석은 폼 로그인 기반

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsConfig.corsFilter()) // 세션을 사용하지 않음
                .addFilter(new JwtAutenticationFilter(authenticationManager))
                .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository))
                .authorizeRequests()
                // url 별 권한설정
                .antMatchers("/auth/**").authenticated()
                .antMatchers("/user/**").access("hasRole('USER')")
                .antMatchers("/pro/**").access("hasRole('SHOP')")
                .anyRequest().permitAll()
                // login 관련 설정
                .and()
                .formLogin().disable() // 사용하지 않음 -> 필터로 로그인 처리함
                .httpBasic().disable();
        // .loginPage("/경로") // 경로로 이동
        // .usernameParameter("username2") // 이렇게 바꿔줘야 동작함
        // .loginProcessingUrl("/login") // "경로" 요청이 들어오면 시큐리티가 대신 처리함
        // .defaultSuccessUrl("/") // 로그인이 완료되면
        // .failureForwardUrl("/fail") // 실패하면 줄 거
        // .and()
        // .logout()
        // .logoutUrl("/logout"); // 로그아웃 url 설정, 안하면 디폴트로 logout인가

        return http.build();
    }

}
