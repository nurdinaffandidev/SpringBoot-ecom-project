package com.nurdinaffandidev.SpringBoot_ecom_project.filter;

import com.nurdinaffandidev.SpringBoot_ecom_project.user.service.EcomUserDetailsService;
import com.nurdinaffandidev.SpringBoot_ecom_project.user.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Client side pass following:
        // Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJudXJkaW5fdXNlciIsImlhdCI6MTc0ODkzNTY4NCwiZXhwIjoxNzQ4OTM2MDQ0fQ.M12lCqCWB87_iTbGUV65o5OdvMa0JIAvGBcYeqa0Fzs

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = applicationContext.getBean(EcomUserDetailsService.class).loadUserByUsername(username);

            if(jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =  new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
