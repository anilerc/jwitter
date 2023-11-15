package com.anilerc.jwitter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTweetRequest(@NotBlank String content) {
}
