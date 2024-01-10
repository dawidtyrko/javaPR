package com.dogs.projectjava.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "favoritedogs")
@Getter
@Setter
public class FavoriteDog {
    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "dogId")
    private DogEntity dog;
}
