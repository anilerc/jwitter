package com.anilerc.jwitter.dto.request;

import jakarta.validation.constraints.NotNull;

public record RemoveLikeRequest(@NotNull Long tweetId) {
}
