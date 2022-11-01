package com.example.SpringLaptop.Controller;

import com.example.SpringLaptop.Entities.Laptop;
import com.example.SpringLaptop.Repository.LaptopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private LaptopRepository laptopRepository;

    public LaptopController (LaptopRepository laptopRepository){
        this.laptopRepository = laptopRepository;
    }

    @PostMapping("/laptop")
    public Laptop create(@RequestBody Laptop laptop){
       return laptopRepository.save(laptop);
    }

    @GetMapping("/laptop")
    public List<Laptop> findAll(){
       return laptopRepository.findAll();
    }

    @GetMapping("/laptop/{id}")
    public ResponseEntity<Laptop> findById(@PathVariable Long id){
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);
        if(laptopOpt.isPresent()){
            return ResponseEntity.ok(laptopOpt.get());
        }else{
            return  ResponseEntity.notFound().build();
        }

    }
    @PutMapping("/laptop")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
    if (!laptopRepository.existsById(laptop.getId())){
        return ResponseEntity.notFound().build();
    }
    if(laptop.getId()==null){
    return ResponseEntity.badRequest().build();
    }
    Laptop result = laptopRepository.save(laptop);
    return ResponseEntity.ok(result);
    }

    @DeleteMapping("laptop/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        if(!laptopRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/laptop")
    public ResponseEntity<Laptop> deleteAll(){
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();

    }
}
