package celune.persistencia;

import celunet.logica.Celular;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CelularJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public CelularJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CelularJpaController() {
        emf = Persistence.createEntityManagerFactory("CelunetJPA");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // === MÉTODOS CRUD ===
    // CREATE
    public void create(Celular celular) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(celular);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error al crear celular: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // READ (Buscar por ID)
    public Celular findCelular(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Celular.class, id);
        } finally {
            em.close();
        }
    }

    // READ (Listar todos)
    public List<Celular> findCelularEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Celular> query = em.createQuery("SELECT c FROM Celular c", Celular.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // UPDATE
    public void edit(Celular celular) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(celular);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error al actualizar celular: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // DELETE
    public void destroy(int id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Celular celular = em.find(Celular.class, id);
            if (celular != null) {
                em.remove(celular);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error al eliminar celular: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Celular findByClienteId(int clienteId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Celular> q = em.createQuery(
                    "SELECT c FROM Celular c WHERE c.cliente.id = :id",
                    Celular.class
            );
            q.setParameter("id", clienteId);

            List<Celular> resultados = q.getResultList();
            return resultados.isEmpty() ? null : resultados.get(0);

        } finally {
            em.close();
        }
    }

    public Celular findByModelo(String modelo) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Celular> q = em.createQuery(
                    "SELECT c FROM Celular c WHERE c.modelo = :modelo",
                    Celular.class
            );
            q.setParameter("modelo", modelo);

            List<Celular> resultados = q.getResultList();
            return resultados.isEmpty() ? null : resultados.get(0);

        } finally {
            em.close();
        }
    }

}
