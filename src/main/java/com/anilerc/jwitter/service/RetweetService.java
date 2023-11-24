package com.anilerc.jwitter.service;

import com.anilerc.jwitter.exception.NotFoundException;
import com.anilerc.jwitter.model.Retweet;
import com.anilerc.jwitter.model.Tweet;
import com.anilerc.jwitter.model.User;
import com.anilerc.jwitter.repository.RetweetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class RetweetService {

    private final UserService userService;
    private final TweetService tweetService;
    private final RetweetRepository repository;

    public void retweetTweet(Long tweetId, Principal principal) {
        var currentUserUsername = principal.getName();
        User currentUser = userService.findByUsername(currentUserUsername);
        Tweet tweetToRetweet = tweetService.getTweetById(tweetId);

        var newRetweet = Retweet.builder().user(currentUser).tweet(tweetToRetweet).build();

        repository.save(newRetweet);
    }

    public void removeRetweet(Long tweetId, Principal principal) {
        var currentUserUsername = principal.getName();
        User currentUser = userService.findByUsername(currentUserUsername);

        Tweet tweet = tweetService.getTweetById(tweetId);

        Retweet retweetToDelete = repository.findByTweetAndUser(tweet, currentUser).orElseThrow(() -> new NotFoundException("Retweet not found!"));

        repository.delete(retweetToDelete);

    }


}
