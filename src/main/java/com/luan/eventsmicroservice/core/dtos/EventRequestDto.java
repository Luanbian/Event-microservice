package com.luan.eventsmicroservice.core.dtos;

public record EventRequestDto(
        Integer maxParticipants,
        String date,
        String title,
        String description
) {}
