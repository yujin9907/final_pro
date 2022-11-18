package site.metacoding.finals.config;

import javax.servlet.Filter;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final CorsConfig corsConfig;
    private final UserRepository userRepository;
    // JWT 기반 로그인 시큐리티 설정, 주석은 폼 로그인 기반

    @Bean
    // @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable() // 로그인 관련 설정
                .httpBasic().disable()
                .apply(new MyCustomDsl())
                .and()
                .authorizeRequests()
                // url 별 권한설정
                .antMatchers("/auth/**").authenticated()
                .antMatchers("/auth/user/**").access("hasRole('USER')")
                .antMatchers("/pro/**").access("hasRole('SHOP')")
                .anyRequest().permitAll();

        return http.build();
    }

    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilter(corsConfig.corsFilter())
                    .addFilter(new JwtAutenticationFilter(authenticationManager))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
        }
    }

}
