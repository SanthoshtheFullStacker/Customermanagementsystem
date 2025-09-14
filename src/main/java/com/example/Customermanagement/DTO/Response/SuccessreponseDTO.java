package com.example.Customermanagement.DTO.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.ResponseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessreponseDTO {
    private int status;
    private String message;
    private Object data;

    public SuccessreponseDTO(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public SuccessreponseDTO(int status, String message) {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResponseEntity<Object> isSuccessConsole(int status, String message, Object data){
        SuccessreponseDTO successreponseDTO = new SuccessreponseDTO(status,message,data);
        return ResponseEntity.status(status).body(successreponseDTO);
    }


}
