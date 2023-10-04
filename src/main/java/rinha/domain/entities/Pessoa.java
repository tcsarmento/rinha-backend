package rinha.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PESSOA")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "APELIDO", nullable = false)
	@NotNull
	@Pattern(regexp = ".*[a-zA-ZÀ-ÖØ-öø-ÿÇç].*")
	@Pattern(regexp = "^.{1,32}$")
	private String apelido;

	@Column(name = "NOME", nullable = false)
	@NotNull
	@Pattern(regexp = ".*[a-zA-ZÀ-ÖØ-öø-ÿÇç].*")
	@Pattern(regexp = "^.{1,100}$")
	private String nome;

	@Column(name = "NASCIMENTO", nullable = false)
	@NotNull
	private String nascimento;

	@Column(name = "STACK")
	@JsonIgnore
	private String stacks;

	@Transient
	private List<String> stack;

	@PrePersist
	private void prePersist() {
		String uuid = UUID.randomUUID().toString();
		this.id = uuid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getStacks() {
		return stacks;
	}

	public void setStacks(String stacks) {
		this.stacks = getStack().toString();
	}

	public List<String> getStack() {
		if(getStacks() != null) {
			stack = Arrays.asList(getStacks().split(","));
		}
		return stack;
	}

	public void setStack(List<String> stack) {
		this.stack = stack;
	}
}