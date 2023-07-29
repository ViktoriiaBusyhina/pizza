package com.example.pizza.security;

import com.example.pizza.enam.Roles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static com.example.pizza.enam.Roles.ADMIN;
import static com.example.pizza.enam.Roles.USER;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //@Bean
    public InMemoryUserDetailsManager userDetailsManager() {



        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles(String.valueOf(USER))
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles(String.valueOf(ADMIN))
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/registration").permitAll();
                    auth.requestMatchers("/new-cafe").hasRole(ADMIN.name());
                    auth.requestMatchers("/cafe/find/all").hasRole(ADMIN.name());
                    auth.requestMatchers("/cafe/find/{id}").hasRole(ADMIN.name());
                    auth.requestMatchers("/cafe/update/{id}").hasRole(ADMIN.name());
                    auth.requestMatchers("/cafe/delete/{id}").hasRole(ADMIN.name());
                    auth.requestMatchers("/new-customer").hasRole(ADMIN.name());
                    auth.requestMatchers("/customer/find/all").hasRole(ADMIN.name());
                    auth.requestMatchers("/customer/find/{id}").hasRole(ADMIN.name());
                    auth.requestMatchers("/customer/update/{id}").hasRole(ADMIN.name());
                    auth.requestMatchers("/customer/delete/{id}").hasRole(ADMIN.name());
                    auth.requestMatchers("/new-order").hasRole(ADMIN.name());
                    auth.requestMatchers("/order/find/{id}").hasRole(ADMIN.name());
                    auth.requestMatchers("/order/find/all").hasRole(ADMIN.name());
                    auth.requestMatchers("/order/update/{id}").hasRole(ADMIN.name());
                    auth.requestMatchers("/order/checkOrderStatus/{id}").hasRole(ADMIN.name());
                    auth.requestMatchers("/order/delete/{id}").hasRole(ADMIN.name());
                    auth.requestMatchers("/new-pizza").hasRole(ADMIN.name());
                    auth.requestMatchers("/pizza/find/all").hasRole(ADMIN.name());
                    auth.requestMatchers("/pizza/find/{id}").hasRole(ADMIN.name());
                    auth.requestMatchers("/pizza/update/{id}").hasRole(ADMIN.name());
                    auth.requestMatchers("/pizza/delete/{id}").hasRole(ADMIN.name());
                    auth.requestMatchers("/pizza/block_pizza/{id}").hasRole(ADMIN.name());
                    auth.requestMatchers("/new-order").hasRole(Roles.USER.name());
                    auth.requestMatchers("/order/checkOrderStatus/{id}").hasRole(Roles.USER.name());
                    auth.requestMatchers("/pizza/find/all").hasRole(Roles.USER.name());

                })
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}