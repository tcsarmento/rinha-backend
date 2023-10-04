package rinha.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rinha.domain.entities.Pessoa;
import rinha.domain.repositories.PessoaRepository;


@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	/*public Optional<Pessoa> getById(final Long processId) {
		return processRepository.findById(processId);
	}*/

	public void save(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
	}
}