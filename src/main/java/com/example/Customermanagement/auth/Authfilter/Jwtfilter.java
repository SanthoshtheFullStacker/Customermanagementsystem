package com.example.Customermanagement.auth.Authfilter;

import com.example.Customermanagement.auth.Model.Userdetails;
import com.example.Customermanagement.auth.Service.Userdetailsservice;
import com.example.Customermanagement.auth.Serviceimpl.JWTserviceimpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class Jwtfilter extends OncePerRequestFilter {

    @Autowired
    private JWTserviceimpl jwTserviceimpl;

    @Autowired
    private Userdetailsservice userdetailsservice;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        String token = null;
        String username = null;

        if(authorization != null && SecurityContextHolder.getContext().getAuthentication() == null){

            token = authorization.substring(7);

            username = jwTserviceimpl.extractUsername(token);

            if(!jwTserviceimpl.isTokenExpired(token)){

                Userdetails userdetails = (Userdetails) userdetailsservice.loadUserByUsername(username);


                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userdetails, null, userdetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        }

        filterChain.doFilter(request,response);

    }


}
