package com.dogs.projectjava.controller;

import com.dogs.projectjava.Dto.DogDto;
import com.dogs.projectjava.entity.Dog;
import com.dogs.projectjava.exceptionHandler.ErrorResponse;
import com.dogs.projectjava.mapper.DogMapper;
import com.dogs.projectjava.service.DogApi;
import com.dogs.projectjava.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/dogs")
@RequiredArgsConstructor
public class DogController {
    private final DogMapper dogMapper;
    private final DogService dogService;
    private final DogApi dogApi = new DogApi();

    @GetMapping()
    public ResponseEntity<List<DogDto>> getAllDogs(){
        var dogs = dogService.getAllDogs().stream().map(dogMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dogs);
    }
    @GetMapping(value = "/{dogName}",produces = "application/json")
    public ResponseEntity<?> getDog(@PathVariable String dogName){

        try{
            String nameTrimmed = dogName.replace("+","").trim().toLowerCase();
            var dog = dogService.getAllDogs().stream().filter(dogEntity -> dogEntity.getName().trim().toLowerCase().contains(nameTrimmed)).findFirst();
            if(dog.isPresent()){
                var dogResponse = dogMapper.toDto(dog.get());
                return ResponseEntity.ok(dogResponse);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Dog not found"));
            }

        }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("An unexpected error occurred"));
        }
    }

    @DeleteMapping(value = "/{dogName}",produces = "application/json")
    public ResponseEntity<?> deleteDog(@PathVariable String dogName){
        try{
            String nameTrimmed = dogName.replace("+","").trim().toLowerCase();
            var dog = dogService.getAllDogs().stream().filter(dogEntity -> dogEntity.getName().trim().toLowerCase().contains(nameTrimmed)).findFirst();
            if(dog.isPresent()){
                dogService.deleteById(dog.get().getId());
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted object successfully");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Dog not found"));
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("An unexpected error occurred"));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDog(@RequestBody Dog dog) {

        System.out.println("Received Dog: " + dog);


        dogService.saveDogToDatabase(dog);

        return new ResponseEntity<>("Dog added successfully", HttpStatus.CREATED);
    }

}
