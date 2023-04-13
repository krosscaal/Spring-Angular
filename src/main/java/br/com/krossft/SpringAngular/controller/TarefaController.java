package br.com.krossft.SpringAngular.controller;

import br.com.krossft.SpringAngular.models.Tarefa;
import br.com.krossft.SpringAngular.service.InterfaceTarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TarefaController {

    @Autowired
    private InterfaceTarefaService interfaceTarefaService;

    @GetMapping
    @ResponseBody
    public List<Tarefa> listAll(){
        return this.interfaceTarefaService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Tarefa> findOne(@PathVariable("id") Long id){
        return this.interfaceTarefaService.findOne(id);
    }

    @PostMapping
    @ResponseBody
    public Tarefa create(@RequestBody Tarefa tarefa){
        return this.interfaceTarefaService.create(tarefa);
    }
}
