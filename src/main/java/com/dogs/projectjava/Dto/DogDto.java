package com.dogs.projectjava.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DogDto {
    private int id;
    private String name;
    private String imageLink;
    private int playfulness;
    private int goodWithChildren;
    private int goodWithOtherDogs;
    private int energy;
}
