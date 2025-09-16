package com.eazybytes.eazystore.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class EazyStoreSecurityConfig {
  private final List<String> publicPaths;
  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.headers(headers ->
            headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
        .cors(cors -> cors.configurationSource(new CorsConfigFilter()))
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests((request) -> {
          publicPaths.forEach(path -> request.requestMatchers(path).permitAll());
          request.anyRequest().authenticated();
    });
    http.formLogin(withDefaults());
    http.httpBasic(withDefaults());
    return http.build();
  }

  // Creating an InMemory User Information
  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
    UserDetails user1 = User.builder().username("madan").password("$2a$12$0KQ0XdjPosc4mD8Hz.0O1ucQRH.vPdo8QUVOFNBydirMubg0u1FLO").roles("USER").build();
    UserDetails user2 = User.builder().username("admin").password("$2a$12$OcvHsWcqlf6XKToDBHVEfeooaf4LYz/ps.OPZK6dyXU4aoIDRlD/C").roles("USER", "ADMIN").build();

    return new InMemoryUserDetailsManager(user1, user2);
  };

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider){
    ProviderManager providerManager = new ProviderManager(authenticationProvider);
    return providerManager;
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CompromisedPasswordChecker compromisedPasswordChecker(){
    return new HaveIBeenPwnedRestApiPasswordChecker();
  };

}
