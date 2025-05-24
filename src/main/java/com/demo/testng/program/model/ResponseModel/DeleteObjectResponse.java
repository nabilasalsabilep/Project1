package com.demo.testng.program.model.ResponseModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeleteObjectResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;
}
