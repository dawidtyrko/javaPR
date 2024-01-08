package com.dogs.projectjava.repo;

import com.dogs.projectjava.entity.Dog;
import com.dogs.projectjava.entity.DogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends JpaRepository<DogEntity,Integer> {
}
