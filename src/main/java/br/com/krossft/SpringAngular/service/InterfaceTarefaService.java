package br.com.krossft.SpringAngular.service;

import br.com.krossft.SpringAngular.models.Tarefa;

import java.util.List;
import java.util.Optional;

public interface InterfaceTarefaService {
    List<Tarefa> findAll();

    Optional<Tarefa> findOne(Long id);

    Tarefa create( Tarefa tarefa);
}
