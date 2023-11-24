package com.anilerc.jwitter.service;

import com.anilerc.jwitter.exception.NotFoundException;
import com.anilerc.jwitter.model.Like;
import com.anilerc.jwitter.model.Tweet;
import com.anilerc.jwitter.model.User;
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

    public void likeTweet(Long tweetId, Principal principal) {
        var tweetToLike = tweetService.getTweetById(tweetId);

        var currentUser = userService.findByUsername(principal.getName());

        var newLike = Like.builder().tweet(tweetToLike).user(currentUser).build();

        likeRepository.save(newLike);
    }

    public void removeLike(Long tweetId, Principal principal) {
        Tweet tweetToRemoveLike = tweetService.getTweetById(tweetId);

        User currentUser = userService.findByUsername(principal.getName());

        Like likeToDelete = likeRepository.findByTweetAndUser(tweetToRemoveLike, currentUser).orElseThrow(() -> new NotFoundException("Like not found!"));

        likeRepository.delete(likeToDelete);
    }
}
