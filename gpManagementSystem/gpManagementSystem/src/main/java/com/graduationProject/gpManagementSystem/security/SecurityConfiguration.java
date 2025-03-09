package com.graduationProject.gpManagementSystem.security;



// import jakarta.annotation.Nonnull;
// import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import static org.springframework.http.HttpMethod.DELETE;
// import static org.springframework.http.HttpMethod.GET;
// import static org.springframework.http.HttpMethod.POST;
// import static org.springframework.http.HttpMethod.PUT;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// import java.net.http.HttpRequest;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
// @EnableMethodSecurity
// @EnableMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfiguration  {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private  final  AuthenticationProvider authenticationProvider;



    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
    http
                  .csrf((e)->e.disable())
                  .authorizeHttpRequests( authorizeRequest ->
                          authorizeRequest
                                  .requestMatchers("api/v1/auth/**").permitAll()
                                  .requestMatchers("/ws/**" , "/").permitAll() // Allow WebSocket endpoints
                                  .requestMatchers("/topic/**", "/app/**").permitAll() // Allow STOMP endpoints

                                  .anyRequest()
                                  .authenticated()

                                 )
                                 .csrf(csrf -> csrf.disable()) // Disable CSRF for WebSocket
                                 .headers(headers -> headers
                                 .frameOptions(frameOptions -> frameOptions.disable()) // Disable X-Frame-Options
                                 .cacheControl(cache -> cache.disable()) // Avoid caching security headers
                             )

                 .sessionManagement((session) -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                       )


                  .authenticationProvider(authenticationProvider)
                  .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class);




return http.build();



}

@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .requestMatchers(
                new AntPathRequestMatcher("/"),
                new AntPathRequestMatcher("/index.html"),
                new AntPathRequestMatcher("/js/main.js"),
                new AntPathRequestMatcher("/css/main.css"),
                new AntPathRequestMatcher("/ws/**")
            );
    }


}