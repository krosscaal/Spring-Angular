package br.com.krossft.SpringAngular.controller;

import br.com.krossft.SpringAngular.dto.HeroDto;
import br.com.krossft.SpringAngular.models.Hero;
import br.com.krossft.SpringAngular.service.InterfaceHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/heroes")
public class HeroController {


    @Autowired
    private InterfaceHeroService interfaceHeroService;

    /* GET: primeria forma */
    @GetMapping
    @ResponseBody
    public List<Hero> listAll(){
        return this.interfaceHeroService.findAll();
    }

    /* GET: segunda forma */
    @GetMapping("/list")
    public ResponseEntity<List<Hero>> findAll(){
        List<Hero> listAll = this.interfaceHeroService.findAll();
        return ResponseEntity.ok().body(listAll);
    }

    /*GET por id, primeira forma*/
    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Hero> findOne(@PathVariable("id") Long id){
        return Optional.ofNullable(
                this.interfaceHeroService
                        .findOne(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    /* GET por id, segunda forma*/
    @GetMapping(value = "/hero/{id}")
    public ResponseEntity<Hero> findById(@PathVariable("id") Long id){
        Optional<Hero> obj = this.interfaceHeroService.findOne(id);
        if (obj.isPresent()){
            return ResponseEntity.ok(obj.get());
        }
        return ResponseEntity.notFound().build();
    }
    /* GET por id, terceira forma */
    @GetMapping("/gethero/{id}")
    @ResponseBody
    public ResponseEntity<Hero> findHero(@PathVariable("id") Long id){

        return this.interfaceHeroService.findOne(id).map(hero -> ResponseEntity.ok(hero)).orElse(ResponseEntity.notFound().build());
    }

    /* POST primeira forma*/
    @PostMapping
    @ResponseBody
    public ResponseEntity<Hero> create(@Valid @RequestBody Hero hero){
        this.interfaceHeroService.create(hero);
        return new ResponseEntity<>(hero, HttpStatus.CREATED);
    }

    /* POST segunda forma */
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Hero newHero(@Valid @RequestBody HeroDto dto){
        Hero hero = dto.transformParaObjeto();
        return this.interfaceHeroService.create(dto.transformParaObjeto());
    }

    /* PUT primeira forma*/
    @PutMapping("/update/{id}")
    @ResponseBody
    public Hero update(@PathVariable("id") Long id, @RequestBody Hero hero){
        Hero heroUpdate = findOne(id).get();
        if(heroUpdate == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        heroUpdate.setName(hero.getName());
        return this.interfaceHeroService.update(heroUpdate);
    }

    /* PUT segunda forma*/
    @PutMapping("/update-hero/{id}")
    public ResponseEntity<Hero> updateHero(@PathVariable Long id, @Valid @RequestBody Hero hero){
        if (!interfaceHeroService.ifIdExits(id)){
            return ResponseEntity.notFound().build();
        }
        hero.setId(id);
        hero = interfaceHeroService.update(hero);
        return ResponseEntity.ok(hero);
    }


    @DeleteMapping("/delete-hero/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id){
        if(!interfaceHeroService.ifIdExits(id)){
            return ResponseEntity.notFound().build();
        }
        this.interfaceHeroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
