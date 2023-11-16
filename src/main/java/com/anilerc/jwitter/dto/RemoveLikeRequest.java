package com.anilerc.jwitter.dto;

import jakarta.validation.constraints.NotNull;

public record RemoveLikeRequest(@NotNull Long tweetId) {
}
