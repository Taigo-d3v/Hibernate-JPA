package DAO;

import jakarta.persistence.*;
import modelo.Livro;

import java.util.List;

public class LivroDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");

    public void salvar(Livro livro) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(livro);
        em.getTransaction().commit();
        em.close();
    }

    public Livro consultarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Livro livro = em.find(Livro.class, id);
        em.close();
        return livro;
    }

    public void excluir(Long id) {
        EntityManager em = emf.createEntityManager();
        Livro livro = em.find(Livro.class, id);
        if (livro != null) {
            em.getTransaction().begin();
            em.remove(livro);
            em.getTransaction().commit();
        }
        em.close();
    }

    public List<Livro> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Livro> livros = em.createQuery("FROM Livro", Livro.class).getResultList();
        em.close();
        return livros;
    }
}
