package modelo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date dataEmprestimo;

	@Temporal(TemporalType.DATE)
	private Date dataPrevistaDevolucao;

	@Temporal(TemporalType.DATE)
	private Date dataEntregaReal;

	private int situacao; //

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@OneToOne
	@JoinColumn(name = "exemplar_id", nullable = false, unique = true)
	private Exemplar exemplar;

	public Emprestimo() {
	}

	public Emprestimo(Date dataEmprestimo, Date dataPrevistaDevolucao, int situacao, Usuario usuario,
			Exemplar exemplar) {
		this.dataEmprestimo = dataEmprestimo;
		this.dataPrevistaDevolucao = dataPrevistaDevolucao;
		this.situacao = situacao;
		this.usuario = usuario;
		this.exemplar = exemplar;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataPrevistaDevolucao() {
		return dataPrevistaDevolucao;
	}

	public void setDataPrevistaDevolucao(Date dataPrevistaDevolucao) {
		this.dataPrevistaDevolucao = dataPrevistaDevolucao;
	}

	public Date getDataEntregaReal() {
		return dataEntregaReal;
	}

	public void setDataEntregaReal(Date dataEntregaReal) {
		this.dataEntregaReal = dataEntregaReal;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Exemplar getExemplar() {
		return exemplar;
	}

	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}

	@Override
	public String toString() {
		return "Emprestimo{" + "id=" + id + ", dataEmprestimo=" + dataEmprestimo + ", dataPrevistaDevolucao="
				+ dataPrevistaDevolucao + ", dataEntregaReal=" + dataEntregaReal + ", situacao=" + situacao
				+ ", usuario=" + (usuario != null ? usuario.getNome() : "null") + ", exemplar="
				+ (exemplar != null ? exemplar.getCodigo() : "null") + '}';
	}
}