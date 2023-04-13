package br.com.krossft.SpringAngular.service;

import br.com.krossft.SpringAngular.models.Power;

import java.util.List;
import java.util.Optional;

public interface InterfacePowerService {

    List<Power> listAll();

    Power create(Power power);

    Power update(Power power);

    Optional<Power> findById(Integer id);

    Boolean ifIdExits(Integer id);

}
