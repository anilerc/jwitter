package com.anilerc.jwitter.dto;

import jakarta.validation.constraints.NotNull;

public record DeleteTweetRequest(@NotNull Long tweetId) {
}
