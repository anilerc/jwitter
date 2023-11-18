package com.anilerc.jwitter.service;

import com.anilerc.jwitter.dto.request.LikeTweetRequest;
import com.anilerc.jwitter.dto.request.RemoveLikeRequest;
import com.anilerc.jwitter.exception.NotFoundException;
import com.anilerc.jwitter.model.Like;
import com.anilerc.jwitter.repository.LikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final TweetService tweetService;
    private final UserService userService;

    public void likeTweet(LikeTweetRequest request, Principal principal) {
        var tweetToLike = tweetService.getTweetById(request.tweetId());

        var currentUser = userService.findByUsername(principal.getName());

        var newLike = Like.builder().tweet(tweetToLike).user(currentUser).createdAt(LocalDateTime.now()).build();

        likeRepository.save(newLike);
    }

    public void removeLike(RemoveLikeRequest request, Principal principal) {

        var tweetToRemoveLike = tweetService.getTweetById(request.tweetId());

        var currentUser = userService.findByUsername(principal.getName());

        var likeToDelete = likeRepository.findByTweetAndUser(tweetToRemoveLike, currentUser).orElseThrow(() -> new NotFoundException("Like not found!"));

        likeRepository.delete(likeToDelete);
    }
}
