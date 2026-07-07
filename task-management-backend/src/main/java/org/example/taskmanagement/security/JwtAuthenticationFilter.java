package org.example.taskmanagement.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter
        extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        if(request.getServletPath().startsWith("/auth/")){
            filterChain.doFilter(request, response);
        }

        String authHeader =
                request.getHeader("Authorization");

        System.out.println(
                "Authorization Header: "
                        + authHeader
        );
        if (authHeader != null &&
                authHeader.startsWith("Bearer ")) {

            String token =
                    authHeader.substring(7);

            String email =
                    jwtService.extractUsername(token);

            System.out.println(
                    "Email From JWT: "
                            + email
            );
            boolean valid =
                    jwtService.isTokenValid(
                            token,
                            email
                    );
            if (valid) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                null
                        );

                SecurityContextHolder.getContext()
                        .setAuthentication(authToken);

                System.out.println(
                        "User Authenticated: "
                                + email
                );
            }
            System.out.println(
                    "Token Valid: "
                            + valid
            );
        }
        filterChain.doFilter(
                request,
                response
        );
    }
        private final JwtService jwtService;
        public JwtAuthenticationFilter(JwtService jwtService){
            this.jwtService=jwtService;
        }
}