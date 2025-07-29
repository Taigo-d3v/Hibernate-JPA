package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.Exemplar;

import java.util.List;
 
public class ExemplarDAO {
 
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
 
    public void salvar(Exemplar exemplar) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(exemplar);
        em.getTransaction().commit();
        em.close();
    }
 
    public Exemplar consultarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Exemplar exemplar = em.find(Exemplar.class, id);
        em.close();
        return exemplar;
    }
 
    public void excluir(Long id) {
        EntityManager em = emf.createEntityManager();
        Exemplar exemplar = em.find(Exemplar.class, id);
        if (exemplar != null) {
            em.getTransaction().begin();
            em.remove(exemplar);
            em.getTransaction().commit();
        }
        em.close();
    }
 
    public List<Exemplar> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Exemplar> exemplares = em.createQuery("FROM Exemplar", Exemplar.class).getResultList();
        em.close();
        return exemplares;
    }
 
    // Exemplo de método específico para a entidade
    public List<Exemplar> listarPorLivroId(Long livroId) {
        EntityManager em = emf.createEntityManager();
        List<Exemplar> exemplares = em.createQuery(
                        "SELECT e FROM Exemplar e WHERE e.livro.id = :livroId", Exemplar.class)
                .setParameter("livroId", livroId)
                .getResultList();
        em.close();
        return exemplares;
    }
}
