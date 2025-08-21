//package com.dep.ordermanagement.def;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.List;
//
//@Configuration
//@EnableMethodSecurity
//public class WebSecurityConfig {
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOriginPatterns(List.of("*")); // use patterns, not setAllowedOrigins
//        configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS"));
//        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setAllowCredentials(true); // now works fine with patterns
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> {}) // uses your CorsConfigurationSource bean
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**",
//                                "/v3/api-docs/swagger-config/**",
//                                "/api/v1/auth/**"
//                        ).permitAll()
//                        .anyRequest().permitAll()
//                );
//        return http.build();
//    }
//
////    @Bean
////    public WebSecurityCustomizer webSecurityCustomizer(){
////        return (web) ->  web.ignoring().requestMatchers(
////                //swagger API's
////                "/swagger-ui/**",
////                "/v3/api-docs/**",
////                "/v3/api-docs/swagger-config/**"
////                //application API's
////                ,"/api/v1/auth/**", "/**");
////    }
//}
