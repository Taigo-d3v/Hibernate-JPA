package DAO;

import jakarta.persistence.*;
import modelo.Usuario;

import java.util.List;

public class UsuarioDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");

    public void salvar(Usuario usuario) {
        EntityManager em = emf.createEntityManager(); // Cria uma conexao com o BD
        em.getTransaction().begin();	// Inicia uma transação que garante que só seja confimardo se tudo ocorrer bem
        em.persist(usuario);	//	Registra a Entity (usuario) no BD
        em.getTransaction().commit();	// Finaliza e confirma se tudo ocorreu bem, se não, nada é alterado no BD.
        em.close();
    }

    public Usuario consultarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Usuario usuario = em.find(Usuario.class, id);	// Equivalente à SELECT * FROM entity WHERE id = ?;
        em.close();
        return usuario;
    }

    public void excluir(Long id) {
        EntityManager em = emf.createEntityManager();
        Usuario usuario = em.find(Usuario.class, id);
        if (usuario != null) {
            em.getTransaction().begin();
            em.remove(usuario);
            em.getTransaction().commit();
        }
        em.close();
    }

    public List<Usuario> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Usuario> usuarios = em.createQuery("FROM Usuario", Usuario.class).getResultList();
        em.close();
        return usuarios;
    }
}
