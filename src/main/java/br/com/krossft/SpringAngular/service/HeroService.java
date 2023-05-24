package br.com.krossft.SpringAngular.service;

import br.com.krossft.SpringAngular.dto.HeroDto;
import br.com.krossft.SpringAngular.models.Hero;
import br.com.krossft.SpringAngular.models.Power;
import br.com.krossft.SpringAngular.repository.HeroRepository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HeroService implements InterfaceHeroService{

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PowerService powerService;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Hero> findAll() {

        return this.heroRepository.findAll(Sort.by("name"));
    }

    @Override
    public List<Hero> listAll() {

        final TypedQuery<Hero> query =
            this.entityManager.createQuery("FROM Hero h order by h.name", Hero.class);
        return query.getResultList();
    }

    @Override
    public Optional<Hero> findOne(Long id) {
        return this.heroRepository.findById(id);
    }

    @Override
    public Hero create(Hero hero) {
        hero.setName(hero.getName().toUpperCase());
        hero.setEmail(hero.getEmail().toLowerCase());
        final Power power = powerService.findById(hero.getPower().getId())
            .orElseThrow(() -> new RuntimeException("id Power não existe"));
        hero.setPower(power);

        return this.heroRepository.save(hero);
    }

    @Override
    public Hero createHero(final HeroDto dto) {
        Hero hero = modelMapper.map(dto, Hero.class);
        final Power power = powerService.findById(dto.getPower())
            .orElseThrow(() -> new RuntimeException("id Power não existe"));
        hero.setPower(power);
        hero.setEmail(dto.getEmail().toLowerCase());
        hero.setName(dto.getName().toUpperCase());
        return this.heroRepository.save(hero);
    }

    @Override
    public Hero update(Hero hero) {
        hero.setName(hero.getName().toUpperCase());
        hero.setEmail(hero.getEmail().toLowerCase());
        final Power power = powerService.findById(hero.getPower().getId())
            .orElseThrow(() -> new RuntimeException("id Power não existe"));
        hero.setPower(power);
        return this.heroRepository.save(hero);
    }

    @Override
    public Hero updateHero(final Long id, final HeroDto dto) {
        Hero hero = modelMapper.map(dto, Hero.class);
        hero.setId(id);
        final Power power = powerService.findById(dto.getPower())
            .orElseThrow(() -> new RuntimeException("id Power não existe"));
        hero.setPower(power);
        hero.setEmail(dto.getEmail().toLowerCase());
        hero.setName(dto.getName().toUpperCase());

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
