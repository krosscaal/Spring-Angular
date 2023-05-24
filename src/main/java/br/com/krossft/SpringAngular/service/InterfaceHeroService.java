package br.com.krossft.SpringAngular.service;

import br.com.krossft.SpringAngular.dto.HeroDto;
import br.com.krossft.SpringAngular.models.Hero;

import java.util.List;
import java.util.Optional;

public interface InterfaceHeroService {

    List<Hero> findAll();

    List<Hero> listAll();

    Optional<Hero> findOne(Long id);

    Hero findById(Long id);

    Hero create(Hero hero);

    Hero createHero(HeroDto dto);

    Hero update(Hero hero);

    Hero updateHero(Long id, HeroDto dto);

    void deleteById(Long id);

    Boolean ifIdExits(Long id);
}
