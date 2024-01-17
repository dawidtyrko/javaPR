package com.dogs.projectjava.repo;

import com.dogs.projectjava.entity.Dog;
import com.dogs.projectjava.entity.DogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<DogEntity,Integer> {
    @Query("SELECT d FROM DogEntity d WHERE d.name = :name")
    DogEntity findByName(@Param("name") String name);

    @Query("SELECT DISTINCT d.name FROM DogEntity d")
    List<String> findDistinctDogNames();
}
