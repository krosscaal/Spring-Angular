package br.com.krossft.SpringAngular.service;

import br.com.krossft.SpringAngular.models.Hero;

import java.util.List;
import java.util.Optional;

public interface InterfaceHeroService {

    List<Hero> findAll();

    Optional<Hero> findOne(Long id);

    Hero findById(Long id);

    Hero create(Hero hero);

    Hero update(Hero hero);

    void deleteById(Long id);

    Boolean ifIdExits(Long id);
}
