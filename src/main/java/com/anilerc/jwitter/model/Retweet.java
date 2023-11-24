package com.anilerc.jwitter.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "retweets",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tweet_id", "user_id"})
)
@Getter
@Builder
public class Retweet {

    @Id
    @SequenceGenerator(
            name = "retweet_sequence",
            sequenceName = "retweet_sequence",
            allocationSize = 1)

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "retweet_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "tweet_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name="retweet_tweet_fk"
            )
    )
    private Tweet tweet;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name="retweet_user_fk"
            )
    )
    private User user;

}
