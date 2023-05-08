package bookmarks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;

import bookmarks.security.CustomAuthenticationSuccessHandler;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {
        @Autowired
        CsrfConfig csrfConfig;
        @Autowired
        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

        @Bean
        public SecurityFilterChain configuration(HttpSecurity http) throws Exception {
                return http
                                .csrf(csrf -> csrf
                                                .csrfTokenRepository(csrfConfig.csrfTokenRepository())
                                                .csrfTokenRequestHandler(csrfConfig.csrfTokenRequestHandler()))
                                .cors(Customizer.withDefaults())
                                // Requests authorization
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers("/login").permitAll()
                                                .requestMatchers("/csrf").permitAll()
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
