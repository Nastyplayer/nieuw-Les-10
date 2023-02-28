package com.example.easymodel11.controller;


import com.example.easymodel11.exceptions.RecordNotFoundException;
import com.example.easymodel11.model.Television;
import com.example.easymodel11.repository.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("televisions")
public class TelevisionController {

    @Autowired
    TelevisionRepository repos;
    private ArrayList<Television> televisions;
    //= new ArrayList<Television>();


    @GetMapping("/televisions")
  //  public ResponseEntity<Iterable<Television>> getTelevision() {
       // return ResponseEntity.ok(repos.findAll());
    public ResponseEntity<Object> getAllTelevisions() {
        return ResponseEntity.ok(televisions);
    }
////
    @PostMapping("televisions")
    public ResponseEntity<Television> createTelevisiont(@RequestBody Television t) {
            repos.save(t);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + t.getId()).toUriString());

        return ResponseEntity.created(uri).body(t);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        repos.deleteById(id);
        return ResponseEntity.noContent().build();
    }



    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteByName(@PathVariable String name) {
        repos.deleteByName(name);
       return ResponseEntity.noContent().build();
    }

////
     @GetMapping("/find/{name}")
     public ResponseEntity<String> findByName(@PathVariable String name) {
        Optional<Television> optionaltv = repos.findByName(name);
    if (optionaltv.isPresent())

         return ResponseEntity.ok(optionaltv.get().name);
    else
        return ResponseEntity.noContent().build();
     }


     @GetMapping ("/{id}")
    public ResponseEntity<String> GetByid(@PathVariable int id) {

       if (id == 10) {
            throw new RecordNotFoundException("het werkt");
        }
        return ResponseEntity.ok("television: "+id);
        }



    @PutMapping ("/{id}")
    public ResponseEntity<Television> updateList(@PathVariable long id, @RequestBody String television) {
        Optional<Television> optionaltv = repos.findById(id);
       if (optionaltv.isEmpty() ) {
           throw new RecordNotFoundException("no found", HttpStatus.NOT_FOUND);
       } else {
          Television updateList = optionaltv.get();
          repos.save(updateList);
           return ResponseEntity.ok(updateList);
       }}
}



//      if (id >= 0 && id < Televisions.size() ) {
  //          Television.set(id, television);
  //      return ResponseEntity.ok("updated");

