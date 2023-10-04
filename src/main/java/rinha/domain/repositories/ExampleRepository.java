package rinha.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rinha.domain.entities.Example;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
}