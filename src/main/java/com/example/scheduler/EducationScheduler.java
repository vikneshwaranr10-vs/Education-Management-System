package com.example.scheduler;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EducationScheduler {

    @Scheduled(fixedRate = 30000)
    public void runScheduledTask() {

        log.info("Education Scheduler executed at: {}",
                LocalDateTime.now());
    }
}