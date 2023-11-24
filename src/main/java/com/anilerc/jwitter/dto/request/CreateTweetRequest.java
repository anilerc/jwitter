package com.anilerc.jwitter.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateTweetRequest(@NotBlank String content) {
}
