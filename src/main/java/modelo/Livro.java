package modelo;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Livros")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private String autor;

	@Temporal(TemporalType.DATE)
	private Date ano;

	private int edicao;
	private String editora;

	@Column(length = 20)
	private String isbn;

	@OneToMany(mappedBy = "livro", cascade = CascadeType.ALL)
	private List<Exemplar> exemplares;

	public Livro() {
	}

	public Livro(String titulo, String autor, Date ano, int edicao, String editora, String isbn) {

		super();
		this.titulo = titulo;
		this.autor = autor;
		this.ano = ano;
		this.edicao = edicao;
		this.editora = editora;
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", ano=" + ano + ", edicao=" + edicao
				+ ", editora=" + editora + ", isbn=" + isbn + "]";
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
