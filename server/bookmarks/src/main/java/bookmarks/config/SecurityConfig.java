package bookmarks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import bookmarks.security.CustomAuthenticationSuccessHandler;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {
        @Autowired
        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

        @Bean
        public SecurityFilterChain configuration(HttpSecurity http) throws Exception {
                return http
                                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                                .cors(Customizer.withDefaults())
                                // Requests authorization
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers("/login").permitAll()
                                                .anyRequest().authenticated())
                                // Login page config
                                .formLogin(form -> form
                                                .loginPage("http://localhost:9000/auth/login")
                                                .loginProcessingUrl("/login")
                                                .successHandler(customAuthenticationSuccessHandler)
                                                .permitAll())

                                .httpBasic(Customizer.withDefaults())
                                .build();
        }
}
