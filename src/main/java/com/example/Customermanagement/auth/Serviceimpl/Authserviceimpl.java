package com.example.Customermanagement.auth.Serviceimpl;

import com.example.Customermanagement.DTO.Request.Usermodelrequest;
import com.example.Customermanagement.DTO.Response.ErrorresponseDTO;
import com.example.Customermanagement.DTO.Response.SuccessreponseDTO;
import com.example.Customermanagement.auth.Model.Usermodel;
import com.example.Customermanagement.auth.Model.Userroles;
import com.example.Customermanagement.auth.Repository.Userrepository;
import com.example.Customermanagement.auth.Service.Authservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class Authserviceimpl implements Authservice {


    public static final Logger logger = LoggerFactory.getLogger(Authserviceimpl.class);


    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    private Userrepository userrepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTserviceimpl jwTserviceimpl;



    @Override
    public ResponseEntity<Object> login(Usermodelrequest usermodelrequest) {
        try{

            logger.info("*********** Login API Called ******************");

            if(usermodelrequest.getPassword() == null || usermodelrequest.getUsername() == null){

                return ErrorresponseDTO.isErrorConsole(404,"Required Value Not Found");

            }

            Optional<Usermodel> userdatabbyusername = userrepository.findFirstByUsername(usermodelrequest.getUsername());


            if(userdatabbyusername.isEmpty()){

                return ErrorresponseDTO.isErrorConsole(400,"User Not Available");

            }

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usermodelrequest.getUsername(),usermodelrequest.getPassword()));

            Map<String, String> tokenres = new HashMap<>();

            if(authentication.isAuthenticated()){

                tokenres.put("token",jwTserviceimpl.generateToken(userdatabbyusername.get()));

                return SuccessreponseDTO.isSuccessConsole(200,"User Logged In Successfully",tokenres);

            }

            return ErrorresponseDTO.isErrorConsole(400,"Invalid Credentials");

        } catch (Exception e){
            return ErrorresponseDTO.isErrorConsole(500,e+"");
        }
    }

    @Override
    public ResponseEntity<Object> register(Usermodelrequest usermodelrequest) {
        try{

            logger.info("*********** register service Called ******************");


            if(usermodelrequest.getPassword() == null || usermodelrequest.getPassword() == null){
                return ErrorresponseDTO.isErrorConsole(404,"Required Values Not Found");
            }

            Optional<Usermodel> userdata = userrepository.findFirstByUsername(usermodelrequest.getUsername());

            if(userdata.isPresent()){
                return ErrorresponseDTO.isErrorConsole(400,"User Name Already Exists");
            }

            Usermodel usermodel = new Usermodel(usermodelrequest.getUsername(), usermodelrequest.getPassword(), usermodelrequest.getRole());

            usermodel.setPassword(passwordEncoder.encode(usermodelrequest.getPassword()));

            Usermodel savedData = userrepository.save(usermodel);

            return SuccessreponseDTO.isSuccessConsole(200,"User Registered Successfully",savedData);
        } catch (Exception e){
            return ErrorresponseDTO.isErrorConsole(500,e+"");
        }
    }


}
