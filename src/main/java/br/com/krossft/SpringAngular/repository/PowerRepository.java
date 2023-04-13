package br.com.krossft.SpringAngular.repository;

import br.com.krossft.SpringAngular.models.Power;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerRepository extends JpaRepository<Power, Integer> {

}
