package com.luan.eventsmicroservice.core.dtos;

public record EmailRequestDto(
    String to,
    String subject,
    String body
) {}
