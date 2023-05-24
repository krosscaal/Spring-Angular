package br.com.krossft.SpringAngular.service;

import br.com.krossft.SpringAngular.models.Power;
import br.com.krossft.SpringAngular.repository.PowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PowerService implements InterfacePowerService{

    @Autowired
    PowerRepository powerRepository;

    @Override
    public List<Power> listAll() {
        return this.powerRepository.findAll();
    }

    @Override
    public Power create(Power power) {
        power.setPower(power.getPower().toUpperCase());
        return this.powerRepository.save(power);
    }

    @Override
    public Power update(Power power) {
        power.setPower(power.getPower().toUpperCase());
        return this.powerRepository.save(power);
    }

    @Override
    public Optional<Power> findById(Integer id) {
        return this.powerRepository.findById(id);
    }

    @Override
    public Boolean ifIdExits(Integer id) {
        return this.powerRepository.existsById(id);
    }
}
