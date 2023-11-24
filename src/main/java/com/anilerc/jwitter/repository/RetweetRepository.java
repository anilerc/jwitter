package com.anilerc.jwitter.repository;

import com.anilerc.jwitter.model.Retweet;
import com.anilerc.jwitter.model.Tweet;
import com.anilerc.jwitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RetweetRepository extends JpaRepository<Retweet, Long> {
    Optional<Retweet> findByTweetAndUser(Tweet tweet, User user);
}
