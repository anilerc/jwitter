package com.anilerc.jwitter.service;

import com.anilerc.jwitter.dto.request.CreateTweetRequest;
import com.anilerc.jwitter.dto.response.TweetDto;
import com.anilerc.jwitter.exception.NotFoundException;
import com.anilerc.jwitter.exception.UnauthorizedException;
import com.anilerc.jwitter.model.Tweet;
import com.anilerc.jwitter.model.User;
import com.anilerc.jwitter.repository.TweetRepository;
import lombok.AllArgsConstructor;
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

        Tweet newTweet = Tweet.builder().content(content).user(user).build();

        tweetRepository.save(newTweet);

        return new TweetDto(content);
    }

    public void deleteTweet(Long tweetId, Principal principal) {

        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new NotFoundException("Tweet not found!"));

        if (!(principal.getName().equals(tweet.getUser().getUsername()))) {
            throw new UnauthorizedException("Not allowed!");
        }

        tweetRepository.delete(tweet);

    }

    public Tweet getTweetById(Long id) {
        return tweetRepository.findById(id).orElseThrow(() -> new NotFoundException("Tweet not found!"));
    }


}
