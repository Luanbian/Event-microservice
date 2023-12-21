package com.luan.eventsmicroservice.services;

import com.luan.eventsmicroservice.core.dtos.EmailRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email-service", url = "url-to-email-microservice")
public interface EmailServiceClient {
    @PostMapping("/send")
    void SendEmail (@RequestBody EmailRequestDto emailRequest);
}
