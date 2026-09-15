package com.example.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ApiResponse;
import com.example.dto.DashboardResponseDto;
import com.example.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<ApiResponse<DashboardResponseDto>> getDashboard() {

        DashboardResponseDto dashboard =
                dashboardService.getDashboard();

        ApiResponse<DashboardResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Dashboard data fetched successfully",
                        dashboard,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }
}