package com.demo.testng.program.model.RequestModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RequestRegister {
    @JsonProperty("email")
    private String email;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("department")
    private String department;

    @JsonProperty("phone_number")
    private String phoneNumber;

    // public RequestRegister(String email, String password, String fullname, String department, String phonenumber) {
    //     this.email = email;
    //     this.password = password;
    //     this.fullName = fullname;
    //     this.phoneNumber = phonenumber;
    // }
}
