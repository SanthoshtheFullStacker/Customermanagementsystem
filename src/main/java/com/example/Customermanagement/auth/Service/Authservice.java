package com.example.Customermanagement.auth.Service;

import com.example.Customermanagement.DTO.Request.Usermodelrequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface Authservice {
    ResponseEntity<Object> login(Usermodelrequest usermodelrequest);

    ResponseEntity<Object> register(Usermodelrequest usermodelrequest);
}
