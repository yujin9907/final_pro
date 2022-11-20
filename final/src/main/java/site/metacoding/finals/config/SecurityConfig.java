package site.metacoding.finals.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import site.metacoding.finals.config.auth.JwtAutenticationFilter;
import site.metacoding.finals.config.auth.JwtAuthorizationFilter;
import site.metacoding.finals.config.oauth.Oauth2UserService;
import site.metacoding.finals.domain.user.User;
import site.metacoding.finals.domain.user.UserRepository;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CorsConfig corsConfig;
    @Autowired
    private UserRepository userRepository;
    // @Autowired
    // private User user;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin()
                // .successHandler(ㅠㅠ)
                .disable()
                .httpBasic().disable()
                .apply(new MyCustomDsl());
        http
                .authorizeRequests()
                .antMatchers("/auth/**").authenticated()
                .antMatchers("/auth/user/**").access("hasRole('USER')")
                .antMatchers("/auth/shop/**").access("hasRole('SHOP')")
                .anyRequest().permitAll();
        http.logout()
                .logoutSuccessUrl("/");
        http.oauth2Login()
                .loginPage("/loginForm")
                .userInfoEndpoint() // 로그인 성공 후 사용자정보를 가져온다
                .userService(new Oauth2UserService(passwordEncoder(), userRepository)); // 사용자정보를 처리할 때 사용한다
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
