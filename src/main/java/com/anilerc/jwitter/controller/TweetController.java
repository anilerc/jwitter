package com.anilerc.jwitter.controller;

import com.anilerc.jwitter.dto.CreateTweetRequest;
import com.anilerc.jwitter.dto.DeleteTweetRequest;
import com.anilerc.jwitter.dto.TweetDto;
import com.anilerc.jwitter.service.TweetService;
import jakarta.validation.Valid;
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

    @DeleteMapping
    public ResponseEntity<Void> deleteTweet(@Valid @RequestBody DeleteTweetRequest request, Principal principal) {
        tweetService.deleteTweet(request, principal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
