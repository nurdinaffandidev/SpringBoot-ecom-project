package com.nurdinaffandidev.SpringBoot_ecom_project.configuration;

import com.nurdinaffandidev.SpringBoot_ecom_project.filter.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // inform spring don't go with default flow, go with flow mentioned here. commenting this will enable default security flow
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean // commenting this will enable default security flow
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // disable csrf
//        http.csrf(customizer -> customizer.disable());
//        // create login restriction by applying authentication. Authorize all requests (require authentication)
//        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//        // allow frames for H2 Console
//        http.headers(headers -> headers.frameOptions(frameOption -> frameOption.disable()));
//        // enable(optional) form login, this will return a view response ie. http format
//        //http.formLogin(Customizer.withDefaults());
//        // enable REST access, ie. return json response
//        http.httpBasic(Customizer.withDefaults());
//        // make http stateless to not worry about session id
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        // return the object of security filter chain
//        return http.build();

        // using builder method:
        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/user/register", "/user/login").permitAll() // bypass authentication for `/user/login` and `/user/register`
                        .anyRequest().authenticated())
                .headers(headers -> headers
                        .frameOptions(frameOption -> frameOption.disable())
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Same implementation as above in details w/o using lambda, using inner classes
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // disable csrf
//        Customizer<CsrfConfigurer<HttpSecurity>> customizerCsrf = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
//                httpSecurityCsrfConfigurer.disable();
//            }
//        };
//        http.csrf(customizerCsrf);
//
//        // create login restriction by applying authentication. Authorize all requests (require authentication)
//        Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> authorizationManagerRequestMatcherRegistryCustomizer = new Customizer<AuthorizeHttpRequestsConfigurer<org.springframework.security.config.annotation.web.builders.HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>() {
//            @Override
//            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizationManagerRequestMatcherRegistry) {
//                authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
//            }
//        };
//        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistryCustomizer);
//
//        // enable(optional) form login, this will return a view response ie. http format
////        Customizer<FormLoginConfigurer<HttpSecurity>> formLoginConfigurerCustomizer = new Customizer<FormLoginConfigurer<HttpSecurity>>() {
////            @Override
////            public void customize(FormLoginConfigurer<HttpSecurity> httpSecurityFormLoginConfigurer) {
////                // default config
////            }
////        };
////        http.formLogin(formLoginConfigurerCustomizer);
//
//        // enable REST access, ie. return json response
//        Customizer<HttpBasicConfigurer<HttpSecurity>> httpBasicConfigurerCustomizer = new Customizer<HttpBasicConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(HttpBasicConfigurer<HttpSecurity> httpSecurityHttpBasicConfigurer) {
//                // default config
//            }
//        };
//        http.httpBasic(httpBasicConfigurerCustomizer);
//
//        // make http stateless to not worry about session id
//        Customizer<SessionManagementConfigurer<HttpSecurity>> sessionManagementConfigurerCustomizer = new Customizer<SessionManagementConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(SessionManagementConfigurer<HttpSecurity> httpSecuritySessionManagementConfigurer) {
//                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//            }
//        };
//        http.sessionManagement(sessionManagementConfigurerCustomizer);
//
//        // return the object of security filter chain
//        return http.build();
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // implement data authentication provider: DAO Authentication Provider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // implement default password encoder
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // no hashing of password to required to verify plain-text password stored in database
        provider.setPasswordEncoder(passwordEncoder()); // convert password to bcrypt hash and verify correct password entered with hashed password in database
        // implement user details service to verify user
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    // authenticationManager talks to authenticationProvider
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
