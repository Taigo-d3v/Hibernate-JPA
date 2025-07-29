package DAO;


import jakarta.persistence.*;
import modelo.Emprestimo;

import java.util.List;

public class EmprestimoDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");

    public void salvar(Emprestimo emprestimo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(emprestimo);
        em.getTransaction().commit();
        em.close();
    }

    public Emprestimo consultarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Emprestimo emprestimo = em.find(Emprestimo.class, id);
        em.close();
        return emprestimo;
    }

    public void excluir(Long id) {
        EntityManager em = emf.createEntityManager();
        Emprestimo emprestimo = em.find(Emprestimo.class, id);
        if (emprestimo != null) {
            em.getTransaction().begin();
            em.remove(emprestimo);
            em.getTransaction().commit();
        }
        em.close();
    }

    public List<Emprestimo> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Emprestimo> emprestimos = em.createQuery("FROM Emprestimo", Emprestimo.class).getResultList();
        em.close();
        return emprestimos;
    }

    // Exemplo de método específico: listar por usuário
    public List<Emprestimo> listarPorUsuarioId(Long usuarioId) {
        EntityManager em = emf.createEntityManager();
        List<Emprestimo> lista = em.createQuery(
                "SELECT e FROM Emprestimo e WHERE e.usuario.id = :usuarioId", Emprestimo.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
        em.close();
        return lista;
    }
}