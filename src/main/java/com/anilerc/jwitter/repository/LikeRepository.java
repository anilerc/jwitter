package com.anilerc.jwitter.repository;

import com.anilerc.jwitter.model.Like;
import com.anilerc.jwitter.model.Tweet;
import com.anilerc.jwitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByTweetAndUser(Tweet tweet, User user);
}
