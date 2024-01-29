package com.dogs.projectjava.mapper;

import com.dogs.projectjava.Dto.DogDto;
import com.dogs.projectjava.entity.Dog;
import com.dogs.projectjava.entity.DogEntity;
import org.springframework.stereotype.Component;

@Component
public class DogMapper {
    public DogDto toDto(DogEntity dogEntity){
        return new DogDto(dogEntity.getId(),dogEntity.getName(),dogEntity.getImageLink(),dogEntity.getPlayfulness(),dogEntity.getGoodWithChildren(),dogEntity.getGoodWithOtherDogs(),dogEntity.getEnergy());
    }
    public DogEntity toEntity(Dog dogDto){
        return  new DogEntity(dogDto.getName(), dogDto.getImageLink(), dogDto.getPlayfulness(),dogDto.getGoodWithChildren(),dogDto.getGoodWithOtherDogs(),dogDto.getEnergy());
    }
}
