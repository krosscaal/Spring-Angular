package br.com.krossft.SpringAngular.controller;

import br.com.krossft.SpringAngular.dto.HeroDto;
import br.com.krossft.SpringAngular.models.Hero;
import br.com.krossft.SpringAngular.service.InterfaceHeroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Listar Heroes v1")
    @GetMapping
    @ResponseBody
    public List<Hero> listAll(){
        return this.interfaceHeroService.findAll();
    }

    /* GET: segunda forma */
    @ApiOperation(value = "listar Heroes v2")
    @GetMapping("/list")
    public ResponseEntity<List<Hero>> findAll(){
        List<Hero> listAll = this.interfaceHeroService.listAll();
        return ResponseEntity.ok().body(listAll);
    }

    /*GET por id, primeira forma*/
    @ApiOperation(value = "buscar por id v1")
    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Hero> findOne(@PathVariable("id") Long id){
        return Optional.ofNullable(
                this.interfaceHeroService
                        .findOne(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    /* GET por id, segunda forma*/
    @ApiOperation(value = "buscar por id v2")
    @GetMapping(value = "/hero/{id}")
    public ResponseEntity<Hero> findById(@PathVariable("id") Long id){
        Optional<Hero> obj = this.interfaceHeroService.findOne(id);
        if (obj.isPresent()){
            return ResponseEntity.ok(obj.get());
        }
        return ResponseEntity.notFound().build();
    }
    /* GET por id, terceira forma */
    @ApiOperation(value = "buscar por id v3")
    @GetMapping("/gethero/{id}")
    @ResponseBody
    public ResponseEntity<Hero> findHero(@PathVariable("id") Long id){

        return this.interfaceHeroService.findOne(id).map(hero -> ResponseEntity.ok(hero)).orElse(ResponseEntity.notFound().build());
    }

    /* POST primeira forma*/
    @ApiOperation(value = "criar heroe v1")
    @PostMapping
    @ResponseBody
    public ResponseEntity<Hero> create(@Valid @RequestBody Hero hero){
        this.interfaceHeroService.create(hero);
        return new ResponseEntity<>(hero, HttpStatus.CREATED);
    }

    /* POST segunda forma */
    @ApiOperation(value = "criar heroe v2")
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Hero newHero(@Valid @RequestBody HeroDto dto){
        return this.interfaceHeroService.createHero(dto);
    }

    /* POST terceira forma */
    @ApiOperation(value = "criar heroe v3")
    @PostMapping("/new2")
    @ResponseBody
    public ResponseEntity<Hero> newHero2(@Valid @RequestBody HeroDto dto) {

        final Hero hero = this.interfaceHeroService.createHero(dto);
        return new ResponseEntity<>(hero, HttpStatus.CREATED);

    }

    /* PUT primeira forma*/
    @ApiOperation(value = "atualizar heroe v1")
    @PutMapping("/update/{id}")
    @ResponseBody
    public Hero update(@PathVariable("id") Long id, @RequestBody HeroDto dto){
        this.interfaceHeroService.findOne(id)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return this.interfaceHeroService.updateHero(id, dto);
    }

    /* PUT segunda forma*/
    @ApiOperation(value = "atualizar heroe v2")
    @PutMapping("/update-hero/{id}")
    public ResponseEntity<Hero> updateHero(@PathVariable Long id, @Valid @RequestBody Hero hero){
        if (!interfaceHeroService.ifIdExits(id)){
            return ResponseEntity.notFound().build();
        }
        hero.setId(id);
        hero = interfaceHeroService.update(hero);
        return ResponseEntity.ok(hero);
    }


    @ApiOperation(value = "remover heroe por id")
    @DeleteMapping("/delete-hero/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id){
        this.interfaceHeroService.findOne(id)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        this.interfaceHeroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
