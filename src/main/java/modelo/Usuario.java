package modelo;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String cpf;
	@Column(nullable = false)
	private String telefone;
	@Column(nullable = false)
	private String matricula;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Emprestimo> emprestimos;

	public Usuario() {

	}

	public Usuario(String nome, String cpf, String telefone, String matricula) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + ", matricula="
				+ matricula + "]";
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
