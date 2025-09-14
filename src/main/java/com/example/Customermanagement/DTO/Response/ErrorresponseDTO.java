package com.example.Customermanagement.DTO.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.ResponseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorresponseDTO {
    private int status;
    private String message;

    public ErrorresponseDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResponseEntity<Object> isErrorConsole(int status, String message){
        ErrorresponseDTO errorresponseDTO = new ErrorresponseDTO(status,message);
        return ResponseEntity.status(status).body(errorresponseDTO);
    }
}
