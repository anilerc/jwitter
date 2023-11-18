package com.anilerc.jwitter.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTweetRequest(@NotBlank String content) {
}
