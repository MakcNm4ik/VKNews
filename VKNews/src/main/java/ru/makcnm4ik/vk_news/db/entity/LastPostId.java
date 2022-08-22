package ru.makcnm4ik.vk_news.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Table(name = "last_post_id")
@Entity
public class LastPostId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_post_id")
    @Setter
    private int lastPostId;

    public LastPostId(int lastPostId) {
        this.lastPostId = lastPostId;
    }
}
