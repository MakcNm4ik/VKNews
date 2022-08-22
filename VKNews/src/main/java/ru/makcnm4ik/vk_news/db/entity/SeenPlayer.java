package ru.makcnm4ik.vk_news.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Table(name = "seen_players")
@Entity
public class SeenPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String uuid;

    public SeenPlayer(String uuid) {
        this.uuid = uuid;
    }
}
