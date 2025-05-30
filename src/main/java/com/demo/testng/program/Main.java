package com.demo.testng.program;

import java.io.ObjectInputFilter.Config;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        final Map<String, Object> data = new HashMap<>();

        data.put("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjIiLCJpYXQiOjE3NDgxNTA5ODV9.LPl3AVoEyXw-w733FG3GPDBxw5C4Msfoz0h3-cDeXjU");
        data.put("baseUrl", "https://whitesmokehouse.com");

        System.out.println("Token: " + data.get("token"));
        System.out.println("Base URL: " + data.get("baseUrl"));
    }
}