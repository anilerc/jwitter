package com.anilerc.jwitter.dto.request;

import jakarta.validation.constraints.NotNull;

public record DeleteTweetRequest(@NotNull Long tweetId) {
}
