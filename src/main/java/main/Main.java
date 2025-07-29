package main;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import DAO.EmprestimoDAO;
import DAO.ExemplarDAO;
import DAO.LivroDAO;
import DAO.UsuarioDAO;
import modelo.Emprestimo;
import modelo.Exemplar;
import modelo.Livro;
import modelo.Usuario;

public class Main {

	public static void main(String[] args) {

		// Instanciando os DAOs
		LivroDAO livroDAO = new LivroDAO();
		ExemplarDAO exemplarDAO = new ExemplarDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

		try {
			// 1. Cadastro de Livros
			Livro livro1 = new Livro();
			livro1.setTitulo("Dom Casmurro");
			livro1.setAutor("Machado de Assis");
			livro1.setAno(Date.valueOf("1899-01-01"));
			livro1.setEdicao(1);
			livro1.setEditora("Editora Garnier");
			livro1.setIsbn("978-85-7232-000-0");
			livroDAO.salvar(livro1);

			Livro livro2 = new Livro();
			livro2.setTitulo("O Senhor dos Anéis");
			livro2.setAutor("J.R.R. Tolkien");
			livro2.setAno(Date.valueOf("1954-07-29"));
			livro2.setEdicao(3);
			livro2.setEditora("Martins Fontes");
			livro2.setIsbn("978-85-336-2323-3");
			livroDAO.salvar(livro2);

			// 2. Cadastro de Exemplares
			Exemplar exemplar1 = new Exemplar();
			exemplar1.setCodigo("EX-001");
			exemplar1.setEmprestado(false);
			exemplar1.setLivro(livro1);
			exemplarDAO.salvar(exemplar1);

			Exemplar exemplar2 = new Exemplar();
			exemplar2.setCodigo("EX-002");
			exemplar2.setEmprestado(false);
			exemplar2.setLivro(livro1);
			exemplarDAO.salvar(exemplar2);

			Exemplar exemplar3 = new Exemplar();
			exemplar3.setCodigo("EX-003");
			exemplar3.setEmprestado(false);
			exemplar3.setLivro(livro2);
			exemplarDAO.salvar(exemplar3);

			// 3. Cadastro de Usuários
			Usuario usuario1 = new Usuario();
			usuario1.setNome("João Silva");
			usuario1.setCpf("123.456.789-00");
			usuario1.setTelefone("(11) 9999-8888");
			usuario1.setMatricula("20230001");
			usuarioDAO.salvar(usuario1);

			Usuario usuario2 = new Usuario();
			usuario2.setNome("Maria Oliveira");
			usuario2.setCpf("987.654.321-00");
			usuario2.setTelefone("(11) 7777-6666");
			usuario2.setMatricula("20230002");
			usuarioDAO.salvar(usuario2);

			// 4. Realizar Empréstimos
			Emprestimo emprestimo1 = new Emprestimo();
			emprestimo1.setDataEmprestimo(new Date(System.currentTimeMillis()));
			emprestimo1.setDataPrevistaDevolucao(Date.valueOf("2023-12-31"));

			emprestimo1.setSituacao(1);
			emprestimo1.setUsuario(usuario1);
			emprestimo1.setExemplar(exemplar1);
			emprestimoDAO.salvar(emprestimo1);

			// Atualizar status do exemplar
			exemplar1.setEmprestado(true);
			exemplarDAO.salvar(exemplar1);

			Emprestimo emprestimo2 = new Emprestimo();
			emprestimo2.setDataEmprestimo(new Date(System.currentTimeMillis()));
			emprestimo2.setDataPrevistaDevolucao(Date.valueOf("2023-12-15"));
			emprestimo2.setSituacao(1);
			emprestimo2.setUsuario(usuario2);
			emprestimo2.setExemplar(exemplar3);
			emprestimoDAO.salvar(emprestimo2);

			exemplar3.setEmprestado(true);
			exemplarDAO.salvar(exemplar3);

			// 5. Consultas e exibição dos dados
			System.out.println("\n=== LIVROS CADASTRADOS ===");
			List<Livro> livros = livroDAO.listarTodos();
			livros.forEach(System.out::println);

			System.out.println("\n=== EXEMPLARES CADASTRADOS ===");
			List<Exemplar> exemplares = exemplarDAO.listarTodos();
			exemplares.forEach(System.out::println);

			System.out.println("\n=== EXEMPLARES DO LIVRO 1 ===");
			List<Exemplar> exemplaresLivro1 = exemplarDAO.listarPorLivroId(livro1.getId());
			exemplaresLivro1.forEach(System.out::println);

			System.out.println("\n=== USUÁRIOS CADASTRADOS ===");
			List<Usuario> usuarios = usuarioDAO.listarTodos();
			usuarios.forEach(System.out::println);

			System.out.println("\n=== EMPRÉSTIMOS CADASTRADOS ===");
			List<Emprestimo> emprestimos = emprestimoDAO.listarTodos();
			emprestimos.forEach(System.out::println);

			System.out.println("\n=== EMPRÉSTIMOS DO USUÁRIO 1 ===");
			List<Emprestimo> emprestimosUsuario1 = emprestimoDAO.listarPorUsuarioId(usuario1.getId());
			emprestimosUsuario1.forEach(System.out::println);

			// 6. Exemplo de devolução de livro
			System.out.println("\n=== PROCESSO DE DEVOLUÇÃO ===");
			Emprestimo emp = emprestimoDAO.consultarPorId(emprestimo1.getId());
			if (emp != null) {
				emp.setDataEntregaReal(new Date(System.currentTimeMillis()));
				
				emp.setSituacao(2);
				emprestimoDAO.salvar(emp);

				Exemplar ex = exemplarDAO.consultarPorId(emp.getExemplar().getId());
				ex.setEmprestado(false);
				exemplarDAO.salvar(ex);

				System.out.println("Empréstimo devolvido: " + emp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
