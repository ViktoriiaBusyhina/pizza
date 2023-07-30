package com.example.pizza.security;

import com.example.pizza.enam.Roles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static com.example.pizza.enam.Roles.ADMIN;

/**
 * Configuration class for web security using Spring Security.
 * This class defines security settings and access control for various endpoints.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    /**
     * Creates a bean for the JdbcUserDetailsManager using the provided DataSource.
     *
     * @param dataSource The DataSource used for user details management.
     * @return JdbcUserDetailsManager instance for user authentication and management.
     */
    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
    /**
     * Creates a bean for the BCryptPasswordEncoder for password encoding.
     *
     * @return BCryptPasswordEncoder instance for password encoding and decoding.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     * Configures the security filter chain for the HTTP requests.
     *
     * @param http The HttpSecurity instance to be customized.
     * @return SecurityFilterChain instance with the configured access rules.
     * @throws Exception if an error occurs during the configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/v3/api-docs/**").permitAll();
                    auth.requestMatchers("/swagger-ui/**").permitAll();
                    auth.requestMatchers("/swagger-ui.html").permitAll();

                    auth.requestMatchers("/registration").permitAll();

                    auth.requestMatchers("/new-order").hasRole(Roles.USER.name());
                    auth.requestMatchers("/order/checkOrderStatus/{id}").hasRole(Roles.USER.name());
                    auth.requestMatchers("/pizza/find/all").hasRole(Roles.USER.name());

                    auth.requestMatchers("/**").hasRole(ADMIN.name());

                })
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();

    }
}