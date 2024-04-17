package com.example.projektgruptest.config.security;

import com.example.projektgruptest.auth.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@Configuration
@RequiredArgsConstructor
public class SpringSecConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(customizer -> {
                    customizer
                            .requestMatchers("/login").permitAll()
                            .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                            .requestMatchers("/pracownikDodaj").hasAuthority("KADRA")
                            .requestMatchers("/uzytkownicy").hasAuthority("KADRA")
                            .requestMatchers("/uzytkownikEdytuj/{id}").hasAuthority("KADRA")
                            .requestMatchers("/uzytkownikUsun/{id}").hasAuthority("KADRA")
                            .requestMatchers("/pracownikEdytuj/{id}").hasAuthority("KADRA")
                            .requestMatchers("/Wnioski").hasAuthority("PRACOWNIK")
                            .requestMatchers("/Wniosek").hasAuthority("KADRA")
                            .requestMatchers("/Wniosek/{id}").hasAuthority("KADRA")
                            .anyRequest().authenticated();
                })
                .sessionManagement(customizer -> {
                    customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .csrf(customizer -> {
                    customizer.disable();
                })
                .cors(customizer -> {
                    customizer.configure(http);
                })
                .addFilterAfter(jwtAuthorizationFilter, ExceptionTranslationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder noOpPasswordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(noOpPasswordEncoder);
        return authenticationManagerBuilder.build();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
