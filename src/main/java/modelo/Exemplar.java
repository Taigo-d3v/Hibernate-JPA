package modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "exemplares")
public class Exemplar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 20)
	private String codigo;

	private boolean emprestado;

	@ManyToOne
	@JoinColumn(name = "livro_id", nullable = false)
	private Livro livro;

	public Exemplar() {
	}

	public Exemplar(String codigo, boolean emprestado, Livro livro) {
		this.codigo = codigo;
		this.emprestado = emprestado;
		this.livro = livro;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public boolean isEmprestado() {
		return emprestado;
	}

	public void setEmprestado(boolean emprestado) {
		this.emprestado = emprestado;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	@Override
	public String toString() {
		return "Exemplar{" + "id=" + id + ", codigo='" + codigo + '\'' + ", emprestado=" + emprestado + ", livro="
				+ (livro != null ? livro.getTitulo() : "null") + '}';
	}
}
