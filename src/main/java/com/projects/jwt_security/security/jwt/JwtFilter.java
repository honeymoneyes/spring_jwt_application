package com.projects.jwt_security.security.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.projects.jwt_security.security.services.PersonDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final PersonDetailsService personDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (token.isBlank()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid JWT token in Bearer header");

            } else {
                try {
                    String username = jwtUtils.verificationTokenAndRetrieve(token);

                    UserDetails userDetails = personDetailsService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails,
                                    userDetails.getPassword(),
                                    userDetails.getAuthorities());

                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                } catch (JWTVerificationException | UsernameNotFoundException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                            "Invalid JWT token");
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
