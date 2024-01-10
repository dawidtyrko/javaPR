package com.dogs.projectjava.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Dog {


    @JsonProperty("image_link")
    private String imageLink;
    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @JsonProperty("good_with_children")
    private int goodWithChildren;

    @JsonProperty("good_with_other_dogs")
    private int goodWithOtherDogs;

    private int shedding;
    private int grooming;
    private int drooling;
    @JsonProperty("coat_length")
    private int coatLength;
    @JsonProperty("good_with_strangers")
    private int goodWithStrangers;

    private int playfulness;
    private int protectiveness;
    private int trainability;
    private int energy;
    private int barking;
    @JsonProperty("min_life_expectancy")
    private int minLifeExpectancy;
    @JsonProperty("max_life_expectancy")
    private int maxLifeExpectancy;

    @JsonProperty("max_height_male")
    private int maxHeightMale;

    @JsonProperty("max_height_female")
    private int maxHeightFemale;

    @JsonProperty("max_weight_male")
    private int maxWeightMale;

    @JsonProperty("max_weight_female")
    private int maxWeightFemale;

    @JsonProperty("min_height_male")
    private int minHeightMale;

    @JsonProperty("min_height_female")
    private Integer minHeightFemale;


    @JsonProperty("min_weight_male")
    private Integer minWeightMale;

    @JsonProperty("min_weight_female")
    private int minWeightFemale;

    private String name;

    @Override
    public String toString() {
        return "Dog{" +
                "imageLink='" + imageLink + '\'' +
                ", goodWithChildren=" + goodWithChildren +
                ", goodWithOtherDogs=" + goodWithOtherDogs +
                ", shedding=" + shedding +
                ", grooming=" + grooming +
                ", drooling=" + drooling +
                ", coatLength=" + coatLength +
                ", goodWithStrangers=" + goodWithStrangers +
                ", playfulness=" + playfulness +
                ", protectiveness=" + protectiveness +
                ", trainability=" + trainability +
                ", energy=" + energy +
                ", barking=" + barking +
                ", minLifeExpectancy=" + minLifeExpectancy +
                ", maxLifeExpectancy=" + maxLifeExpectancy +
                ", maxHeightMale=" + maxHeightMale +
                ", maxHeightFemale=" + maxHeightFemale +
                ", maxWeightMale=" + maxWeightMale +
                ", maxWeightFemale=" + maxWeightFemale +
                ", minHeightMale=" + minHeightMale +
                ", minHeightFemale=" + minHeightFemale +
                ", minWeightMale=" + minWeightMale +
                ", minWeightFemale=" + minWeightFemale +
                ", name='" + name + '\'' +
                '}';
    }
}
