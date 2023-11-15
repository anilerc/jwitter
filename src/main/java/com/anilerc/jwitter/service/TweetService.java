package com.anilerc.jwitter.service;

import com.anilerc.jwitter.dto.CreateTweetRequest;
import com.anilerc.jwitter.dto.DeleteTweetRequest;
import com.anilerc.jwitter.dto.TweetDto;
import com.anilerc.jwitter.exception.UnauthorizedException;
import com.anilerc.jwitter.exception.UserNotFoundException;
import com.anilerc.jwitter.model.Tweet;
import com.anilerc.jwitter.model.User;
import com.anilerc.jwitter.repository.TweetRepository;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TweetService {

    private final UserService userService;
    private final TweetRepository tweetRepository;

    public TweetDto createTweet(CreateTweetRequest request, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        String content = request.content();
        LocalDateTime createdAt = LocalDateTime.now();

        var newTweet = Tweet.builder().content(content).user(user).createdAt(createdAt).build();

        tweetRepository.save(newTweet);

        return new TweetDto(content);
    }

    public void deleteTweet(DeleteTweetRequest deleteRequest, Principal principal) {
        var tweetId = deleteRequest.tweetId();

        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new UserNotFoundException("Mismo"));

        if (!(principal.getName().equals(tweet.getUser().getUsername()))) {
            throw new UnauthorizedException("Not allowed!");
        }

        tweetRepository.delete(tweet);

    }


}
