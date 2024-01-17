package com.dogs.projectjava;
import com.dogs.projectjava.entity.Dog;
import com.dogs.projectjava.entity.DogEntity;
import com.dogs.projectjava.repo.DogRepository;
import com.dogs.projectjava.service.DogService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
public class DogServiceTest {
    @Mock
    private DogRepository dogRepository;

    @InjectMocks
    private DogService dogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveDogToDatabase() {
        Dog dog = new Dog();
        DogEntity dogEntity = new DogEntity();
        dogEntity.setName("DogName");
        dogEntity.setPlayfulness(3);
        dogEntity.setEnergy(4);
        dogEntity.setImageLink("imageLink");
        dogEntity.setGoodWithChildren(4);
        dogEntity.setGoodWithOtherDogs(3);

        when(dogRepository.save(any(DogEntity.class))).thenReturn(dogEntity);

        dogService.saveDogToDatabase(dog);

        verify(dogRepository, times(1)).save(any(DogEntity.class));
    }

    @Test
    void testGetAllDogs() {
        List<DogEntity> dogList = new ArrayList<>();
        DogEntity dogEntity = new DogEntity();
        DogEntity dogEntity1 = new DogEntity();
        dogEntity.setName("test");
        dogEntity1.setName("test1");
        dogList.add(dogEntity);
        dogList.add(dogEntity1);

        when(dogRepository.findAll()).thenReturn(dogList);

        List<DogEntity> result = dogService.getAllDogs();

        assertEquals(2, result.size());
        verify(dogRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById() {
        int dogId = 1;

        dogService.deleteById(dogId);

        verify(dogRepository, times(1)).deleteById(dogId);
    }


    @Test
    void testDeleteByIdNotFound() {
        int dogId = 1;

        doThrow(new EmptyResultDataAccessException(1)).when(dogRepository).deleteById(anyInt());

        assertThrows(EntityNotFoundException.class, () -> dogService.deleteById(dogId));

        verify(dogRepository, times(1)).deleteById(dogId);
    }
}
