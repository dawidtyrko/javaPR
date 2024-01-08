package com.dogs.projectjava.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dog")
@Getter
@Setter
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
}
