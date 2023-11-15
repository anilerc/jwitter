package com.anilerc.jwitter.controller;

import com.anilerc.jwitter.dto.CreateTweetRequest;
import com.anilerc.jwitter.dto.TweetDto;
import com.anilerc.jwitter.service.TweetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/tweets")
@AllArgsConstructor
public class TweetController {

    private final TweetService tweetService;

    @PostMapping
    public TweetDto createTweet(@Valid @RequestBody CreateTweetRequest request, Principal principal) {
        return tweetService.createTweet(request, principal);
    }
}
