package br.com.krossft.SpringAngular.controller;

import br.com.krossft.SpringAngular.models.Power;
import br.com.krossft.SpringAngular.service.InterfacePowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/heroes/power")
public class PowerController {

    @Autowired
    InterfacePowerService interfacePowerService;

    @GetMapping("/list")
    public ResponseEntity<List<Power>> findAll(){
        List<Power> listAll = this.interfacePowerService.listAll();
        return ResponseEntity.ok().body(listAll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Power> findById(@PathVariable ("id") Integer id){
        Optional<Power> obj = this.interfacePowerService.findById(id);
        if(obj.isPresent()){
            return ResponseEntity.ok().body(obj.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/new")
    public ResponseEntity<Power> create(@Valid @RequestBody Power power){
        this.interfacePowerService.create(power);
        return new ResponseEntity<>(power,HttpStatus.CREATED);
    }

    @PutMapping("/update-power/{id}")
    public ResponseEntity<Power> update(@PathVariable ("id") Integer id, @Valid @RequestBody Power power){
        if(!this.interfacePowerService.ifIdExits(id)){
            return ResponseEntity.notFound().build();
        }
        power.setId(id);
        power = this.interfacePowerService.update(power);
        return ResponseEntity.ok(power);
    }
}
