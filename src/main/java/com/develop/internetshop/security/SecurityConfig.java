package com.develop.internetshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.develop.internetshop.entities.User.UserType;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // анонимный пользователь
        http
            .csrf(Customizer.withDefaults())
            .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers("/", "/register", "/category", "/product/**").permitAll()
                    .requestMatchers("/css/**").permitAll()
                    .requestMatchers("/scss/**").permitAll()
                    .requestMatchers("/js/**").permitAll()
                    .requestMatchers("/vendors/**").permitAll()
                    .requestMatchers("/img/**").permitAll()
                    .requestMatchers("/cart").hasRole(UserType.USER.name())
                    .requestMatchers("/confirmation").hasRole(UserType.USER.name())// .anyRequest().authenticated()
            ).formLogin(
                    form -> form
                            .loginPage("/login")
                            .loginProcessingUrl("/login")
                            .defaultSuccessUrl("/")
                            .permitAll())
            .logout(
                    logout -> logout
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .permitAll());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
