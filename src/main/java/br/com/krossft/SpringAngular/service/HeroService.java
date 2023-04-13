package br.com.krossft.SpringAngular.service;

import br.com.krossft.SpringAngular.models.Hero;
import br.com.krossft.SpringAngular.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService implements InterfaceHeroService{

    @Autowired
    private HeroRepository heroRepository;

    @Override
    public List<Hero> findAll() {
        return this.heroRepository.findAll();
    }

    @Override
    public Optional<Hero> findOne(Long id) {
        return this.heroRepository.findById(id);
    }

    @Override
    public Hero create(Hero hero) {
        return this.heroRepository.save(hero);
    }

    @Override
    public Hero update(Hero hero) {
        return this.heroRepository.save(hero);
    }

    @Override
    public Hero findById(Long id) {
        return this.heroRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        this.heroRepository.deleteById(id);
    }

    @Override
    public Boolean ifIdExits(Long id) {
        return this.heroRepository.existsById(id);
    }
}
