package com.anilerc.jwitter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "likes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tweet_id", "user_id"})
        )
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Like {
    @Id
    @SequenceGenerator(
            name = "like_sequence",
            sequenceName = "like_sequence",
            allocationSize = 1)

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "like_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "tweet_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name="like_tweet_fk"
            )
    )
    private Tweet tweet;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name="like_user_fk"
            )
    )
    private User user;

    @Column(nullable = true)
    private LocalDateTime createdAt;
}
