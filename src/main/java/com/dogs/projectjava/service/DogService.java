package com.dogs.projectjava.service;

import com.dogs.projectjava.entity.Dog;
import com.dogs.projectjava.entity.DogEntity;
import com.dogs.projectjava.repo.DogRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DogService {
    private final DogRepository dogRepository;

    @Autowired
    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public void saveDogToDatabase(Dog dog){
        DogEntity dogEntity = converter(dog);
        dogRepository.save(dogEntity);
    }

    public DogEntity converter(Dog dog){
        DogEntity dogEntity = new DogEntity();
        dogEntity.setName(dog.getName());
        dogEntity.setPlayfulness(dog.getPlayfulness());
        dogEntity.setEnergy(dog.getEnergy());
        dogEntity.setImageLink(dog.getImageLink());
        dogEntity.setGoodWithChildren(dog.getGoodWithChildren());
        dogEntity.setGoodWithOtherDogs(dog.getGoodWithOtherDogs());
        return dogEntity;
    }

    public List<DogEntity> getAllDogs(){
        return dogRepository.findAll();
    }

    @Transactional
    public void deleteById(int id){
        try {
            dogRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException("Dog with ID " + id + " not found");
        }
    }
    public DogEntity findDogByName(String name){
        return dogRepository.findByName(name);
    }

    public List<String> findDistinctNames(){
        return dogRepository.findDistinctDogNames();
    }
}
