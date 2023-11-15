package com.anilerc.jwitter.service;

import com.anilerc.jwitter.dto.CreateTweetRequest;
import com.anilerc.jwitter.dto.TweetDto;
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


}
