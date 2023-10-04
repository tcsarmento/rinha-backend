package rinha.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rinha.domain.entities.Pessoa;


import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, String> {

    public Optional<Pessoa> findPessoaByApelido(@Param("apelido") String apelido);

    @Query(value = "SELECT * FROM PESSOA WHERE TERMO LIKE %:termo%", nativeQuery = true)
    public List<Pessoa> findPessoasLikeTermo(@Param("termo") String termo);

    @Query(value = "SELECT count(1) FROM PESSOA", nativeQuery = true)
    public Long findCount();
}