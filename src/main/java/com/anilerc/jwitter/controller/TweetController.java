package com.anilerc.jwitter.controller;

import com.anilerc.jwitter.dto.request.CreateTweetRequest;
import com.anilerc.jwitter.dto.response.TweetDto;
import com.anilerc.jwitter.service.TweetService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/tweets")
@AllArgsConstructor
public class TweetController {

    private final TweetService tweetService;

    @PostMapping
    public ResponseEntity<TweetDto> createTweet(@Valid @RequestBody CreateTweetRequest request, Principal principal) {
        return new ResponseEntity<>(tweetService.createTweet(request, principal), HttpStatus.CREATED);
    }

    @DeleteMapping("/{tweetId}")
    public ResponseEntity<Void> deleteTweet(@PathVariable @NotNull Long tweetId, Principal principal) {
        tweetService.deleteTweet(tweetId, principal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
