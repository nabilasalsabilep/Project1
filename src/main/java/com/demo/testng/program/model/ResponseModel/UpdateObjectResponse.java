package com.demo.testng.program.model.ResponseModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UpdateObjectResponse {
    @JsonProperty("id")
    private Integer objectID;

    @JsonProperty("name")
    private String objectName;

    @JsonProperty("data")
    private DataDetails data;

    @Data
    public static class DataDetails {
        @JsonProperty("year")
        private String year;

        @JsonProperty("price")
        private Double price;

        @JsonProperty("CPU model")
        private String cpuModel;

        @JsonProperty("Hard disk size")
        private String hardDiskSize;

        @JsonProperty("capacity")
        private String capacity;

        @JsonProperty("screen_size")
        private String screenSize;

        @JsonProperty("color")
        private String color;
    }
    
}