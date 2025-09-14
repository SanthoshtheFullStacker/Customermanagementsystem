package com.example.Customermanagement.auth;

import com.example.Customermanagement.auth.Service.Userdetailsservice;
import com.fasterxml.jackson.databind.annotation.NoClass;
import org.hibernate.mapping.Collection;
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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  /* usage of this annotate method this config particulary spring security */
public class Securityconfig {

    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    public Userdetailsservice userdetailsservice;




    @Bean /* to mention below function should be called as a configuration when server starts */
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        /* to resolve the below error in return statement we are gonna disable some unneccessary */

        /* first one CSRF disable */

        httpSecurity.csrf(csrf -> csrf.disable());

        /* second step to http request authorization by that we can
        disable a authentication request to any api below code

        will return 403 forbidden cause of we are disabled request in this way it dont know from which way i should accept a request
         */

        httpSecurity.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/auth/login","/auth/register","/**").permitAll().anyRequest().authenticated());

        /* to make it work thorugh insomnia basic auth we are enabling a httpbasic */

        httpSecurity.httpBasic(Customizer.withDefaults());

        /* to enable form to get a USERNAME and PASSWORD */

        httpSecurity.formLogin(Customizer.withDefaults());

        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build(); /* it will disable everything provided by a spring security ( you will get a forbidden error )*/
    }

//    now it will only the users that we mentioned in security file we want to enable a
//    multiple users


//    Spring security cant recongnize a BcryptPasswordEncoder a type so we are using password encoder

//    @Bean
//    public static PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder(12);
//    }




//    @Bean
//    public UserDetailsService userDetailsService() {
//
//
//        UserDetails user1 = User.builder()
//                .username("uta")
//                .password(passwordEncoder().encode("u@123")) // Encode properly
//                .roles("USER")
//                .build();
//
//        System.out.println("Encoded password: " + user1.getPassword());
//
//        return new InMemoryUserDetailsManager(user1);
//    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userdetailsservice);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }











}
