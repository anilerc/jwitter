package com.anilerc.jwitter.repository;

import com.anilerc.jwitter.model.Like;
import com.anilerc.jwitter.model.Tweet;
import com.anilerc.jwitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    /*
    @Query(
            "SELECT l FROM likes l INNER JOIN tweets t ON ?1 = l.tweet_id INNER JOIN users u ON ?2 = l.user_id"
    )
    Like findByTweetAndUsername(Long tweetId, Long userId);

     */
}
