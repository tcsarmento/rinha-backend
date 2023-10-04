package rinha.api.rest.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rinha.domain.entities.Pessoa;
import rinha.domain.exceptions.HttpException;
import rinha.domain.exceptions.NotFoundException;
import rinha.domain.exceptions.UnprocessableEntityException;
import rinha.domain.repositories.PessoaRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping
public class PessoaEndpoint {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping(value = "/pessoas/{id}")
	public ResponseEntity<Pessoa> getId(@PathVariable("id") final String id) {
		final Pessoa pessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(""));

		return ResponseEntity.ok().body(pessoa);
	}

	@GetMapping(value = "/pessoas")
	public ResponseEntity<List<Pessoa>> getPessoas(@RequestParam(name = "t", required = false) String param) {
		if(param == null) throw new HttpException("",HttpStatus.BAD_REQUEST);
		final List<Pessoa> pessoas = pessoaRepository.findPessoasLikeTermo(param);

		return ResponseEntity.ok().body(pessoas);
	}

	@GetMapping(value = "/contagem-pessoas")
	public ResponseEntity<Long> getContagem() {
		final Long contagem = pessoaRepository.findCount();

		return ResponseEntity.ok().body(contagem);
	}

	@PostMapping(value = "/pessoas")
	public ResponseEntity<?> save(@RequestBody @Valid final Pessoa pessoa) {

		Optional<Pessoa> pessoaOptional =
				pessoaRepository.findPessoaByApelido(pessoa.getApelido());

		pessoaOptional.ifPresent(p -> {
			throw new UnprocessableEntityException("");
		});

		validatorStack(pessoa);
		validateNascimento(pessoa);

		return ResponseEntity.created(
				URI.create(
						String.format("/pessoas/"+pessoaRepository.save(pessoa).getId())))
				.build();
	}

	private void validateNascimento(Pessoa pessoa) {
		if (!pessoa.getNascimento().matches("\\d{4}-\\d{2}-\\d{2}")) {
			throw new HttpException("",HttpStatus.BAD_REQUEST);
		}
	}

	private void validatorStack(Pessoa pessoa) {
		List<String> stacks = pessoa.getStack();
		if(Objects.isNull(stacks)) return;

		stacks.forEach(s -> isValidTextStackAndSize(s));
	}

	private HttpStatus isValidTextStackAndSize (String text) {
		if (!text.matches(".*[a-zA-ZÀ-ÖØ-öø-ÿÇç].*")) {
			throw new HttpException("",HttpStatus.BAD_REQUEST);
		}

		if (!text.matches("^.{1,32}$")) {
			throw new HttpException("",HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return null;
	}
}