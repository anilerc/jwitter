package com.anilerc.jwitter.controller;

import com.anilerc.jwitter.dto.LikeTweetRequest;
import com.anilerc.jwitter.dto.RemoveLikeRequest;
import com.anilerc.jwitter.service.LikeService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<Void> likeTweet(@Valid @RequestBody LikeTweetRequest request, Principal principal) {
        likeService.likeTweet(request, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeLike(@Valid @RequestBody RemoveLikeRequest request, Principal principal) {
        likeService.removeLike(request, principal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
