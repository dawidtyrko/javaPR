package com.dogs.projectjava.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dog")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_link")
    private String imageLink;

    @Column(name = "playfulness")
    private int playfulness;

    @Column(name = "good_with_children")
    private int goodWithChildren;

    @Column(name = "good_with_other_dogs")
    private int goodWithOtherDogs;

    @Column(name = "energy")
    private int energy;

    @OneToMany(mappedBy = "dog", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FavoriteDog> favoriteDogs = new HashSet<>();
}
