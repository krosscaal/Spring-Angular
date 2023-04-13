package br.com.krossft.SpringAngular.service;

import br.com.krossft.SpringAngular.models.Tarefa;
import br.com.krossft.SpringAngular.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService implements InterfaceTarefaService{

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public List<Tarefa> findAll() {
        return this.tarefaRepository.findAll();
    }

    @Override
    public Optional<Tarefa> findOne(Long id) {
        return this.tarefaRepository.findById(id);
    }

    @Override
    public Tarefa create(Tarefa tarefa) {
        return this.tarefaRepository.save(tarefa);
    }
}
