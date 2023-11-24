package com.anilerc.jwitter.controller;

import com.anilerc.jwitter.dto.response.MessageResponse;
import com.anilerc.jwitter.service.RetweetService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/retweets")
@AllArgsConstructor
public class RetweetController {

    private final RetweetService retweetService;

    @PostMapping("/{tweetId}")
    public ResponseEntity<MessageResponse> retweetTweet(@PathVariable @NotNull Long tweetId, Principal principal) {
        retweetService.retweetTweet(tweetId, principal);
        return new ResponseEntity<>(new MessageResponse("Retweeted successfully."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{tweetId}")
    public ResponseEntity<MessageResponse> deleteTweet(@PathVariable @NotNull Long tweetId, Principal principal) {
        retweetService.removeRetweet(tweetId, principal);
        return new ResponseEntity<>(new MessageResponse("Retweet removed successfully."), HttpStatus.NO_CONTENT);
    }



}
