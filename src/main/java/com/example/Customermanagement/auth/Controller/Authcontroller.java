package com.example.Customermanagement.auth.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Authcontroller {

    /*
    *   Defaultly CSRF will permit all the GET method automatically without any verfification
    *
    *   but for post method it will validate with CSRF token to permit the API
    *
   */


    @PostMapping("/demopost")
    public String demopost(){
        return "demo post method";
    }


    @GetMapping("/getcsrftoken")
    public CsrfToken getcsrftoken(HttpServletRequest httpServletRequest){
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }





}
