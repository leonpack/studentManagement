package dev.nathan.studentmanagementv1.config;

import dev.nathan.studentmanagementv1.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
            configurer
                    .requestMatchers(HttpMethod.GET, "/api/v1/users").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/v1/users").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/v1/subjects").hasAnyAuthority("ADMIN", "STUDENT", "TEACHER")
                    .requestMatchers(HttpMethod.GET, "/api/v1/subjects/**").hasAnyAuthority("ADMIN", "STUDENT", "TEACHER")
                    .requestMatchers(HttpMethod.POST, "/api/v1/subject").hasAuthority("TEACHER")
        );

        //use http basic authentication
        http.httpBasic(Customizer.withDefaults());

        //also disable csrf
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(encoder());
        return auth;
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
