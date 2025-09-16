package com.example.Customermanagement.auth.Controller;

import com.example.Customermanagement.DTO.Request.Usermodelrequest;
import com.example.Customermanagement.DTO.Response.ErrorresponseDTO;
import com.example.Customermanagement.auth.Service.Authservice;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/auth")
public class Authcontroller {

    public static final Logger logger = LoggerFactory.getLogger(Authcontroller.class);

    /*
    *   Defaultly CSRF will permit all the GET method automatically without any verfification
    *
    *   but for post method it will validate with CSRF token to permit the API
    *
   */
    @Autowired
    private Authservice authservice;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/demopost")
    public String demopost(){
        return "demo post method";
    }


    @GetMapping("/getcsrftoken")
    public CsrfToken getcsrftoken(HttpServletRequest httpServletRequest){
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }


    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Usermodelrequest usermodelrequest,HttpServletRequest request){
        try{



            return authservice.register(usermodelrequest);
        } catch (Exception e){
            return ErrorresponseDTO.isErrorConsole(500,e+"");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Usermodelrequest usermodelrequest,HttpServletRequest request){
        try{

            logger.info("********* Login Controller called ****** in "+request.getRemoteAddr());
            return authservice.login(usermodelrequest);
        } catch (Exception e){
            return ErrorresponseDTO.isErrorConsole(500,e+"");
        }
    }














}
