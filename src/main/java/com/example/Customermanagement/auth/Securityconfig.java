package com.example.Customermanagement.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  /* usage of this annotate method this config particulary spring security */
public class Securityconfig {

    @Bean /* to mention below function should be called as a configuration when server starts */
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        /* to resolve the below error in return statement we are gonna disable some unneccessary */

        /* first one CSRF disable */

        httpSecurity.csrf(csrf -> csrf.disable());

        /* second step to http request authorization by that we can
        disable a authentication request to any api below code

        will return 403 forbidden cause of we are disabled request in this way it dont know from which way i should accept a request
         */

        httpSecurity.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());

        /* to make it work thorugh insomnia basic auth we are enabling a httpbasic */

        httpSecurity.httpBasic(Customizer.withDefaults());

        /* to enable form to get a USERNAME and PASSWORD */

        httpSecurity.formLogin(Customizer.withDefaults());

        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build(); /* it will disable everything provided by a spring security ( you will get a forbidden error )*/
    }




}
