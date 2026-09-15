package com.example.config;

import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "education")
public class EducationActuatorEndpoint {

    @ReadOperation
    public Map<String, Object> educationStatus() {

        return Map.of(
                "application", "Education Management System",
                "status", "UP",
                "message", "Education Management System is running successfully"
        );
    }
}