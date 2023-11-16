package com.anilerc.jwitter.dto;

import jakarta.validation.constraints.NotNull;

public record LikeTweetRequest(@NotNull Long tweetId) {
}
