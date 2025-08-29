package com.eazybytes.eazystore.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class EazyStoreSecurityConfig {
  private final List<String> publicPaths;
  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.cors(cors -> cors.configurationSource(new CorsConfigFilter()))
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
  public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    ProviderManager providerManager = new ProviderManager(daoAuthenticationProvider);
    return providerManager;
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

}
