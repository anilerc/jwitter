package com.anilerc.jwitter.controller;

import com.anilerc.jwitter.service.LikeService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/likes")
@AllArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{tweetId}")
    public ResponseEntity<Void> likeTweet(@PathVariable @NotNull Long tweetId, Principal principal) {
        likeService.likeTweet(tweetId, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{tweetId}")
    public ResponseEntity<Void> removeLike(@PathVariable @NotNull Long tweetId, Principal principal) {
        likeService.removeLike(tweetId, principal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
