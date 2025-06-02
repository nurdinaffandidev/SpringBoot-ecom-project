package com.nurdinaffandidev.SpringBoot_ecom_project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // inform spring don't go with default flow, go with flow mentioned here. commenting this will enable default security flow
public class SecurityConfiguration {

    @Bean // commenting this will enable default security flow
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // disable csrf
//        http.csrf(customizer -> customizer.disable());
//        // create login restriction by applying authentication. Authorize all requests (require authentication)
//        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//        // enable(optional) form login, this will return a view response ie. http format
//        //http.formLogin(Customizer.withDefaults());
//        // enable REST access, ie. return json response
//        http.httpBasic(Customizer.withDefaults());
//        // make http stateless to not worry about session id
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        // return the object of security filter chain
//        return http.build();

        // using builder method:
        return http.csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
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
}
